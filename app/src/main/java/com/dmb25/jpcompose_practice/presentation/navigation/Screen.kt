package com.dmb25.jpcompose_practice.presentation.navigation

sealed interface Screen {

    data object Home : Screen

    data class Detail(val petId: Int) : Screen
}