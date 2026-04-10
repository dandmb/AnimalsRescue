package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdatePetUseCaseTest {

    private lateinit var repository: PetRepository
    private lateinit var updatePetUseCase: UpdatePetUseCase

    @Before
    fun setup() {
        repository = mockk()
        updatePetUseCase = UpdatePetUseCase(repository)
    }

    @Test
    fun `when the valid pet is given, update the pet`() = runTest{
        coEvery { repository.updatePet(any()) } returns DummyPetDataSource.dogList[0]
        updatePetUseCase(DummyPetDataSource.dogList[0])
        coVerify(exactly = 1) { repository.updatePet(any()) }
    }

    @Test
    fun `when the valid pet is given, should return the updated pet`() = runTest {
        coEvery { repository.updatePet(any()) } returns DummyPetDataSource.dogList[0]
        val pet = updatePetUseCase(DummyPetDataSource.dogList[0])
        assert(pet == DummyPetDataSource.dogList[0])
    }

    @Test
    fun `when the invalid pet is given, should return null`() = runTest {
        coEvery { repository.updatePet(any()) } returns null
        val pet = updatePetUseCase(DummyPetDataSource.dogList[0])
        assert(pet == null)
    }
}