package com.dmb25.jpcompose_practice.presentation.mapper

import com.dmb25.jpcompose_practice.presentation.navigation.Screen

fun Screen.toRoute(): String = when (this) {
    Screen.Home -> "home"
    is Screen.Detail -> "detail/${this.petId}"
}