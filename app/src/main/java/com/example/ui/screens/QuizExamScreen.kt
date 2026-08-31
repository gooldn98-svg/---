package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ActiveQuizState
import com.example.data.BurhanViewModel
import com.example.data.ScreenDestination
import com.example.data.model.ExplanationStep
import com.example.data.model.Question
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizExamScreen(
    viewModel: BurhanViewModel,
    modifier: Modifier = Modifier
) {
    val quizState by viewModel.quizState.collectAsState()
    val questions = quizState.questions
    val currentIdx = quizState.currentQuestionIndex
    val currentQuestion = questions.getOrNull(currentIdx)

    if (currentQuestion == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = PrimaryBlue)
        }
        return
    }

    val minutes = quizState.secondsRemaining / 60
    val seconds = quizState.secondsRemaining % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)
    val isTimerLow = quizState.secondsRemaining < 120

    val optionLabels = listOf("أ", "ب", "ج", "د")
    val isBookmarked = quizState.bookmarkedQuestions.contains(currentQuestion.id)
    val userSelectedOption = quizState.userAnswers[currentIdx]
    val isAnswered = userSelectedOption != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = quizState.targetTitle,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Text(
                            text = "سؤال ${currentIdx + 1} من ${questions.size}",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(ScreenDestination.Home) },
                        modifier = Modifier.testTag("exit_quiz_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "إنهاء وخروج"
                        )
                    }
                },
                actions = {
                    // Timer Chip
                    Surface(
                        color = if (isTimerLow) ErrorRedLight else Color(0xFFEFF6FF),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isTimerLow) ErrorRed else Color(0xFFBFDBFE)
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = "المؤقت",
                                tint = if (isTimerLow) ErrorRed else PrimaryBlue,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = formattedTime,
                                color = if (isTimerLow) ErrorRed else PrimaryBlue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }

                    // Bookmark Button
                    IconButton(
                        onClick = { viewModel.toggleBookmark(currentQuestion.id) },
                        modifier = Modifier.testTag("bookmark_question_button")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "حفظ السؤال",
                            tint = if (isBookmarked) GoldMedal else TextSecondary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceWhite)
            )
        },
        bottomBar = {
            Surface(
                color = SurfaceWhite,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Previous Button
                    OutlinedButton(
                        onClick = { viewModel.previousQuestion() },
                        enabled = currentIdx > 0,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("previous_question_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "السابق"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("السابق", fontWeight = FontWeight.SemiBold)
                    }

                    // Steps of Solution button
                    OutlinedButton(
                        onClick = { viewModel.toggleExplanationDialog(true) },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentTeal),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AccentTeal),
                        modifier = Modifier.testTag("view_solution_steps_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "خطوات الحل",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("خطوات الحل", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    // Next / Submit Button
                    Button(
                        onClick = { viewModel.nextQuestion() },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("next_question_button")
                    ) {
                        Text(
                            text = if (currentIdx == questions.size - 1) "تسليم الاختبار" else "التالي",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "التالي"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(BackgroundLight)
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Exam Header Info & Progress Bar
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = currentQuestion.branch.color.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "${currentQuestion.branch.titleAr} | ${currentQuestion.unitName}",
                            color = currentQuestion.branch.color,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = currentQuestion.author,
                                color = Color(0xFF166534),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "• ${currentQuestion.yearSource}",
                                color = TextSecondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { (currentIdx + 1) / questions.size.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = PrimaryBlue,
                    trackColor = BorderLight
                )
            }

            // Question Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryBlue),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${currentIdx + 1}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "السؤال الوزاري المؤتمت:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = PrimaryBlue
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = currentQuestion.questionText,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            lineHeight = 26.sp
                        )

                        if (!currentQuestion.mathEquation.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(
                                color = Color(0xFFF8FAFC),
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = currentQuestion.mathEquation,
                                    modifier = Modifier.padding(14.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFF1E293B),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // Yemeni Bubble-Style Multiple Choice Options (أ، ب، ج، د)
            item {
                Text(
                    text = "اختر الإجابة الصحيحة (تظليل الدائرة المؤتمتة):",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                )
            }

            items(currentQuestion.options.indices.toList()) { optionIdx ->
                val optionText = currentQuestion.options[optionIdx]
                val isSelected = userSelectedOption == optionIdx
                val isCorrect = optionIdx == currentQuestion.correctAnswerIndex

                // Determine styling based on selection & review
                val borderColor = when {
                    isSelected && isCorrect -> SuccessGreen
                    isSelected && !isCorrect -> ErrorRed
                    isSelected -> PrimaryBlue
                    else -> BorderLight
                }

                val cardBg = when {
                    isSelected && isCorrect -> SuccessGreenLight
                    isSelected && !isCorrect -> ErrorRedLight
                    isSelected -> Color(0xFFEFF6FF)
                    else -> SurfaceCard
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { viewModel.selectOption(optionIdx) }
                        .testTag("option_card_$optionIdx"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    border = androidx.compose.foundation.BorderStroke(if (isSelected) 2.dp else 1.dp, borderColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 3.dp else 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Bubble Indicator (e.g. أ, ب, ج, د)
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        isSelected && isCorrect -> SuccessGreen
                                        isSelected && !isCorrect -> ErrorRed
                                        isSelected -> PrimaryBlue
                                        else -> SurfaceSubtle
                                    }
                                )
                                .border(
                                    1.5.dp,
                                    if (isSelected) Color.Transparent else Color(0xFF94A3B8),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = optionLabels.getOrElse(optionIdx) { "${optionIdx + 1}" },
                                color = if (isSelected) Color.White else TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = optionText,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = TextPrimary,
                            modifier = Modifier.weight(1f)
                        )

                        if (isSelected) {
                            Icon(
                                imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                contentDescription = if (isCorrect) "إجابة صحيحة" else "إجابة خاطئة",
                                tint = if (isCorrect) SuccessGreen else ErrorRed,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }

            // Quick notice if wrong answer selected: Show hint to view solution steps
            if (isAnswered && userSelectedOption != currentQuestion.correctAnswerIndex) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.toggleExplanationDialog(true) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = ErrorRedLight),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFECACA))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.ErrorOutline,
                                contentDescription = "تنبيه",
                                tint = ErrorRed,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "إجابة غير دقيقة! اضغط هنا لمشاهدة خطوات الحل المنطقي",
                                    color = Color(0xFF991B1B),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = "الإجابة الصحيحة هي (${optionLabels.getOrElse(currentQuestion.correctAnswerIndex) { "" }})",
                                    color = Color(0xFFB91C1C),
                                    fontSize = 11.sp
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "عرض الحل",
                                tint = ErrorRed
                            )
                        }
                    }
                }
            }
        }
    }

    // Step-by-Step Explanation Modal / Bottom Sheet
    if (quizState.showExplanationDialog) {
        StepByStepExplanationDialog(
            question = currentQuestion,
            onDismiss = { viewModel.toggleExplanationDialog(false) }
        )
    }
}

@Composable
fun StepByStepExplanationDialog(
    question: Question,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFCCFBF1)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = "البرهان المنطقي",
                        tint = AccentTeal,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "خطوات الحل الرياضي المنطقي",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "البرهان الكامل للسؤال",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(question.explanationSteps) { step ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    color = PrimaryBlue,
                                    shape = CircleShape
                                ) {
                                    Text(
                                        text = "${step.stepNumber}",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = step.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = PrimaryNavy
                                )
                            }

                            if (!step.mathFormula.isNullOrEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Surface(
                                    color = Color(0xFFF1F5F9),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = step.mathFormula,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF1E3A8A),
                                        modifier = Modifier.padding(8.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = step.explanation,
                                fontSize = 12.sp,
                                color = TextSecondary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("فهمت الخطوات، متابعة", fontWeight = FontWeight.Bold)
            }
        },
        shape = RoundedCornerShape(20.dp),
        containerColor = SurfaceWhite
    )
}
