package com.dmb25.jpcompose_practice.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmb25.jpcompose_practice.domain.usecase.GetPetByIdUseCase
import com.dmb25.jpcompose_practice.domain.usecase.UpdatePetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val updatePetUseCase: UpdatePetUseCase,
    savedStateHandle: SavedStateHandle,
    ): ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    private val petId: Int =
        checkNotNull(savedStateHandle["petId"])

    init {
        getPetById(petId)
    }

    fun getPetById(id: Int) {
        viewModelScope.launch {
            if (getPetByIdUseCase(id) == null) {
                _uiState.value = DetailUiState.Error("Pet not found")
            }else{
                _uiState.value = DetailUiState.Success(getPetByIdUseCase(id)!!)
            }
        }
    }
}