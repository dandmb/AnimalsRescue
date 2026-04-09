package com.dmb25.jpcompose_practice.presentation.ui.home

import com.dmb25.jpcompose_practice.domain.model.Pet

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val pets: List<Pet>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}