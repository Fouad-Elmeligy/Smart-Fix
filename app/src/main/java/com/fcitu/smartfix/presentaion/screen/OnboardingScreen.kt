package com.fcitu.smartfix.presentaion.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(
    onStartClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8DCC8)) // اللون البيج
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // العنوان الرئيسي
            Text(
                text = "احجز خدمتك في",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "3 خطوات سهلة",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF5722),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(60.dp))

            // الخطوة الأولى - اختر الخدمة
            OnboardingItem(
                icon = Icons.Default.Search,
                backgroundColor = Color(0xFFFFF3E0),
                iconTint = Color(0xFFFF5722),
                title = "اختر الخدمة",
                description = "ابحث وحدد خدمة الصيانة المنزلية التي\nتحتاجها بكل سهولة."
            )

            Spacer(modifier = Modifier.height(32.dp))

            // الخطوة الثانية - حدد موعدك
            OnboardingItem(
                icon = Icons.Default.CalendarToday,
                backgroundColor = Color(0xFFE3F2FD),
                iconTint = Color(0xFF2196F3),
                title = "حدد موعدك",
                description = "اختر التاريخ والوقت المناسبين لك\nلاستقبال الفني المحترف."
            )

            Spacer(modifier = Modifier.height(32.dp))

            // الخطوة الثالثة - أكد واسترح
            OnboardingItem(
                icon = Icons.Default.CheckCircle,
                backgroundColor = Color(0xFFE8F5E9),
                iconTint = Color(0xFF4CAF50),
                title = "أكد واسترح",
                description = "فني محترف ومعتمد في طريقه إليك\nالآن لإنجاز المهمة."
            )

            Spacer(modifier = Modifier.height(100.dp))

            // زر ابدأ الآن
            Button(
                onClick = onStartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5722)
                )
            ) {
                Text(
                    text = "ابدأ الآن",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun OnboardingItem(
    icon: ImageVector,
    backgroundColor: Color,
    iconTint: Color,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ) {
        // النص
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C2C2C),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                color = Color(0xFF9E9E9E),
                textAlign = TextAlign.End,
                lineHeight = 22.sp
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        // الأيقونة في صندوق ملون
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    MaterialTheme {
        OnboardingScreen()
    }
}