package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BurhanViewModel
import com.example.data.ScreenDestination
import com.example.data.model.Badge
import com.example.data.model.BadgeTier
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgesScreen(
    viewModel: BurhanViewModel,
    modifier: Modifier = Modifier
) {
    val studentProfile by viewModel.studentProfile.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "شارات التميز ونظام الألعاب",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(ScreenDestination.Home) },
                        modifier = Modifier.testTag("badges_back_home_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
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
            // Gamification Overview Hero
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(GoldMedal),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "👑", fontSize = 28.sp)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = "مستواك: ${studentProfile.rankTitle}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "رصيد النقاط: ${studentProfile.totalXp} XP | سلسلة ${studentProfile.streakDays} أيام 🔥",
                                color = Color(0xFF94A3B8),
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "الشارات المفتوحة: ${studentProfile.badges.count { it.isUnlocked }} من أصل ${studentProfile.badges.size}",
                                color = Color(0xFF38BDF8),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            // Bronze Badges
            item {
                BadgeSectionHeader(title = "الشارات البرونزية (المستوى الأول)", color = BronzeMedal, symbol = "🥉")
            }
            items(studentProfile.badges.filter { it.tier == BadgeTier.BRONZE }) { badge ->
                BadgeItemCard(badge = badge)
            }

            // Silver Badges
            item {
                Spacer(modifier = Modifier.height(8.dp))
                BadgeSectionHeader(title = "الشارات الفضية (المستوى المتقدم)", color = SilverMedal, symbol = "🥈")
            }
            items(studentProfile.badges.filter { it.tier == BadgeTier.SILVER }) { badge ->
                BadgeItemCard(badge = badge)
            }

            // Gold Badges
            item {
                Spacer(modifier = Modifier.height(8.dp))
                BadgeSectionHeader(title = "الشارات الذهبية (أوائل الجمهورية)", color = GoldMedal, symbol = "🥇")
            }
            items(studentProfile.badges.filter { it.tier == BadgeTier.GOLD }) { badge ->
                BadgeItemCard(badge = badge)
            }
        }
    }
}

@Composable
fun BadgeSectionHeader(title: String, color: Color, symbol: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = symbol, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = TextPrimary
        )
    }
}

@Composable
fun BadgeItemCard(badge: Badge) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (badge.isUnlocked) SurfaceCard else Color(0xFFF1F5F9)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (badge.isUnlocked) Color(badge.tier.colorHex).copy(alpha = 0.4f) else BorderLight
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (badge.isUnlocked) 2.dp else 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(
                        if (badge.isUnlocked) Color(badge.tier.colorHex).copy(alpha = 0.15f) else Color(0xFFE2E8F0)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (badge.isUnlocked) badge.iconSymbol else "🔒",
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = badge.titleAr,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = if (badge.isUnlocked) TextPrimary else TextMuted
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        color = Color(badge.tier.colorHex).copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = badge.tier.titleAr,
                            color = Color(badge.tier.colorHex),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = badge.descriptionAr,
                    fontSize = 12.sp,
                    color = if (badge.isUnlocked) TextSecondary else TextMuted,
                    lineHeight = 16.sp
                )
            }

            if (badge.isUnlocked) {
                Surface(
                    color = SuccessGreenLight,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "مكتملة ✅",
                        color = SuccessGreen,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            } else {
                Text(
                    text = "مغلقة",
                    color = TextMuted,
                    fontSize = 11.sp
                )
            }
        }
    }
}
