package com.dmb25.jpcompose_practice.domain.repository

import com.dmb25.jpcompose_practice.data.AddingState
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    fun addPet(pet: Pet): Flow<String>
    suspend fun getPet(id: Int): Pet?
    suspend fun deletePet(id: Int)
    suspend fun updatePet(pet: Pet)
    fun getPets(): Flow<List<Pet>>
}