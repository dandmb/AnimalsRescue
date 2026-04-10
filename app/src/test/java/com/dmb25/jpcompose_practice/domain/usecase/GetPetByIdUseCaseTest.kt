package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetPetByIdUseCaseTest {
    private lateinit var repository: PetRepository
    private lateinit var getPetByIdUseCase: GetPetByIdUseCase
    @Before
    fun setup() {
        repository = mockk()
        getPetByIdUseCase = GetPetByIdUseCase(repository)
    }

    @Test
    fun `when the id is valid, return the pet`()= runTest{
        coEvery { repository.getPet(1) } returns DummyPetDataSource.dogList[0]
        val pet = getPetByIdUseCase(1)
        assert(pet == DummyPetDataSource.dogList[0])
    }

    @Test
    fun `when the id is invalid, return null`()= runTest {
        coEvery { repository.getPet(-1) } returns null
        val pet = getPetByIdUseCase(-1)
        assert(pet == null)
    }

    @Test
    fun `when the id is valid, the repo is called once`() = runTest {
        coEvery { repository.getPet(1) } returns DummyPetDataSource.dogList[0]
        getPetByIdUseCase(1)
        coVerify(exactly = 1) { repository.getPet(1) }
    }



}