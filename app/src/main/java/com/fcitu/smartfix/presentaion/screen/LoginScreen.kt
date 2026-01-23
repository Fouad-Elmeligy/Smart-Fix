package com.fcitu.smartfix.presentaion.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// الألوان المستخدمة في التطبيق
object SmartFixColors {
    val Beige = Color(0xFFE8DCC8)
    val Blue = Color(0xFF0066FF)
    val Orange = Color(0xFFFF5722)
    val Yellow = Color(0xFFFFF59D)
    val White = Color(0xFFFFFFFF)
    val TextGray = Color(0xFF9E9E9E)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartFixColors.Beige)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // العنوان
            Text(
                text = "Welcome to Smart Fix",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = SmartFixColors.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // حقل اسم المستخدم
            Text(
                text = "Username",
                fontSize = 16.sp,
                color = SmartFixColors.TextGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        text = "Enter your email or username",
                        color = SmartFixColors.TextGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Username",
                        tint = SmartFixColors.TextGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = SmartFixColors.White,
                    focusedContainerColor = SmartFixColors.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = SmartFixColors.Blue
                )
            )

            // حقل كلمة المرور
            Text(
                text = "Password",
                fontSize = 16.sp,
                color = SmartFixColors.TextGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Enter your password",
                        color = SmartFixColors.TextGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = SmartFixColors.TextGray
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = SmartFixColors.Orange
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = SmartFixColors.White,
                    focusedContainerColor = SmartFixColors.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = SmartFixColors.Blue
                )
            )

            // نسيت كلمة المرور
            TextButton(
                onClick = { /* Handle forgot password */ },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Forgot Password?",
                    color = SmartFixColors.TextGray,
                    fontSize = 14.sp
                )
            }

            // زر تسجيل الدخول
            Button(
                onClick = { /* Handle login */ },
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SmartFixColors.Blue
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }



            // زر إنشاء حساب جديد
            Button(
                onClick = { /* Handle create account */ },
                modifier = Modifier
                    .width(230.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SmartFixColors.Orange
                )
            ) {
                Text(
                    text = "Create a New Account",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// Preview للشاشة
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}