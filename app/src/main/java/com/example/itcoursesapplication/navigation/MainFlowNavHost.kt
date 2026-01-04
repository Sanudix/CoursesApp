package com.example.itcoursesapplication.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.example.account.AccountScreen
import com.example.favourites.navigation.FavoritesFlowNavHost
import com.example.main.navigation.HomeFlowNavHost
import com.example.theme.ITCoursesApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainFlowNavHost() {
    val colors = ITCoursesApplicationTheme.colors

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colors.background,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = colors.cardBackground
            ) {
                AppDestinations.entries.forEach { destination ->
                    val isSelected = destination == currentDestination

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentDestination = destination },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    if (isSelected) destination.selectedIconResId
                                    else destination.iconResId
                                ),
                                contentDescription = stringResource(destination.labelResId),
                                tint = if (isSelected) colors.accent else colors.textPrimary
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(destination.labelResId),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) colors.accent else colors.textPrimary
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = colors.inputBackground
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentDestination) {
                AppDestinations.HOME -> {
                    HomeFlowNavHost()
                }

                AppDestinations.FAVORITES -> {
                    rememberNavController()
                    FavoritesFlowNavHost()
                }

                AppDestinations.PROFILE -> AccountScreen()
            }
        }
    }
}