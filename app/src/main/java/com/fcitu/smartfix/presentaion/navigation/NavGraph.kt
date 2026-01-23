package com.fcitu.smartfix.presentaion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fcitu.smartfix.ServiceDetailsScreen
import com.fcitu.smartfix.presentaion.screen.BookingConfirmationScreen
import com.fcitu.smartfix.presentaion.screen.HomeScreen
import com.fcitu.smartfix.presentaion.screen.LoginScreen
import com.fcitu.smartfix.presentaion.screen.OnboardingScreen
import com.fcitu.smartfix.presentaion.screen.SplashScreen
import com.fcitu.smartfix.presentaion.screen.selectDate.SelectDateScreen
import com.fcitu.smartfix.presentaion.screen.successSentOrder.OrderSentScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Splash
    ) {

        composable<Destination.Splash> {
            SplashScreen {
                navController.navigate(Destination.Onboarding) {
                    popUpTo<Destination.Splash> { inclusive = true }
                }
            }
        }

        composable<Destination.Onboarding> {
            OnboardingScreen {
                navController.navigate(Destination.Login)
            }
        }

        composable<Destination.Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Destination.Home) {
                        popUpTo<Destination.Login> { inclusive = true }
                    }
                }
            )
        }

        composable<Destination.Home> {
            HomeScreen(
                onServiceClick = { service ->
                    navController.navigate(
                        Destination.ServiceDetails(service)
                    )
                },
            )
        }

        composable<Destination.ServiceDetails> { entry ->
            val args = entry.toRoute<Destination.ServiceDetails>()

            ServiceDetailsScreen(
                serviceName = args.serviceName,
                onNext = { name ->
                    navController.navigate(Destination.SelectDate(name))
                }
            )
        }

        composable<Destination.SelectDate> { entry ->
            val args = entry.toRoute<Destination.SelectDate>() // args.serviceName

            SelectDateScreen(
                onBackClick = { navController.popBackStack() },
                onNextClick = { date, time ->
                    // Navigate to BookingConfirmation
                    navController.navigate(
                        Destination.BookingConfirmation(
                            serviceName = args.serviceName,
                            date = date.toString(),
                            time = time
                        )
                    )
                }
            )
        }

        composable<Destination.BookingConfirmation> { entry ->
            val args = entry.toRoute<Destination.BookingConfirmation>()

            BookingConfirmationScreen(
                onBackClick = { navController.popBackStack() },
                onSubmitClick = {
                    navController.navigate(Destination.OrderSuccess) {
                        popUpTo<Destination.Home>()
                    }
                }
            )
        }

        composable<Destination.OrderSuccess> {
            OrderSentScreen(
                onTrackOrdersClick = { /* TODO */ },
                onBackHomeClick = {
                    navController.navigate(Destination.Home) {
                        popUpTo<Destination.Home> { inclusive = true }
                    }
                }
            )
        }

    }
}