package com.example.data.model

enum class BadgeTier(val titleAr: String, val colorHex: Long) {
    BRONZE("برونزية", 0xFFD97706),
    SILVER("فضية", 0xFF94A3B8),
    GOLD("ذهبية", 0xFFF59E0B)
}

data class Badge(
    val id: String,
    val titleAr: String,
    val descriptionAr: String,
    val tier: BadgeTier,
    val iconSymbol: String,
    val isUnlocked: Boolean = false,
    val unlockedAt: String? = null
)

data class BranchStat(
    val branch: SubjectBranch,
    val questionsAttempted: Int,
    val correctAnswers: Int,
    val accuracyPercent: Int,
    val statusLevel: String // "ممتاز", "جيد جداً", "يحتاج لمراجعة", "نقطة ضعف"
)

data class StudentProfile(
    val id: String = "student_1",
    val fullName: String = "الطالب المثابر",
    val governorate: String = "صنعاء / تعز / عدن",
    val schoolYear: String = "ثالث ثانوي - علمي",
    val streakDays: Int = 5,
    val totalXp: Int = 1250,
    val rankTitle: String = "متمكن الرياضيات",
    val completedTestsCount: Int = 14,
    val totalQuestionsSolved: Int = 180,
    val correctQuestionsCount: Int = 148,
    val overallAccuracy: Int = 82,
    val badges: List<Badge> = listOf(
        Badge(
            id = "first_exam",
            titleAr = "الانطلاقة الأولى",
            descriptionAr = "أتممت أول نموذج تدريبي وزاري بنجاح",
            tier = BadgeTier.BRONZE,
            iconSymbol = "🎯",
            isUnlocked = true,
            unlockedAt = "منذ 4 أيام"
        ),
        Badge(
            id = "streak_3",
            titleAr = "شعلة المثابرة",
            descriptionAr = "استمرارية تدريب لمدة 3 أيام متتالية",
            tier = BadgeTier.BRONZE,
            iconSymbol = "🔥",
            isUnlocked = true,
            unlockedAt = "منذ يومين"
        ),
        Badge(
            id = "algebra_master",
            titleAr = "فارس الجبر والمحددات",
            descriptionAr = "تحقيق نسبة دقة 85%+ في وحدة الأعداد المركبة والمصفوفات",
            tier = BadgeTier.SILVER,
            iconSymbol = "⚡",
            isUnlocked = true,
            unlockedAt = "أمس"
        ),
        Badge(
            id = "calculus_ace",
            titleAr = "عبقري التفاضل والتكامل",
            descriptionAr = "إتقان 25 مسألة اشتقاق وتكامل دون أخطاء",
            tier = BadgeTier.SILVER,
            iconSymbol = "📐",
            isUnlocked = false
        ),
        Badge(
            id = "streak_7",
            titleAr = "تاج الاستمرارية",
            descriptionAr = "الحفاظ على المذاكرة اليومية لـ 7 أيام متتالية",
            tier = BadgeTier.GOLD,
            iconSymbol = "👑",
            isUnlocked = false
        ),
        Badge(
            id = "perfect_ministerial",
            titleAr = "العلامة الكاملة الوزارية",
            descriptionAr = "إحراز 100% في نموذج وزاري مؤتمت شامل مع المؤقت",
            tier = BadgeTier.GOLD,
            iconSymbol = "🏆",
            isUnlocked = false
        ),
        Badge(
            id = "anis_maqtari_honor",
            titleAr = "وسام سلسلة أ/ أنيس المقطري",
            descriptionAr = "إتقان وحل نماذج سلسلة التميز للأستاذ القدير أنيس المقطري",
            tier = BadgeTier.GOLD,
            iconSymbol = "🌟",
            isUnlocked = true,
            unlockedAt = "متاح الآن"
        )
    ),
    val branchPerformance: Map<SubjectBranch, BranchStat> = mapOf(
        SubjectBranch.ALGEBRA to BranchStat(
            branch = SubjectBranch.ALGEBRA,
            questionsAttempted = 50,
            correctAnswers = 45,
            accuracyPercent = 90,
            statusLevel = "نقطة قوة ممتازة"
        ),
        SubjectBranch.GEOMETRY to BranchStat(
            branch = SubjectBranch.GEOMETRY,
            questionsAttempted = 35,
            correctAnswers = 29,
            accuracyPercent = 83,
            statusLevel = "مستوى متقدم"
        ),
        SubjectBranch.CALCULUS to BranchStat(
            branch = SubjectBranch.CALCULUS,
            questionsAttempted = 45,
            correctAnswers = 38,
            accuracyPercent = 84,
            statusLevel = "مستوى جيد جداً"
        ),
        SubjectBranch.INTEGRATION to BranchStat(
            branch = SubjectBranch.INTEGRATION,
            questionsAttempted = 30,
            correctAnswers = 20,
            accuracyPercent = 67,
            statusLevel = "يحتاج تركيز وتدريب"
        ),
        SubjectBranch.PROBABILITY to BranchStat(
            branch = SubjectBranch.PROBABILITY,
            questionsAttempted = 20,
            correctAnswers = 16,
            accuracyPercent = 80,
            statusLevel = "مستوى جيد"
        )
    )
)

data class ExamResult(
    val mode: TrainingMode,
    val targetTitle: String,
    val totalQuestions: Int,
    val correctCount: Int,
    val wrongCount: Int,
    val timeSpentSeconds: Int,
    val earnedXp: Int,
    val userAnswers: Map<Int, Int>, // question index -> chosen option index
    val questions: List<Question>
)
