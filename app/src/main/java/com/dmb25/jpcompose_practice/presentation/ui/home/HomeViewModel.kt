package com.dmb25.jpcompose_practice.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmb25.jpcompose_practice.domain.usecase.DeletePetByIdUseCase
import com.dmb25.jpcompose_practice.domain.usecase.GetAllPetsUseCase
import com.dmb25.jpcompose_practice.domain.usecase.GetPetByIdUseCase
import com.dmb25.jpcompose_practice.domain.usecase.UpdatePetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllPetsUseCase: GetAllPetsUseCase,
    private val deletePetById: DeletePetByIdUseCase,
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val updatePetUseCase: UpdatePetUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getAllPets()
    }

    fun getAllPets() {
        viewModelScope.launch {
            getAllPetsUseCase().collect { pets ->
                try {
                    if (pets.isEmpty()) _uiState.value = HomeUiState.Loading else _uiState.value = HomeUiState.Success(pets)
                } catch (e: Exception) {
                    _uiState.value = HomeUiState.Error(e.message.toString())
                }
            }
        }
    }
}