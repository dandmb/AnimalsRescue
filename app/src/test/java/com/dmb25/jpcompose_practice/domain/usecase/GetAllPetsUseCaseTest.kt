package com.dmb25.jpcompose_practice.domain.usecase

import app.cash.turbine.test
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllPetsUseCaseTest {

    private lateinit var repository: PetRepository
    private lateinit var getAllPetsUseCase: GetAllPetsUseCase

    @Before
    fun setup() {
        repository = mockk()
        getAllPetsUseCase = GetAllPetsUseCase(repository)
    }

    @Test
    fun `when ask, all pets are returned`() = runTest {
        every { repository.getPets() } returns flowOf(DummyPetDataSource.dogList)
        getAllPetsUseCase().test {
            val pets = awaitItem()
            assert(pets.size == DummyPetDataSource.dogList.size)
            awaitComplete()
        }
    }

    @Test
    fun `when ask, empty list is returned if there is no data`() = runTest {
        every { repository.getPets() } returns flowOf(emptyList())
        getAllPetsUseCase().test {
            val pets = awaitItem()
            assert(pets.isEmpty())
            awaitComplete()
        }
    }

    @Test
    fun `when getAllPetsUseCase is called, repository is called once`() = runTest {
        every { repository.getPets() } returns flowOf(DummyPetDataSource.dogList)
        getAllPetsUseCase().first()
        verify(exactly = 1) { runBlocking { repository.getPets() } }
    }

}