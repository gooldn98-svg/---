package com.example.data.model

object QuestionBank {

    val allUnits = listOf(
        UnitTopic(
            id = "unit_complex_numbers",
            branch = SubjectBranch.ALGEBRA,
            titleAr = "الوحدة الأولى: الأعداد المركبة",
            lessonCount = 4,
            questionCount = 35,
            masteryPercent = 88
        ),
        UnitTopic(
            id = "unit_counting_binomial",
            branch = SubjectBranch.ALGEBRA,
            titleAr = "الوحدة الثانية: مبدأ العد وذات الحدين",
            lessonCount = 3,
            questionCount = 30,
            masteryPercent = 92
        ),
        UnitTopic(
            id = "unit_matrices_determinants",
            branch = SubjectBranch.ALGEBRA,
            titleAr = "الوحدة الثالثة: المصفوفات والمحددات",
            lessonCount = 4,
            questionCount = 28,
            masteryPercent = 85
        ),
        UnitTopic(
            id = "unit_conic_sections",
            branch = SubjectBranch.GEOMETRY,
            titleAr = "الوحدة الرابعة: القطوع المخروطية",
            lessonCount = 4,
            questionCount = 40,
            masteryPercent = 78
        ),
        UnitTopic(
            id = "unit_vectors_space",
            branch = SubjectBranch.GEOMETRY,
            titleAr = "الوحدة الخامسة: المتجهات في الفضاء الثلاثي",
            lessonCount = 3,
            questionCount = 25,
            masteryPercent = 82
        ),
        UnitTopic(
            id = "unit_limits_derivatives",
            branch = SubjectBranch.CALCULUS,
            titleAr = "الوحدة السادسة: النهايات والاتصال والاشتقاق",
            lessonCount = 5,
            questionCount = 45,
            masteryPercent = 86
        ),
        UnitTopic(
            id = "unit_integration_methods",
            branch = SubjectBranch.INTEGRATION,
            titleAr = "الوحدة السابعة: التكامل وتطبيقاته الهندسية",
            lessonCount = 4,
            questionCount = 38,
            masteryPercent = 67
        ),
        UnitTopic(
            id = "unit_probability",
            branch = SubjectBranch.PROBABILITY,
            titleAr = "الوحدة الثامنة: الاحتمال الشرطي والمتغير العشوائي",
            lessonCount = 3,
            questionCount = 22,
            masteryPercent = 80
        )
    )

    val allQuestions: List<Question> = listOf(
        // Q1 - Complex Numbers (Algebra) - Anis Al-Maqtari
        Question(
            id = "alg_1",
            branch = SubjectBranch.ALGEBRA,
            unitName = "الأعداد المركبة",
            lessonName = "الصورة المثلثية وديموافر",
            questionText = "إذا كان العدد المركب ع = 1 + ت√3 ، فإن سعته الأساسية (θ) ومقياسه (ر) هما:",
            mathEquation = "ع = 1 + √3 ت",
            options = listOf(
                "ر = 2 ، θ = π/3 (60°)",
                "ر = 4 ، θ = π/6 (30°)",
                "ر = 2 ، θ = 2π/3 (120°)",
                "ر = √2 ، θ = π/4 (45°)"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "تحديد الجزأين الحقيقي والتخيلي",
                    mathFormula = "س = 1 ، ص = √3",
                    explanation = "بما أن س > 0 و ص > 0، فإن زاوية العدد المركب تقع بالكامل في الربع الأول."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "حساب المقياس (ر)",
                    mathFormula = "ر = √(س² + ص²) = √(1² + (√3)²) = √(1 + 3) = √4 = 2",
                    explanation = "قانون المقياس يعتمد على نظرية فيثاغورس، والناتج = 2."
                ),
                ExplanationStep(
                    stepNumber = 3,
                    title = "حساب السعة الأساسية (θ)",
                    mathFormula = "ظا θ = ص / س = √3 / 1 = √3 ==> θ = π/3 (60°)",
                    explanation = "الزاوية الحادة التي ظلها √3 في الربع الأول هي 60 درجة (π/3 راديان)."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.MEDIUM
        ),

        // Q2 - Complex Numbers Powers & De Moivre - Anis Al-Maqtari
        Question(
            id = "alg_moivre_1",
            branch = SubjectBranch.ALGEBRA,
            unitName = "الأعداد المركبة",
            lessonName = "مبرهنة ديموافر والجذور النونية",
            questionText = "قيمة المقدار: (جتا π/6 + ت جا π/6)⁶ تساوي:",
            mathEquation = "(جتا θ + ت جا θ)ⁿ = جتا(ن θ) + ت جا(ن θ)",
            options = listOf(
                "-1",
                "1",
                "ت",
                "-ت"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "تطبيق مبرهنة ديموافر للأس الصحيح",
                    mathFormula = "(جتا π/6 + ت جا π/6)⁶ = جتا(6 × π/6) + ت جا(6 × π/6)",
                    explanation = "نضرب الأس 6 في زاوية السعة π/6."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "التبسيط والتعويض عن قيم الدوال الدائرية لزاوية π (180°)",
                    mathFormula = "جتا(π) + ت جا(π) = -1 + ت(0) = -1",
                    explanation = "جتا 180° = -1 و جا 180° = 0 ، وبالتالي الناتج النهائي هو -1."
                )
            ),
            yearSource = "نماذج وزارية مؤتمتة - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.EASY
        ),

        // Q3 - Matrices & Determinants (Algebra)
        Question(
            id = "alg_2",
            branch = SubjectBranch.ALGEBRA,
            unitName = "المصفوفات والمحددات",
            lessonName = "خواص المحددات وقاعدة كرامر",
            questionText = "إذا كانت مصفوفة أ من الرتبة (3×3) وكان |أ| = 5 ، فإن قيمة المحدد |2أ| تساوي:",
            mathEquation = "|ك · أ| = كⁿ · |أ| (حيث ن رتبة المصفوفة)",
            options = listOf(
                "10",
                "20",
                "40",
                "80"
            ),
            correctAnswerIndex = 2,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "تطبيق الخاصية الوزارية لمحدد ضرب المصفوفة في عدد حقيقي",
                    mathFormula = "|ك · أ| = كⁿ · |أ|",
                    explanation = "حيث ك هو الثابت المضروب (2) و ن هي رتبة المصفوفة المربعة (ن = 3)."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "التعويض المباشر",
                    mathFormula = "|2أ| = 2³ × |أ| = 8 × 5 = 40",
                    explanation = "ضرب 8 في قيمة المحدد المعطى 5 يعطي الناتج 40."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.EASY
        ),

        // Q4 - Counting & Binomial Theorem - Anis Al-Maqtari
        Question(
            id = "alg_binom_1",
            branch = SubjectBranch.ALGEBRA,
            unitName = "مبدأ العد وذات الحدين",
            lessonName = "الحد العام في مفكوك ذات الحدين",
            questionText = "في مفكوك (س + 2/س)⁸ ، الحد الخالي من س هو الحد:",
            mathEquation = "ح_(ر+1) = ن_ق_ر · (الحد الثاني)^ر · (الحد الأول)^(ن - ر)",
            options = listOf(
                "الحد الخامس (ح₅)",
                "الحد الرابع (ح₄)",
                "الحد السادس (ح₆)",
                "الحد الثالث (ح₃)"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "كتابة قانون الحد العام ح_(ر+1)",
                    mathFormula = "ح_(ر+1) = ⁸ق_ر · (2/س)^ر · س^(8-ر) = ⁸ق_ر · 2^ر · س^(8 - 2ر)",
                    explanation = "تجميع أسس المتغير س في المفكوك."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "مساواة أس س بالصفر للحد الخالي",
                    mathFormula = "8 - 2ر = 0 ==> 2ر = 8 ==> ر = 4",
                    explanation = "بما أن ر = 4 ، فإن رتبة الحد الخالي من س هي ر + 1 = 4 + 1 = 5 (الحد الخامس ح₅)."
                )
            ),
            yearSource = "نماذج وزارية مؤتمتة - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.MEDIUM
        ),

        // Q5 - Conic Sections (Geometry)
        Question(
            id = "geo_1",
            branch = SubjectBranch.GEOMETRY,
            unitName = "القطوع المخروطية",
            lessonName = "معادلة القطع الناقص والزائد",
            questionText = "في القطع الناقص الذي معادلته: (س² / 25) + (ص² / 16) = 1 ، يكون الاختلاف المركزي (ي) مساوياً:",
            mathEquation = "ي = جـ / أ  (حيث جـ² = أ² - ب²)",
            options = listOf(
                "3/5 = 0.6",
                "4/5 = 0.8",
                "5/3 = 1.66",
                "9/25 = 0.36"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "إيجاد قيم أ² و ب² من المعادلة القياسية",
                    mathFormula = "أ² = 25 ==> أ = 5 ، ب² = 16 ==> ب = 4",
                    explanation = "المحور الأكبر ينطبق على محور السينات لأن المقام الأكبر تحت س²."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "حساب البعد البؤري جـ من العلاقة الأساسية للقطع الناقص",
                    mathFormula = "جـ² = أ² - ب² = 25 - 16 = 9 ==> جـ = 3",
                    explanation = "قيمة المسافة البؤرية جـ تساوي 3."
                ),
                ExplanationStep(
                    stepNumber = 3,
                    title = "حساب الاختلاف المركزي (ي)",
                    mathFormula = "ي = جـ / أ = 3 / 5",
                    explanation = "بما أن 0 < ي < 1 (ي = 0.6)، فهذا يؤكد أن المنحنى قطع ناقص والجواب هو 3/5."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.MEDIUM
        ),

        // Q6 - Calculus Limits (Calculus)
        Question(
            id = "calc_1",
            branch = SubjectBranch.CALCULUS,
            unitName = "النهايات والاتصال",
            lessonName = "نهايات الدوال المثلثية وقاعدة لوبيتال",
            questionText = "قيمة النهاية: نهــــا (س -> 0) [ جا(5س) / ظا(2س) ] تساوي:",
            mathEquation = "نهــــا (س -> 0) [ جا(أ س) / ظا(ب س) ]",
            options = listOf(
                "1",
                "5/2",
                "2/5",
                "0"
            ),
            correctAnswerIndex = 1,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "التحقق من حالة عدم التعيين",
                    mathFormula = "جا(0) / ظا(0) = 0 / 0 (حالة عدم تعيين)",
                    explanation = "تستلزم استخدام النظريات الخاصة بنهايات الدوال المثلثية أو قاعدة لوبيتال."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "القسمة بسطاً ومقاماً على س",
                    mathFormula = "[ جا(5س)/س ] ÷ [ ظا(2س)/س ]",
                    explanation = "تطبيق النظرية الأساسية: نهـا جا(أ س)/س = أ ، و نهـا ظا(ب س)/س = ب."
                ),
                ExplanationStep(
                    stepNumber = 3,
                    title = "حساب الناتج النهائي",
                    mathFormula = "5 / 2",
                    explanation = "الناتج المباشر هو نسبة معاملي الزوايا 5 / 2."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.EASY
        ),

        // Q7 - Calculus Derivatives (Calculus)
        Question(
            id = "calc_2",
            branch = SubjectBranch.CALCULUS,
            unitName = "الاشتقاق وتطبيقاته",
            lessonName = "المشتقات العليا والاشتقاق الضمني",
            questionText = "إذا كانت د(س) = لو هـ (جتا س) ، فإن المشتقة الأولى د'(س) تساوي:",
            mathEquation = "د'(س) = المشتقة داخل اللوغاريتم / الدالة نفسها",
            options = listOf(
                "- ظا س",
                "ظا س",
                "- ظتا س",
                "قا س"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "تطبيق قاعدة اشتقاق اللوغاريتم الطبيعي لو هـ (ص)",
                    mathFormula = "د/دس [ لو هـ (ص) ] = ص' / ص",
                    explanation = "مشتقة ما بداخل اللوغاريتم مقسوماً على الدالة الأصلية."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "اشتقاق الدالة المثلثية جتا س",
                    mathFormula = "مشتقة (جتا س) = - جا س",
                    explanation = "إشارة السالب أساسية لمشتقة جيب التمام."
                ),
                ExplanationStep(
                    stepNumber = 3,
                    title = "التعويض والتبسيط بالمتطابقات",
                    mathFormula = "د'(س) = - جا س / جتا س = - ظا س",
                    explanation = "نسبة الجيب إلى جيب التمام تساوي الظل (ظا س) مع إشارة السالب."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.MEDIUM
        ),

        // Q8 - Rolle's & Mean Value Theorem - Anis Al-Maqtari
        Question(
            id = "calc_rolle_1",
            branch = SubjectBranch.CALCULUS,
            unitName = "مبرهنات التفاضل",
            lessonName = "مبرهنة رول ومبرهنة القيمة المتوسطة",
            questionText = "قيمة جـ التي تحقق مبرهنة رول للدالة د(س) = س² - 4س في الفترة [0 ، 4] هي:",
            mathEquation = "د'(جـ) = 0 ، حيث جـ ∈ (0 ، 4)",
            options = listOf(
                "جـ = 2",
                "جـ = 0",
                "جـ = 4",
                "جـ = 1"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "فحص شروط مبرهنة رول",
                    mathFormula = "د(0) = 0² - 0 = 0 ، د(4) = 4² - 4(4) = 16 - 16 = 0 ==> د(0) = د(4)",
                    explanation = "الدالة متصلة وقابلة للاشتقاق وتتحقق فيها شروط مبرهنة رول الثلاثة."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "إيجاد المشتقة ومساواتها بالصفر",
                    mathFormula = "د'(س) = 2س - 4 ==> 2جـ - 4 = 0 ==> 2جـ = 4 ==> جـ = 2",
                    explanation = "بما أن 2 ينتمي للفترة المفتوحة (0 ، 4)، فإن قيمة جـ = 2."
                )
            ),
            yearSource = "نماذج وزارية مؤتمتة - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.EASY
        ),

        // Q9 - Integration (Calculus/Integration)
        Question(
            id = "integ_1",
            branch = SubjectBranch.INTEGRATION,
            unitName = "التكامل وتطبيقاته",
            lessonName = "التكامل بالتعويض والتجزئة",
            questionText = "قيمة التكامل: ∫ (2س + 3) · هـ^(س² + 3س) دس تساوي:",
            mathEquation = "∫ د'(س) · هـ^د(س) دس = هـ^د(س) + ث",
            options = listOf(
                "هـ^(س² + 3س) + ث",
                "(2س + 3) · هـ^(س² + 3س) + ث",
                "1/2 هـ^(س² + 3س) + ث",
                "هـ^(2س+3) + ث"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "فحص مشتقة الأس للدالة الأسية هـ^د(س)",
                    mathFormula = "د(س) = س² + 3س ==> د'(س) = 2س + 3",
                    explanation = "نلاحظ أن المقدار المضروب خارج الدالة هو بالضبط مشتقة أس الدالة هـ."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "تطبيق القاعدة التكاملية الأساسية",
                    mathFormula = "∫ د'(س) · هـ^د(س) دس = هـ^د(س) + ث",
                    explanation = "تكامل مشتقة الأس في الدالة الأسية يعطي الدالة الأسية نفسها مضافاً إليها ثابت التكامل ث."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.MEDIUM
        ),

        // Q10 - Definite Integration & Area - Anis Al-Maqtari
        Question(
            id = "integ_area_1",
            branch = SubjectBranch.INTEGRATION,
            unitName = "التكامل وتطبيقاته الهندسية",
            lessonName = "حساب مساحة المنطقة المستوية",
            questionText = "مساحة المنطقة المحصورة بين منحنى الدالة ص = س² ومحور السينات في الفترة [0 ، 3] تساوي:",
            mathEquation = "م = ∫₀³ س² دس",
            options = listOf(
                "9 وحدات مربعة",
                "27 وحدة مربعة",
                "3 وحدات مربعة",
                "18 وحدة مربعة"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "إعداد التكامل المحدود للمساحة",
                    mathFormula = "م = [ س³ / 3 ] من 0 إلى 3",
                    explanation = "تكامل س² هو س³/3."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "التعويض بحدي التكامل الأعلى والأدنى",
                    mathFormula = "م = (3³ / 3) - (0³ / 3) = (27 / 3) - 0 = 9",
                    explanation = "المساحة المحصورة تساوي 9 وحدات مربعة."
                )
            ),
            yearSource = "نماذج وزارية مؤتمتة - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.EASY
        ),

        // Q11 - Probability (Counting & Probability)
        Question(
            id = "prob_1",
            branch = SubjectBranch.PROBABILITY,
            unitName = "الاحتمالات والإحصاء",
            lessonName = "الاحتمال الشرطي وقانون الضرب",
            questionText = "إذا كان ل(أ) = 0.6 ، ل(ب) = 0.5 ، ل(أ ∩ ب) = 0.3 ، فإن الاحتمال الشرطي ل(أ | ب) يساوي:",
            mathEquation = "ل(أ | ب) = ل(أ ∩ ب) / ل(ب)",
            options = listOf(
                "0.6 (3/5)",
                "0.5 (1/2)",
                "0.3",
                "0.8"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "كتابة صيغة الاحتمال الشرطي لـ أ بشرط وقوع ب",
                    mathFormula = "ل(أ | ب) = ل(أ ∩ ب) / ل(ب) ، بشرط ل(ب) > 0",
                    explanation = "يقيس احتمال وقوع الحدث أ مع العلم المسبق بوقوع الحدث ب."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "التعويض بالقيم المعطاة",
                    mathFormula = "ل(أ | ب) = 0.3 / 0.5 = 3 / 5 = 0.6",
                    explanation = "نقسم قيمة التقاطع 0.3 على قيمة الشرط 0.5 فنحصل على 0.6."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.EASY
        ),

        // Q12 - Vectors 3D (Geometry)
        Question(
            id = "geo_2",
            branch = SubjectBranch.GEOMETRY,
            unitName = "المتجهات في الفضاء",
            lessonName = "الضرب القياسي والاتجاهي للمتجهات",
            questionText = "إذا كان المتجه أ = (2، -1، 3) والمتجه ب = (1، 4، 2)، فإن حاصل الضرب القياسي أ · ب يساوي:",
            mathEquation = "أ · ب = س1·س2 + ص1·ص2 + ع1·ع2",
            options = listOf(
                "4",
                "12",
                "-2",
                "6"
            ),
            correctAnswerIndex = 0,
            explanationSteps = listOf(
                ExplanationStep(
                    stepNumber = 1,
                    title = "تطبيق قانون الضرب النقطي القياسي في الفضاء الثلاثي",
                    mathFormula = "أ · ب = (2 × 1) + (-1 × 4) + (3 × 2)",
                    explanation = "ضرب المركبات المتناظرة وجمع النواتج جبرياً."
                ),
                ExplanationStep(
                    stepNumber = 2,
                    title = "إجراء العمليات الحسابية",
                    mathFormula = "أ · ب = 2 - 4 + 6 = 4",
                    explanation = "حاصل جمع (2 - 4 + 6) يساوي 4 (كمية قياسية موجبة)."
                )
            ),
            yearSource = "سلسلة التميز الوزاري - أ/ أنيس المقطري",
            author = "إعداد: أ/ أنيس المقطري",
            difficulty = Difficulty.EASY
        )
    )

    fun getQuestionsForMode(mode: TrainingMode, unitTitle: String? = null, branch: SubjectBranch? = null): List<Question> {
        val filtered = when {
            unitTitle != null -> allQuestions.filter { it.unitName.contains(unitTitle) || unitTitle.contains(it.unitName) }
            branch != null -> allQuestions.filter { it.branch == branch }
            else -> allQuestions
        }

        val pool = if (filtered.isNotEmpty()) filtered else allQuestions
        val count = mode.defaultQuestionCount.coerceAtMost(pool.size)
        return pool.shuffled().take(count.coerceAtLeast(3))
    }
}
