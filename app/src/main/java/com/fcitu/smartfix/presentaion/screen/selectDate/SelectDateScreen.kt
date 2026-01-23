package com.fcitu.smartfix.presentaion.screen.selectDate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fcitu.smartfix.presentaion.screen.selectDate.components.SelectDateScreenScaffold
import com.fcitu.smartfix.presentaion.screen.selectDate.components.BookingStepHeader
import com.fcitu.smartfix.presentaion.screen.selectDate.components.SelectDateTopAppBar
import com.fcitu.smartfix.presentaion.screen.selectDate.components.CalendarCard
import com.fcitu.smartfix.presentaion.screen.selectDate.components.PrimaryActionButton
import com.fcitu.smartfix.presentaion.screen.selectDate.components.TimeSlotGrid
import com.fcitu.smartfix.ui.theme.BookingColors
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun SelectDateScreen(
    onBackClick: () -> Unit,
    onNextClick: (selectedDate: LocalDate, selectedTime: String) -> Unit,
    modifier: Modifier = Modifier
) {
    SelectDateScreenScaffold {
        val today = remember { LocalDate.now() }
        var currentMonth by remember { mutableStateOf(YearMonth.of(today.year, today.month)) }
        var selectedDate by remember { mutableStateOf<LocalDate?>(today) }
        var selectedTime by remember { mutableStateOf<String?>(null) }

        val timeSlots = remember {
            listOf(
                "11:00 ص", "10:00 ص", "09:00 ص",
                "02:00 م", "01:00 م", "12:00 م",
                "05:00 م", "04:00 م", "03:00 م"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            SelectDateTopAppBar(
                title = "حدد الموعد",
                onBackClick = onBackClick
            )

            Spacer(Modifier.height(12.dp))

            BookingStepHeader(
                steps = listOf("تأكيد", "الوقت", "التفاصيل"),
                currentStepIndex = 1 // 0-based: 0=Confirm,1=Time,2=Details
            )

            Spacer(Modifier.height(20.dp))

            CalendarCard(
                month = currentMonth,
                selectedDate = selectedDate,
                onMonthChange = { currentMonth = it },
                onDateSelected = { selectedDate = it }
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "اختر الوقت",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = BookingColors.TextDark,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(Modifier.height(12.dp))

            TimeSlotGrid(
                slots = timeSlots,
                selectedSlot = selectedTime,
                onSlotSelected = { selectedTime = it }
            )

            Spacer(Modifier.weight(1f))

            PrimaryActionButton(
                text = "متابعة",
                enabled = selectedDate != null && selectedTime != null,
                onClick = {
                    onNextClick(
                        selectedDate ?: today,
                        selectedTime.orEmpty()
                    )
                }
            )
        }
    }
}