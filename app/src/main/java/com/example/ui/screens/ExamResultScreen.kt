package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.data.model.ExamResult
import com.example.data.model.Question
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamResultScreen(
    result: ExamResult,
    viewModel: BurhanViewModel,
    modifier: Modifier = Modifier
) {
    val percentage = if (result.totalQuestions > 0) (result.correctCount * 100) / result.totalQuestions else 0
    val optionLabels = listOf("أ", "ب", "ج", "د")
    var expandedQuestionIndex by remember { mutableStateOf<Int?>(null) }
    var selectedQuestionForDialog by remember { mutableStateOf<Question?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "تقرير نتيجة الاختبار الوزاري",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceWhite)
            )
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
            // 1. Result Header Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = PrimaryNavy)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = when {
                                percentage >= 90 -> "ممتاز جداً! أداء وزاري متميز 🌟"
                                percentage >= 75 -> "جيد جداً! استمر في التدريب 💪"
                                else -> "تحتاج لمراجعة بعض القواعد والخطوات 📚"
                            },
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        percentage >= 85 -> SuccessGreen
                                        percentage >= 60 -> GoldMedal
                                        else -> ErrorRed
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$percentage%",
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Text(
                                    text = "الدرجة",
                                    color = Color(0xFFF1F5F9),
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Stats Summary Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ResultStatBox(
                                label = "صحيحة",
                                value = "${result.correctCount}",
                                color = SuccessGreen
                            )
                            ResultStatBox(
                                label = "خاطئة",
                                value = "${result.wrongCount}",
                                color = ErrorRed
                            )
                            ResultStatBox(
                                label = "الوقت",
                                value = "${result.timeSpentSeconds / 60} دقيقة",
                                color = Color(0xFF38BDF8)
                            )
                            ResultStatBox(
                                label = "النقاط",
                                value = "+${result.earnedXp} XP",
                                color = GoldMedal
                            )
                        }
                    }
                }
            }

            // 2. Action Buttons (Retry mistakes, Dashboard, Home)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { viewModel.navigateTo(ScreenDestination.Dashboard) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("result_go_dashboard_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Assessment, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("لوحة الأداء", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }

                    OutlinedButton(
                        onClick = { viewModel.navigateTo(ScreenDestination.Home) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("result_go_home_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("الرئيسية", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }

            // 3. Question Review List with Steps
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "مراجعة الأسئلة وخطوات الحل الرياضي المنطقي:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = TextPrimary
                )
            }

            itemsIndexed(result.questions) { index, question ->
                val userChoice = result.userAnswers[index]
                val isCorrect = userChoice == question.correctAnswerIndex
                val isExpanded = expandedQuestionIndex == index

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            expandedQuestionIndex = if (isExpanded) null else index
                        },
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isCorrect) SuccessGreenLight else ErrorRedLight
                    ),
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
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                    contentDescription = if (isCorrect) "صحيح" else "خاطئ",
                                    tint = if (isCorrect) SuccessGreen else ErrorRed,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "سؤال ${index + 1}: ${question.branch.titleAr}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = TextPrimary
                                )
                            }

                            TextButton(
                                onClick = { selectedQuestionForDialog = question },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = "عرض البرهان",
                                    tint = AccentTeal,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "عرض البرهان",
                                    color = AccentTeal,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = question.questionText,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                color = if (isCorrect) SuccessGreenLight else ErrorRedLight,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "إجابتك: " + (if (userChoice != null) "${optionLabels.getOrElse(userChoice) { "" }} - ${question.options.getOrElse(userChoice) { "" }}" else "لم تجب"),
                                    color = if (isCorrect) Color(0xFF065F46) else Color(0xFF991B1B),
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                    maxLines = 1
                                )
                            }

                            if (!isCorrect) {
                                Surface(
                                    color = SuccessGreenLight,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "الصحيحة: ${optionLabels.getOrElse(question.correctAnswerIndex) { "" }} - ${question.options.getOrElse(question.correctAnswerIndex) { "" }}",
                                        color = Color(0xFF065F46),
                                        fontSize = 11.sp,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    selectedQuestionForDialog?.let { q ->
        StepByStepExplanationDialog(
            question = q,
            onDismiss = { selectedQuestionForDialog = null }
        )
    }
}

@Composable
fun ResultStatBox(
    label: String,
    value: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = label,
            color = Color(0xFF94A3B8),
            fontSize = 11.sp
        )
    }
}
