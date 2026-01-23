package com.fcitu.smartfix.presentaion.screen.selectDate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fcitu.smartfix.ui.theme.BookingColors

@Composable
fun SelectDateTopAppBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Filled.ArrowForward, // RTL -> this visually points left
                contentDescription = null,
                tint = BookingColors.TextDark
            )
        }

        Spacer(Modifier.width(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = BookingColors.TextDark,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun BookingStepHeader(
    steps: List<String>,
    currentStepIndex: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            steps.forEachIndexed { index, _ ->
                val isActive = index <= currentStepIndex
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(
                            if (isActive) BookingColors.Primary
                            else BookingColors.SurfaceSoft.copy(alpha = 0.8f)
                        )
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            steps.forEachIndexed { index, label ->
                val isCurrent = index == currentStepIndex
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = when {
                            isCurrent -> BookingColors.Primary
                            index < currentStepIndex -> BookingColors.TextDark
                            else -> BookingColors.TextSecondary
                        },
                        fontWeight = if (isCurrent) FontWeight.Medium else FontWeight.Normal
                    )
                )
            }
        }
    }
}