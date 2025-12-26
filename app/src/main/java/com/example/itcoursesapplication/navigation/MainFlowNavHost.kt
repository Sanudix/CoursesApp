package com.example.itcoursesapplication.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.account.AccountScreen
import com.example.favourites.navigation.FavoritesFlowNavHost
import com.example.itcoursesapplication.R
import com.example.main.navigation.HomeFlowNavHost
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainFlowNavHost() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xff151515),
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xff24252A)
            ) {
                AppDestinations.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = destination == currentDestination,
                        onClick = { currentDestination = destination },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    if (destination == currentDestination) destination.selectedIconResId
                                    else destination.iconResId
                                ),
                                contentDescription = destination.label,
                                tint = if (destination == currentDestination) {
                                    Color(0xff12B956)
                                } else {
                                    Color(0xffF2F2F3)
                                }
                            )
                        },
                        label = {
                            Text(
                                text = destination.label,
                                fontFamily = FontFamily(Font(R.font.roboto)),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (destination == currentDestination) {
                                    Color(0xff12B956)
                                } else {
                                    Color(0xffF2F2F3)
                                }
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color(0xff32333A)
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