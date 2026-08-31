package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.ColorAlgebra
import com.example.ui.theme.ColorCalculus
import com.example.ui.theme.ColorGeometry
import com.example.ui.theme.ColorProbability

enum class SubjectBranch(
    val titleAr: String,
    val descriptionAr: String,
    val color: Color
) {
    ALGEBRA(
        titleAr = "الجبر",
        descriptionAr = "الأعداد المركبة، مبدأ العد، ذات الحدين، المصفوفات والمحددات",
        color = ColorAlgebra
    ),
    GEOMETRY(
        titleAr = "الهندسة التحليلية والفراغية",
        descriptionAr = "القطوع المخروطية (المكافئ، الناقص، الزائد) والمتجهات في الفضاء",
        color = ColorGeometry
    ),
    CALCULUS(
        titleAr = "التفاضل وتطبيقاته",
        descriptionAr = "نهايات الدوال، الاتصال، قواعد الاشتقاق، والمعدلات والقيم العظمى",
        color = ColorCalculus
    ),
    INTEGRATION(
        titleAr = "التكامل وتطبيقاته",
        descriptionAr = "التكامل بالتعويض والتجزئة، التكامل المحدود، حساب المساحات والحجوم",
        color = Color(0xFF0D9488)
    ),
    PROBABILITY(
        titleAr = "الاحتمالات والإحصاء",
        descriptionAr = "الاحتمال الشرطي، الاستقلال، المتغير العشوائي والتوزيع الاحتمالي",
        color = ColorProbability
    )
}

enum class TrainingMode(
    val titleAr: String,
    val subtitleAr: String,
    val defaultQuestionCount: Int,
    val durationMinutes: Int
) {
    LESSON(
        titleAr = "تدريب على مستوى الدرس",
        subtitleAr = "أسئلة مركزة لتثبيت المفاهيم والقواعد لكل درس بشكل فردي",
        defaultQuestionCount = 5,
        durationMinutes = 10
    ),
    UNIT(
        titleAr = "اختبار شامل للوحدة",
        subtitleAr = "تقييم متكامل لكل دروس الوحدة لقياس مدى الاستيعاب الإجمالي",
        defaultQuestionCount = 15,
        durationMinutes = 25
    ),
    MINISTERIAL_EXAM(
        titleAr = "نموذج وزاري شامل مؤتمت",
        subtitleAr = "محاكاة واقعية لاختبارات وزارة التربية والتعليم اليمنية بنظام الأتمتة",
        defaultQuestionCount = 20,
        durationMinutes = 45
    )
}

enum class Difficulty(val titleAr: String, val xpReward: Int) {
    EASY("مباشر", 10),
    MEDIUM("متوسط", 20),
    HARD("متقدم وزارياً", 35)
}
