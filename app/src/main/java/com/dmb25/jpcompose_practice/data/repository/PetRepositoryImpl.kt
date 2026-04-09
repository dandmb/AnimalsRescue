package com.dmb25.jpcompose_practice.data.repository

import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.model.Pet
import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PetRepositoryImpl(private val dummyPet: DummyPetDataSource) : PetRepository {
    override suspend fun addPet(pet: Pet) {
        TODO("Not yet implemented")
    }

    override suspend fun getPet(id: Int): Pet? {
        TODO("Not yet implemented")
    }

    override suspend fun deletePet(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePet(pet: Pet) {
        TODO("Not yet implemented")
    }

    override fun getPets(): Flow<List<Pet>> = flow{
        emit(emptyList())
        delay(2000)
        emit(dummyPet.dogList)
    }.flowOn(Dispatchers.IO)

}