package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BurhanViewModel
import com.example.data.ScreenDestination
import com.example.data.model.QuestionBank
import com.example.data.model.SubjectBranch
import com.example.data.model.TrainingMode
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: BurhanViewModel,
    modifier: Modifier = Modifier
) {
    val studentProfile by viewModel.studentProfile.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // 1. Hero Header with Branding & Student Stats
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFF0F172A),
                                Color(0xFF1E3A8A),
                                Color(0xFF172554)
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "تطبيق البُرهان",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = GoldMedal,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = "اليمن 🇾🇪",
                                        color = Color.Black,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            Text(
                                text = "الرياضيات - الثالث الثانوي (القسم العلمي)",
                                fontSize = 13.sp,
                                color = Color(0xFF94A3B8)
                            )
                        }

                        // Streak & Level Chip
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = Color(0xFF1E293B),
                                shape = RoundedCornerShape(14.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "🔥", fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${studentProfile.streakDays} أيام",
                                        color = Color(0xFFFB923C),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            Surface(
                                color = Color(0xFF1E293B),
                                shape = RoundedCornerShape(14.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "⭐", fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${studentProfile.totalXp} XP",
                                        color = GoldMedal,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Student Progress Quick Bar
                    Surface(
                        color = Color(0x33FFFFFF),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "مستوى الطالب: ${studentProfile.rankTitle}",
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "نسبة الدقة العامة: ${studentProfile.overallAccuracy}% | ${studentProfile.totalQuestionsSolved} مسألة محلولة",
                                    color = Color(0xFFE2E8F0),
                                    fontSize = 12.sp
                                )
                            }
                            Button(
                                onClick = { viewModel.navigateTo(ScreenDestination.Dashboard) },
                                colors = ButtonDefaults.buttonColors(containerColor = AccentTeal),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                modifier = Modifier.testTag("view_dashboard_button")
                            ) {
                                Text("لوحة الأداء", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Dedicated Anis Al-Maqtari Series & Ministerial Models Banner
        item {
            Spacer(modifier = Modifier.height(18.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        viewModel.startQuiz(
                            mode = TrainingMode.MINISTERIAL_EXAM,
                            targetTitle = "نماذج أ/ أنيس المقطري الوزارية المؤتمتة"
                        )
                    }
                    .testTag("anis_al_maqtari_special_card"),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4)),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF86EFAC)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDCFCE7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👨‍🏫", fontSize = 28.sp)
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "إعداد أ/ أنيس المقطري",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF166534)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = Color(0xFF22C55E),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "سلسلة التميز ⭐",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "بنك الأسئلة والنماذج الوزارية المؤتمتة والشروحات المنطقية المعتمدة بإعداد الأستاذ القدير أنيس المقطري",
                            fontSize = 12.sp,
                            color = Color(0xFF15803D),
                            lineHeight = 16.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "بدء نماذج الأستاذ أنيس",
                        tint = Color(0xFF166534)
                    )
                }
            }
        }

        // 2. Primary Ministerial Exam Simulation Hero Action
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        viewModel.startQuiz(
                            mode = TrainingMode.MINISTERIAL_EXAM,
                            targetTitle = "النموذج الوزاري المؤتمت الشامل (2024)"
                        )
                    }
                    .testTag("start_ministerial_exam_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(Brush.linearGradient(listOf(Color(0xFF2563EB), Color(0xFF1D4ED8)))),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AssignmentTurnedIn,
                            contentDescription = "الاختبار الوزاري",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "اختبار وزاري مؤتمت شامل",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = ErrorRedLight,
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "مؤقت ⏱️",
                                    color = ErrorRed,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "محاكاة نظام أتمتة الاختبارات الوزارية لجميع وحدات الرياضيات مع إظهار خطوات الحل المنطقي فوراً",
                            fontSize = 12.sp,
                            color = TextSecondary,
                            lineHeight = 16.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "بدء",
                        tint = PrimaryBlue
                    )
                }
            }
        }

        // 3. Progressive Training System Selector (Level by Level)
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "نظام التدريب المتدرج",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TrainingModeCard(
                    title = "مستوى الدرس",
                    subtitle = "5 مسائل مركزة",
                    icon = Icons.Default.MenuBook,
                    badgeColor = Color(0xFF3B82F6),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.startQuiz(
                            mode = TrainingMode.LESSON,
                            targetTitle = "تدريب سريع: درس الأعداد المركبة"
                        )
                    }
                )

                TrainingModeCard(
                    title = "مستوى الوحدة",
                    subtitle = "15 مسألة شاملة",
                    icon = Icons.Default.Layers,
                    badgeColor = Color(0xFF10B981),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.startQuiz(
                            mode = TrainingMode.UNIT,
                            targetTitle = "اختبار وحدة النهايات والاشتقاق"
                        )
                    }
                )

                TrainingModeCard(
                    title = "نموذج وزاري",
                    subtitle = "أتمتة كاملة",
                    icon = Icons.Default.School,
                    badgeColor = Color(0xFFF59E0B),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.startQuiz(
                            mode = TrainingMode.MINISTERIAL_EXAM,
                            targetTitle = "نموذج وزاري متكامل 2024"
                        )
                    }
                )
            }
        }

        // 4. Mathematics Branches List (الجبر، الهندسة، التفاضل، التكامل، الاحتمالات)
        item {
            Spacer(modifier = Modifier.height(26.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "فروع الرياضيات (ثالث ثانوي)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                TextButton(onClick = { viewModel.navigateTo(ScreenDestination.TrainingSelection()) }) {
                    Text("عرض كل الوحدات", color = PrimaryBlue, fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(SubjectBranch.values()) { branch ->
            val stat = studentProfile.branchPerformance[branch]
            BranchCardItem(
                branch = branch,
                stat = stat,
                onStartTraining = {
                    viewModel.navigateTo(ScreenDestination.TrainingSelection(branch))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // 5. Gamification / Badges Quick Banner
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .clickable { viewModel.navigateTo(ScreenDestination.Badges) }
                    .testTag("open_badges_banner"),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🏆", fontSize = 32.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "شارات التميز ونظام التحفيز",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF92400E),
                            fontSize = 15.sp
                        )
                        Text(
                            text = "احصل على الشارات البرونزية والفضية والذهبية عند التدريب المستمر وحل النماذج بدقة",
                            color = Color(0xFFB45309),
                            fontSize = 12.sp
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "عرض الشارات",
                        tint = Color(0xFFB45309)
                    )
                }
            }
        }
    }
}

@Composable
fun TrainingModeCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    badgeColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(badgeColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = badgeColor,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BranchCardItem(
    branch: SubjectBranch,
    stat: com.example.data.model.BranchStat?,
    onStartTraining: () -> Unit
) {
    val accuracy = stat?.accuracyPercent ?: 80
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onStartTraining() },
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(branch.color)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = branch.titleAr,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                }

                Surface(
                    color = if (accuracy >= 85) SuccessGreenLight else Color(0xFFFEF3C7),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "دقة الاستيعاب: $accuracy%",
                        color = if (accuracy >= 85) SuccessGreen else Color(0xFFB45309),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = branch.descriptionAr,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { accuracy / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = branch.color,
                trackColor = SurfaceSubtle,
            )
        }
    }
}
