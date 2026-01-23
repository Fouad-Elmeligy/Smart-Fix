package com.fcitu.smartfix.presentaion.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// الألوان الدقيقة من الصورة
object AppColors {
    val Background = Color(0xFFE8DDD3) // البيج الفاتح
    val CardWhite = Color(0xFFFFFFFF)
    val PrimaryBlue = Color(0xFF1976D2)
    val TextDark = Color(0xFF2C3E50)
    val TextGray = Color(0xFF95A5A6)

    // ألوان البانر
    val BannerBg = Color(0xFF766A5F)
    val OrangeAccent = Color(0xFFFF6B35)

    // ألوان الخدمات - من الصورة
    val PlumbingIcon = Color(0xFF2196F3)
    val PlumbingBg = Color(0xFFE3F2FD)

    val ElectricityIcon = Color(0xFFFF9800)
    val ElectricityBg = Color(0xFFFFF3E0)

    val CarpentryIcon = Color(0xFF795548)
    val CarpentryBg = Color(0xFFFFF8E1)

    val ACIcon = Color(0xFF00BCD4)
    val ACBg = Color(0xFFE0F7FA)

    val RepairIcon = Color(0xFF607D8B)
    val RepairBg = Color(0xFFECEFF1)

    val PaintingIcon = Color(0xFFE91E63)
    val PaintingBg = Color(0xFFFCE4EC)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        containerColor = AppColors.Background,
        bottomBar = { BottomNav() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Header()

            Spacer(modifier = Modifier.height(16.dp))

            // Search
            SearchSection()

            Spacer(modifier = Modifier.height(20.dp))

            // Banner
            DiscountBanner()

            Spacer(modifier = Modifier.height(20.dp))

            // Services
            ServicesSection()

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bell Icon
        Surface(
            modifier = Modifier.size(52.dp),
            shape = RoundedCornerShape(14.dp),
            color = AppColors.CardWhite,
            shadowElevation = 2.dp
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    tint = Color(0xFF34495E),
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        // Location Text
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "موقعك الحالي",
                fontSize = 11.sp,
                color = AppColors.TextGray
            )
            Text(
                text = "القاهرة، مصر",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextDark
            )

        }

        // Location Icon
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = null,
            tint = AppColors.PrimaryBlue,
            modifier = Modifier.size(26.dp)
        )
    }
}

@Composable
fun SearchSection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        color = AppColors.CardWhite,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Filter Icon
            Icon(
                imageVector = Icons.Outlined.Tune,
                contentDescription = null,
                tint = AppColors.TextGray,
                modifier = Modifier.size(20.dp)
            )

            // Search Text
            Text(
                text = "ما الخدمة التي تحتاجها اليوم؟",
                fontSize = 13.sp,
                color = AppColors.TextGray,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )

            // Search Icon
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = AppColors.TextGray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun DiscountBanner() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(185.dp),
        shape = RoundedCornerShape(18.dp),
        color = AppColors.BannerBg
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // خصم حصري
                    Text(
                        text = "خصم حصري",
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // توفير 30%
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "توفير",
                            fontSize = 17.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = AppColors.OrangeAccent
                        ) {
                            Text(
                                text = "30%",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Text(
                        text = "استمتع بخصم حصري على كافة\nخدمات السباكة طوال الأسبوع",
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.95f),
                        textAlign = TextAlign.Center,
                        lineHeight = 16.sp
                    )
                }

                // Button
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "احجز الآن",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2C3E50)
                    )
                }
            }
        }
    }
}

@Composable
fun ServicesSection() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Row 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            ServiceCard(
                title = "سباكة",
                icon = Icons.Outlined.Plumbing,
                iconColor = AppColors.PlumbingIcon,
                bgColor = AppColors.PlumbingBg,
                modifier = Modifier.weight(1f)
            )
            ServiceCard(
                title = "كهرباء",
                icon = Icons.Outlined.Lightbulb,
                iconColor = AppColors.ElectricityIcon,
                bgColor = AppColors.ElectricityBg,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            ServiceCard(
                title = "نجارة",
                icon = Icons.Outlined.Handyman,
                iconColor = AppColors.CarpentryIcon,
                bgColor = AppColors.CarpentryBg,
                modifier = Modifier.weight(1f)
            )
            ServiceCard(
                title = "تكييف",
                icon = Icons.Outlined.AcUnit,
                iconColor = AppColors.ACIcon,
                bgColor = AppColors.ACBg,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            ServiceCard(
                title = "إصلاح أجهزة",
                icon = Icons.Outlined.Build,
                iconColor = AppColors.RepairIcon,
                bgColor = AppColors.RepairBg,
                modifier = Modifier.weight(1f)
            )
            ServiceCard(
                title = "نقاشة",
                icon = Icons.Outlined.Brush,
                iconColor = AppColors.PaintingIcon,
                bgColor = AppColors.PaintingBg,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ServiceCard(
    title: String,
    icon: ImageVector,
    iconColor: Color,
    bgColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = {},
        modifier = modifier
            .height(140.dp),
        shape = RoundedCornerShape(18.dp),
        color = AppColors.CardWhite,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon Background
            Surface(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(14.dp),
                color = bgColor
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Title
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColors.TextDark
            )
        }
    }
}

@Composable
fun BottomNav() {
    NavigationBar(
        containerColor = AppColors.CardWhite,
        tonalElevation = 4.dp,
        modifier = Modifier.height(70.dp)
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    modifier = Modifier.size(23.dp)
                )
            },
            label = {
                Text(
                    text = "الرئيسية",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppColors.PrimaryBlue,
                selectedTextColor = AppColors.PrimaryBlue,
                unselectedIconColor = AppColors.TextGray,
                unselectedTextColor = AppColors.TextGray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            },
            label = {
                Text(
                    text = "طلباتي",
                    fontSize = 11.sp
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppColors.PrimaryBlue,
                selectedTextColor = AppColors.PrimaryBlue,
                unselectedIconColor = AppColors.TextGray,
                unselectedTextColor = AppColors.TextGray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Chat,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            },
            label = {
                Text(
                    text = "المحادثات",
                    fontSize = 11.sp
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppColors.PrimaryBlue,
                selectedTextColor = AppColors.PrimaryBlue,
                unselectedIconColor = AppColors.TextGray,
                unselectedTextColor = AppColors.TextGray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            },
            label = {
                Text(
                    text = "حسابي",
                    fontSize = 11.sp
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppColors.PrimaryBlue,
                selectedTextColor = AppColors.PrimaryBlue,
                unselectedIconColor = AppColors.TextGray,
                unselectedTextColor = AppColors.TextGray,
                indicatorColor = Color.Transparent
            )
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun home() {
    HomeScreen()

}