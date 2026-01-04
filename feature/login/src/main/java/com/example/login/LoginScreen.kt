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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.presentation.extensions.openUrl
import com.example.theme.ITCoursesApplicationTheme
import com.example.theme.dimensions

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val colors = ITCoursesApplicationTheme.colors
    val dimensions = ITCoursesApplicationTheme.dimensions

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
            .background(color = colors.background)
            .padding(top = dimensions.columnHeight)
            .padding(horizontal = dimensions.paddingXMedium)
    ) {
        Text(
            text = stringResource(R.string.login_title),
            modifier = Modifier,
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(dimensions.paddingXLarge))

        Text(
            text = stringResource(R.string.login_email_label),
            color = colors.textPrimary,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(dimensions.paddingSmall))

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
                    text = stringResource(R.string.login_email_placeholder),
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = colors.cursorColor,
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(dimensions.cornerRadiusXLarge))
                .background(colors.inputBackground)
                .fillMaxWidth()
        )

        Spacer(Modifier.height(dimensions.paddingXLarge))

        Text(
            text = stringResource(R.string.login_password_label),
            color = colors.textPrimary,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(dimensions.paddingSmall))

        TextField(
            value = password,
            onValueChange = { newText -> password = newText },
            placeholder = {
                Text(
                    text = stringResource(R.string.login_password_placeholder),
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.textSecondary
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
                cursorColor = colors.cursorColor,
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(dimensions.cornerRadiusXLarge))
                .background(colors.inputBackground)
                .fillMaxWidth()
        )

        Spacer(Modifier.height(dimensions.paddingLarge))

        Button(
            onClick = {
                if (isFormValid) {
                    onLoginSuccess()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.accent,
                disabledContainerColor = colors.buttonDisabled
            ),
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.login_button),
                color = colors.textPrimary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.paddingXMedium),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.login_no_account),
                color = colors.textPrimary,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.width(dimensions.paddingXXSmall))

            Text(
                text = stringResource(R.string.login_register),
                color = colors.accent,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start
            )
        }

        Text(
            text = stringResource(R.string.login_forgot_password),
            color = colors.accent,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.paddingSmall),
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(dimensions.paddingXXLarge))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensions.dividerHeight)
                .background(colors.divider)
        )

        Spacer(Modifier.height(dimensions.paddingXXLarge))

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
                    .width(dimensions.buttonSpecialHeight)
                    .clip(shape = RoundedCornerShape(dimensions.cornerRadiusXLarge))
                    .background(colors.vkBlue)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_vk),
                    contentDescription = stringResource(R.string.login_log_in_vk),
                    tint = Color.White
                )
            }

            Spacer(Modifier.width(dimensions.paddingXMedium))

            Button(
                onClick = {
                    context.openUrl("https://ok.ru/")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .height(dimensions.buttonHeightSmall)
                    .width(dimensions.buttonSpecialHeight)
                    .clip(shape = RoundedCornerShape(dimensions.cornerRadiusXLarge))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                colors.okOrangeStart,
                                colors.okOrangeEnd
                            )
                        )
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_ok),
                    contentDescription = stringResource(R.string.login_log_in_ok),
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