package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.domain.model.Pet
import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow

class GetAllPetsUseCase(
    private val repository: PetRepository,
) {
    operator fun invoke() : Flow<List<Pet>> {
        return repository.getPets()
    }
}