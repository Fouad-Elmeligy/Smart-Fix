package com.fcitu.smartfix.presentaion.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {

    @Serializable
    data object Splash : Destination

    @Serializable
    data object Onboarding : Destination

    @Serializable
    data object Login : Destination

    @Serializable
    data object Home : Destination

    @Serializable
    data class ServiceDetails(
        val serviceName: String
    ) : Destination

    @Serializable
    data class SelectDate(
        val serviceName: String
    ) : Destination

    @Serializable
    data class BookingConfirmation(
        val serviceName: String,
        val date: String,
        val time: String
    ) : Destination

    @Serializable
    data object OrderSuccess : Destination
}