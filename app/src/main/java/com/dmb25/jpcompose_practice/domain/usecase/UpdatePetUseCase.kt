package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.domain.model.Pet
import com.dmb25.jpcompose_practice.domain.repository.PetRepository

class UpdatePetUseCase(
    private val repository: PetRepository
) {
    suspend operator fun invoke(pet: Pet) {
        repository.updatePet(pet)
    }
}