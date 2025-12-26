package com.example.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.extensions.openUrl

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isFormValid by remember(email, password) {
        derivedStateOf {
            email.isNotBlank() &&
                    password.isNotBlank() &&
                    isValidEmail(email)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff151515))
            .padding(top = 140.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Вход",
            modifier = Modifier,
            color = Color(0xffF2F2F3),
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(28.dp))

        Text(
            text = "Email",
            color = Color(0xffF2F2F3),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { newText ->
                val filteredText = newText.replace("[а-яА-ЯёЁ]".toRegex(), "")
                email = filteredText
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None,
            ),
            placeholder = {
                Text(
                    "example@gmail.com",
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    color = Color(0xffF2F2F3),
                    modifier = Modifier.alpha(0.5f)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0x80F2F2F3),
                focusedTextColor = Color(0xffF2F2F3),
                unfocusedTextColor = Color(0xffF2F2F3)
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xff32333A))
                .fillMaxWidth()
        )

        Spacer(Modifier.height(28.dp))

        Text(
            text = "Пароль",
            color = Color(0xffF2F2F3),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { newText -> password = newText },
            placeholder = {
                Text(
                    "Введите пароль",
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    color = Color(0xffF2F2F3),
                    modifier = Modifier.alpha(0.5f)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None,
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0x80F2F2F3),
                focusedTextColor = Color(0xffF2F2F3),
                unfocusedTextColor = Color(0xffF2F2F3)
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xff32333A))
                .fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                if (isFormValid) {
                    onLoginSuccess()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff12B956),
                disabledContainerColor = Color(0xff12B956).copy(alpha = 0.5f)
            ),
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Вход",
                color = Color(0xffF2F2F3),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Нету аккаунта?",
                color = Color(0xffF2F2F3),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.width(4.dp))

            Text(
                text = "Регистрация",
                color = Color(0xff12B956),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
        }

        Text(
            text = "Забыл пароль",
            color = Color(0xff12B956),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xff4D555E))
        )

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    context.openUrl("https://vk.com/")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(156.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(Color(0xff2683ED))
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_vk),
                    contentDescription = "Войти через VK",
                    tint = Color.White
                )
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = {
                    context.openUrl("https://ok.ru/")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .height(40.dp)
                    .width(156.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xffF98509),
                                Color(0xffF95D00)
                            )
                        )
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_ok),
                    contentDescription = "Войти через Одноклассники",
                    tint = Color.White
                )
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return emailRegex.matches(email)
}