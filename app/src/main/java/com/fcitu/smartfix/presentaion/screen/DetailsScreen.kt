package com.fcitu.smartfix

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage // Note: Requires coil-compose dependency

/**
 * Custom Service Request Screen - Version 2
 * Added Image Picking functionality from Gallery.
 */
@Composable
fun ServiceDetailsScreen(
    serviceName: String, // add this
    onNext: (String) -> Unit
) {
    var problemDescription by remember { mutableStateOf("") }

    // State to hold selected image URIs
    var selectedImages by remember { mutableStateOf<List<Uri>>(emptyList()) }

    // Image Picker Launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            if (selectedImages.size < 3) {
                selectedImages = selectedImages + it
            }
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(Color(0xFFFDF7F2))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                CustomTopBar(title = "طلب خدمة جديدة")

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    CustomStepIndicator(currentStep = 0)

                    Spacer(modifier = Modifier.height(32.dp))

                    CustomSectionHeader(title = "صف المشكلة")

                    CustomTextArea(
                        value = problemDescription,
                        onValueChange = { problemDescription = it },
                        placeholder = "يرجى وصف المشكلة بالتفصيل لمساعدة الفني على فهمها بشكل أفضل..."
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Image Upload Section Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomSectionHeader(title = "إضافة صور (اختياري)")
                        Text(
                            text = "حتى 3 صور (${selectedImages.size}/3)",
                            style = TextStyle(color = Color.Gray, fontSize = 12.sp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Image Upload Box or Selected Images Preview
                    if (selectedImages.isEmpty()) {
                        CustomImageUploadBox(onClick = { galleryLauncher.launch("image/*") })
                    } else {
                        Column {
                            // Display selected images in a row
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(selectedImages) { uri ->
                                    SelectedImageItem(
                                        uri = uri,
                                        onRemove = { selectedImages = selectedImages - uri }
                                    )
                                }

                                // Show "Add More" if less than 3
                                if (selectedImages.size < 3) {
                                    item {
                                        AddMoreImageButton(onClick = { galleryLauncher.launch("image/*") })
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomInfoBox(text = "توضيح المشكلة بالصور يساعدنا على اختيار الفني المناسب وتقدير التكلفة بدقة.")

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CustomButton(
                    text = "متابعة",
                    onClick = {onNext(serviceName)}
                )
            }
        }
    }
}

@Composable
fun SelectedImageItem(uri: Uri, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFDDE2E5), RoundedCornerShape(12.dp))
    ) {
        // Image Preview (Using Coil)
        AsyncImage(
            model = uri,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Remove Button
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(24.dp)
                .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                .clickable { onRemove() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun AddMoreImageButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFDDE2E5), RoundedCornerShape(12.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = Icons.Default.AddAPhoto,
            contentDescription = "Add More",
            colorFilter = ColorFilter.tint(Color(0xFF0052CC)),
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun CustomImageUploadBox(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFDDE2E5),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFE8F0FE), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = Icons.Default.AddAPhoto,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0xFF0052CC)),
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "أضف صور للمشكلة",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "من الكاميرا أو معرض الصور",
                style = TextStyle(color = Color.Gray, fontSize = 12.sp)
            )
        }
    }
}

// --- Previous Components Kept for Completeness ---

@Composable
fun CustomTopBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable { },
            colorFilter = ColorFilter.tint(Color.Black)
        )
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun CustomStepIndicator(currentStep: Int) {
    val steps = listOf("التفاصيل", "الوقت", "تأكيد")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        steps.forEachIndexed { index, title ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (index <= currentStep) Color(0xFF0052CC) else Color(0xFFDDE2E5)
                        )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = if (index == currentStep) Color(0xFF0052CC) else Color.Gray,
                        fontWeight = if (index == currentStep) FontWeight.Bold else FontWeight.Normal
                    )
                )
            }
        }
    }
}

@Composable
fun CustomSectionHeader(title: String) {
    Text(
        text = title,
        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun CustomTextArea(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        if (value.isEmpty()) Text(
            text = placeholder,
            style = TextStyle(color = Color.Gray, fontSize = 14.sp)
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(fontSize = 14.sp, color = Color.Black)
        )
    }
}

@Composable
fun CustomInfoBox(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F0FE), RoundedCornerShape(12.dp))
            .padding(12.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFF0052CC)),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = TextStyle(fontSize = 12.sp, color = Color.Black, lineHeight = 18.sp)
        )
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFFFF4D17), RoundedCornerShape(28.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        )
    }
}

@Composable
fun Text(text: String, modifier: Modifier = Modifier, style: TextStyle = TextStyle.Default) {
    androidx.compose.foundation.text.BasicText(text = text, modifier = modifier, style = style)
}