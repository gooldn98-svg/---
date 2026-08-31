package com.example.data.model

data class ExplanationStep(
    val stepNumber: Int,
    val title: String,
    val mathFormula: String? = null,
    val explanation: String
)

data class Question(
    val id: String,
    val branch: SubjectBranch,
    val unitName: String,
    val lessonName: String,
    val questionText: String,
    val mathEquation: String? = null,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanationSteps: List<ExplanationStep>,
    val yearSource: String = "وزاري 2023 - نماذج الأمانة",
    val author: String = "إعداد: أ/ أنيس المقطري",
    val difficulty: Difficulty = Difficulty.MEDIUM
)

data class UnitTopic(
    val id: String,
    val branch: SubjectBranch,
    val titleAr: String,
    val lessonCount: Int,
    val questionCount: Int,
    val masteryPercent: Int
)
