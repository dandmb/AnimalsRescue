package com.dmb25.jpcompose_practice.presentation.ui.detail

import com.dmb25.jpcompose_practice.domain.model.Pet

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val pet: Pet) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}