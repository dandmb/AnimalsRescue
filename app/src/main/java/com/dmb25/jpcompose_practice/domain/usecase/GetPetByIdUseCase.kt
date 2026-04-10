package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.domain.model.Pet
import com.dmb25.jpcompose_practice.domain.repository.PetRepository

class GetPetByIdUseCase(
    private val repository: PetRepository
) {
    suspend operator fun invoke(id: Int): Pet? {
        return repository.getPet(id)
    }
}