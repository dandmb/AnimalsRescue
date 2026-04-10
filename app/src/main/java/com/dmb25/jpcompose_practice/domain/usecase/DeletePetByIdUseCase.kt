package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.domain.repository.PetRepository

class DeletePetByIdUseCase(private val repository: PetRepository) {
    suspend operator fun invoke(id: Int) {
        require(id >= 0) { ERROR_MESSAGE }
        repository.deletePet(id)
    }
    companion object{
        const val ERROR_MESSAGE = "Invalid id"

    }
}