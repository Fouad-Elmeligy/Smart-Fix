package com.fcitu.smartfix.presentaion.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import java.util.UUID

/**
 * Booking Confirmation Screen - Fixed Image Deletion
 *
 * To ensure that only the clicked image is deleted, we use a data class
 * with a unique ID (UUID) for each image entry.
 */

// Data class to represent an image with a unique identifier
data class SelectedImage(
    val id: String = UUID.randomUUID().toString(),
    val uri: Uri,
)

// --- Theme Colors ---
val BackgroundColor = Color(0xFFF8F1E9)
val CardBackgroundColor = Color.White
val PrimaryBlue = Color(0xFF0056D2)
val SecondaryBlue = Color(0xFF007AFF)
val PrimaryOrange = Color(0xFFFF4500)
val TextGray = Color(0xFF666666)
val DividerColor = Color(0xFFEEEEEE)

@Composable
fun BookingConfirmationScreen() {
    // State to hold the list of SelectedImage objects
    var selectedImages by remember { mutableStateOf(listOf<SelectedImage>()) }

    // Gallery Launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            if (selectedImages.size < 3) {
                // Add a new SelectedImage with a unique ID
                selectedImages = selectedImages + SelectedImage(uri = it)
            }
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                TopAppBar(onBackClick = { /* Handle back navigation */ })
            },
            bottomBar = { BottomSubmitButton() },
            containerColor = BackgroundColor
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StepProgressBar()
                OrderSummaryCard()

                AttachedImagesCard(
                    images = selectedImages,
                    onAddImageClick = { galleryLauncher.launch("image/*") },
                    onRemoveImageClick = { imageToRemove ->
                        // Remove only the image with the matching unique ID
                        selectedImages = selectedImages.filter { it.id != imageToRemove.id }
                    }
                )
            }
        }
    }
}

@Composable
fun TopAppBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }

        Text(
            text = "تأكيد الحجز",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun StepProgressBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StepIndicator(modifier = Modifier.weight(1f), isActive = true)
            StepIndicator(modifier = Modifier.weight(1f), isActive = true)
            StepIndicator(modifier = Modifier.weight(1f), isActive = true)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("تأكيد", color = PrimaryBlue, fontSize = 12.sp)
            Text("الوقت", color = PrimaryBlue, fontSize = 12.sp)
            Text("التفاصيل", color = PrimaryBlue, fontSize = 12.sp)
        }
    }
}

@Composable
fun StepIndicator(modifier: Modifier, isActive: Boolean) {
    Box(
        modifier = modifier
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(if (isActive) SecondaryBlue else Color.LightGray)
    )
}

@Composable
fun OrderSummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            SectionHeader(title = "ملخص الطلب")
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(label = "الخدمة", value = "تصليح تكييف")
            InfoRow(label = "الموقع", value = "١٢٣ شارع التحرير، الدقي")
            InfoRow(label = "الموعد", value = "٢٥ يوليو، ٢٠٢٤ - ٠٢:٠٠ م")
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = DividerColor, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("المشكلة", color = PrimaryBlue, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "التكييف لا يبرد وبه تسريب مياه من الوحدة الداخلية.",
                color = TextGray, fontSize = 14.sp, lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun AttachedImagesCard(
    images: List<SelectedImage>,
    onAddImageClick: () -> Unit,
    onRemoveImageClick: (SelectedImage) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            SectionHeader(title = "الصور المرفقة")
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(images, key = { it.id }) { image ->
                    ImageItem(image = image, onRemove = { onRemoveImageClick(image) })
                }

                if (images.size < 3) {
                    item {
                        AddImageButton(onClick = onAddImageClick)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageItem(image: SelectedImage, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = rememberAsyncImagePainter(image.uri),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(24.dp)
                .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                .clickable { onRemove() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun AddImageButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.AddAPhoto,
            contentDescription = "Add Photo",
            tint = Color.LightGray,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(20.dp)
                .background(SecondaryBlue, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = PrimaryBlue, fontSize = 14.sp)
        Text(text = value, color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun BottomSubmitButton() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(28.dp)) {
        Button(
            onClick = { /* Handle Submit */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "إرسال الطلب",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "<",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}