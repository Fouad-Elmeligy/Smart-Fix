package com.fcitu.smartfix.presentaion.screen.successSentOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fcitu.smartfix.presentaion.screen.successSentOrder.components.PrimarySuccessButton
import com.fcitu.smartfix.presentaion.screen.successSentOrder.components.SecondarySuccessButton
import com.fcitu.smartfix.presentaion.screen.successSentOrder.components.SuccessIconSection
import com.fcitu.smartfix.presentaion.screen.successSentOrder.components.SuccessTextSection
import com.fcitu.smartfix.ui.theme.SuccessColors

@Composable
fun OrderSentScreen(
    onTrackOrdersClick: () -> Unit,
    onBackHomeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SuccessColors.Background)
            .systemBarsPadding()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(Modifier.height(32.dp))

        SuccessIconSection()

        SuccessTextSection(modifier = Modifier.padding(vertical = 24.dp))

        Spacer(modifier.weight(1f))

        PrimarySuccessButton(
            text = "متابعة الطلبات",
            onClick = onTrackOrdersClick, // TODO: Nav to orders tracking screen (coming soon)
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SecondarySuccessButton(
            text = "العودة للرئيسية",
            onClick = onBackHomeClick,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}