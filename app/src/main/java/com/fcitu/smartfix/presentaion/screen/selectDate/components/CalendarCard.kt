package com.fcitu.smartfix.presentaion.screen.selectDate.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fcitu.smartfix.ui.theme.BookingColors
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarCard(
    month: YearMonth,
    selectedDate: LocalDate?,
    onMonthChange: (YearMonth) -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = month.atDay(1)
    val daysInMonth = month.lengthOfMonth()
    val firstDayOfWeekIndex = dayOfWeekToColumnIndex(firstDayOfMonth.dayOfWeek)
    val today = LocalDate.now()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 320.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // Month header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onMonthChange(month.minusMonths(1)) }) {
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = null,
                        tint = BookingColors.TextDark
                    )
                }

                Text(
                    text = "${
                        month.month.getDisplayName(
                            TextStyle.FULL,
                            Locale("ar")
                        )
                    } ${month.year}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = BookingColors.TextDark,
                        fontWeight = FontWeight.Bold
                    )
                )

                IconButton(onClick = { onMonthChange(month.plusMonths(1)) }) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = null,
                        tint = BookingColors.TextDark
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Weekday row (starting Saturday .. Friday to match screenshot)
            val weekDays = listOf("س", "ح", "ن", "ث", "ع", "خ", "ج")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                weekDays.forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = BookingColors.TextSecondary,
                            fontWeight = FontWeight.Medium
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(32.dp)
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Days grid
            val totalCells = firstDayOfWeekIndex + daysInMonth
            val rows = (totalCells + 6) / 7

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                var dayNumber = 1
                repeat(rows) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(7) { columnIndex ->
                            val cellIndex = it * 7 + columnIndex
                            if (cellIndex < firstDayOfWeekIndex || dayNumber > daysInMonth) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            } else {
                                val date = month.atDay(dayNumber)
                                val isSelected = selectedDate == date
                                val isPast = date.isBefore(today)

                                DayCell(
                                    day = dayNumber,
                                    isSelected = isSelected,
                                    isEnabled = !isPast,
                                    onClick = { if (!isPast) onDateSelected(date) }
                                )
                                dayNumber++
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DayCell(
    day: Int,
    isSelected: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue = when {
            isSelected -> BookingColors.Primary
            else -> Color.Transparent
        },
        label = "dayBg"
    )

    val textColor = when {
        !isEnabled -> BookingColors.TextSecondary.copy(alpha = 0.4f)
        isSelected -> Color.White
        else -> BookingColors.TextDark
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(bgColor)
            .clickable(enabled = isEnabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = textColor,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
        )
    }
}

private fun dayOfWeekToColumnIndex(dayOfWeek: DayOfWeek): Int {
    return when (dayOfWeek) {
        DayOfWeek.SATURDAY -> 0
        DayOfWeek.SUNDAY -> 1
        DayOfWeek.MONDAY -> 2
        DayOfWeek.TUESDAY -> 3
        DayOfWeek.WEDNESDAY -> 4
        DayOfWeek.THURSDAY -> 5
        DayOfWeek.FRIDAY -> 6
    }
}