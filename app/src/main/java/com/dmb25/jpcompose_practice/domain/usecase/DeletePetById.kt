package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.domain.repository.PetRepository

class DeletePetById(private val repository: PetRepository) {
    suspend operator fun invoke(id: Int) {
        repository.deletePet(id)
    }
}