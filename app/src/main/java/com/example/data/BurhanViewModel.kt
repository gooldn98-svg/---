package com.example.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class ScreenDestination {
    object Home : ScreenDestination()
    data class TrainingSelection(val branch: SubjectBranch? = null) : ScreenDestination()
    data class Quiz(val mode: TrainingMode, val targetTitle: String, val branch: SubjectBranch? = null) : ScreenDestination()
    data class Result(val result: ExamResult) : ScreenDestination()
    object Dashboard : ScreenDestination()
    object Badges : ScreenDestination()
}

data class ActiveQuizState(
    val mode: TrainingMode = TrainingMode.LESSON,
    val targetTitle: String = "نموذج وزاري تجريبي",
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val userAnswers: MutableMap<Int, Int> = mutableMapOf(),
    val isSubmitted: Boolean = false,
    val showExplanationDialog: Boolean = false,
    val secondsRemaining: Int = 600,
    val totalTimeSeconds: Int = 600,
    val isTimerActive: Boolean = false,
    val bookmarkedQuestions: MutableSet<String> = mutableSetOf()
)

class BurhanViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow<ScreenDestination>(ScreenDestination.Home)
    val currentScreen: StateFlow<ScreenDestination> = _currentScreen.asStateFlow()

    private val _studentProfile = MutableStateFlow(StudentProfile())
    val studentProfile: StateFlow<StudentProfile> = _studentProfile.asStateFlow()

    private val _quizState = MutableStateFlow(ActiveQuizState())
    val quizState: StateFlow<ActiveQuizState> = _quizState.asStateFlow()

    private var timerJob: Job? = null

    fun navigateTo(screen: ScreenDestination) {
        _currentScreen.value = screen
    }

    fun startQuiz(mode: TrainingMode, targetTitle: String, branch: SubjectBranch? = null) {
        timerJob?.cancel()
        val questions = QuestionBank.getQuestionsForMode(mode, unitTitle = null, branch = branch)
        val durationSeconds = mode.durationMinutes * 60

        _quizState.value = ActiveQuizState(
            mode = mode,
            targetTitle = targetTitle,
            questions = questions,
            currentQuestionIndex = 0,
            selectedOptionIndex = null,
            userAnswers = mutableMapOf(),
            isSubmitted = false,
            showExplanationDialog = false,
            secondsRemaining = durationSeconds,
            totalTimeSeconds = durationSeconds,
            isTimerActive = true
        )

        _currentScreen.value = ScreenDestination.Quiz(mode, targetTitle, branch)
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_quizState.value.secondsRemaining > 0 && !_quizState.value.isSubmitted) {
                delay(1000)
                _quizState.update { it.copy(secondsRemaining = it.secondsRemaining - 1) }
            }
            if (_quizState.value.secondsRemaining <= 0 && !_quizState.value.isSubmitted) {
                submitExam()
            }
        }
    }

    fun selectOption(optionIndex: Int) {
        val currentIdx = _quizState.value.currentQuestionIndex
        val currentQ = _quizState.value.questions.getOrNull(currentIdx) ?: return

        _quizState.update {
            val answers = it.userAnswers.toMutableMap()
            answers[currentIdx] = optionIndex
            it.copy(
                selectedOptionIndex = optionIndex,
                userAnswers = answers,
                showExplanationDialog = (optionIndex != currentQ.correctAnswerIndex)
            )
        }
    }

    fun toggleExplanationDialog(show: Boolean) {
        _quizState.update { it.copy(showExplanationDialog = show) }
    }

    fun toggleBookmark(questionId: String) {
        _quizState.update {
            val bookmarks = it.bookmarkedQuestions.toMutableSet()
            if (bookmarks.contains(questionId)) {
                bookmarks.remove(questionId)
            } else {
                bookmarks.add(questionId)
            }
            it.copy(bookmarkedQuestions = bookmarks)
        }
    }

    fun nextQuestion() {
        val currentIdx = _quizState.value.currentQuestionIndex
        if (currentIdx < _quizState.value.questions.size - 1) {
            val nextIdx = currentIdx + 1
            val previouslySelected = _quizState.value.userAnswers[nextIdx]
            _quizState.update {
                it.copy(
                    currentQuestionIndex = nextIdx,
                    selectedOptionIndex = previouslySelected,
                    showExplanationDialog = false
                )
            }
        } else {
            submitExam()
        }
    }

    fun previousQuestion() {
        val currentIdx = _quizState.value.currentQuestionIndex
        if (currentIdx > 0) {
            val prevIdx = currentIdx - 1
            val previouslySelected = _quizState.value.userAnswers[prevIdx]
            _quizState.update {
                it.copy(
                    currentQuestionIndex = prevIdx,
                    selectedOptionIndex = previouslySelected,
                    showExplanationDialog = false
                )
            }
        }
    }

    fun submitExam() {
        timerJob?.cancel()
        val state = _quizState.value
        var correctCount = 0
        var wrongCount = 0

        state.questions.forEachIndexed { index, question ->
            val userChoice = state.userAnswers[index]
            if (userChoice == question.correctAnswerIndex) {
                correctCount++
            } else {
                wrongCount++
            }
        }

        val timeSpent = state.totalTimeSeconds - state.secondsRemaining
        val earnedXp = correctCount * 25 + (if (correctCount == state.questions.size) 50 else 10)

        val result = ExamResult(
            mode = state.mode,
            targetTitle = state.targetTitle,
            totalQuestions = state.questions.size,
            correctCount = correctCount,
            wrongCount = wrongCount,
            timeSpentSeconds = timeSpent,
            earnedXp = earnedXp,
            userAnswers = state.userAnswers,
            questions = state.questions
        )

        // Update profile
        _studentProfile.update { profile ->
            val newTotalXp = profile.totalXp + earnedXp
            val newCompleted = profile.completedTestsCount + 1
            val newTotalSolved = profile.totalQuestionsSolved + state.questions.size
            val newCorrect = profile.correctQuestionsCount + correctCount
            val newAccuracy = if (newTotalSolved > 0) (newCorrect * 100) / newTotalSolved else 0

            val updatedBadges = profile.badges.map { badge ->
                if (badge.id == "perfect_ministerial" && correctCount == state.questions.size && state.mode == TrainingMode.MINISTERIAL_EXAM) {
                    badge.copy(isUnlocked = true, unlockedAt = "الآن")
                } else if (badge.id == "calculus_ace" && correctCount >= 3) {
                    badge.copy(isUnlocked = true, unlockedAt = "الآن")
                } else {
                    badge
                }
            }

            profile.copy(
                totalXp = newTotalXp,
                completedTestsCount = newCompleted,
                totalQuestionsSolved = newTotalSolved,
                correctQuestionsCount = newCorrect,
                overallAccuracy = newAccuracy,
                badges = updatedBadges
            )
        }

        _quizState.update { it.copy(isSubmitted = true) }
        _currentScreen.value = ScreenDestination.Result(result)
    }
}
