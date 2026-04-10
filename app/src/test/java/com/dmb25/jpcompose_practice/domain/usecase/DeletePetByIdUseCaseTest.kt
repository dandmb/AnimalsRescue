package com.dmb25.jpcompose_practice.domain.usecase

import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class DeletePetByIdUseCaseTest {
    private lateinit var repository: PetRepository
    private lateinit var deletePetByIdUseCase: DeletePetByIdUseCase
    private lateinit var getPetsUseCase: GetAllPetsUseCase

    @Before
    fun setup(){
        repository = mockk()
        deletePetByIdUseCase = DeletePetByIdUseCase(repository)
        getPetsUseCase = GetAllPetsUseCase(repository)
    }

    @Test
    fun `when the id is valid, delete the pet`() = runTest {

        coEvery { repository.deletePet(1) } returns Unit

        deletePetByIdUseCase(1)

        coVerify(exactly = 1) { repository.deletePet(1) }
    }

    @Test
    fun `when the id is invalid, should throw exception`() = runTest {

        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking { deletePetByIdUseCase(-1) }
        }

        assertEquals(DeletePetByIdUseCase.ERROR_MESSAGE, exception.message)
    }

    @Test
    fun `when the id is invalid, the repo is not called`() {

        runCatching{ runBlocking{ deletePetByIdUseCase(-1) } }
        coVerify(exactly = 0) { repository.deletePet(any()) }

    }
}