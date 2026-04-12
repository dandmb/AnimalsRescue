package com.dmb25.jpcompose_practice.presentation.ui.home

import app.cash.turbine.test
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.usecase.GetAllPetsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var getAllPetsUseCase: GetAllPetsUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        getAllPetsUseCase = mockk()
    }


    @Test
    fun `Initial state verification`() {
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        assert(viewModel.uiState.value == HomeUiState.Loading)
    }

    @Test
    fun `getAllPets handles empty list emission`() = runTest {

        every { getAllPetsUseCase.invoke() } returns flowOf(emptyList())
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        viewModel.uiState.test {
            assert(awaitItem() == HomeUiState.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getAllPets handles non empty list emission`() = runTest {
        every { getAllPetsUseCase.invoke() } returns flowOf(DummyPetDataSource.dogList)
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        viewModel.uiState.test {
            assert(awaitItem() == HomeUiState.Loading)
            advanceUntilIdle()
            assert(awaitItem() == HomeUiState.Success(DummyPetDataSource.dogList))
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Exception handling during flow collection`() = runTest {
        every { getAllPetsUseCase.invoke() } returns flow { throw Exception("Test Exception") }
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        viewModel.uiState.test {
            assert(awaitItem() == HomeUiState.Loading)
            advanceUntilIdle()
            assert(awaitItem() == HomeUiState.Error("Test Exception"))
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Exception with null message handling`() = runTest {
        every { getAllPetsUseCase.invoke() } returns flow { throw Exception(null.toString()) }
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        viewModel.uiState.test {
            assert(awaitItem() == HomeUiState.Loading)
            advanceUntilIdle()
            assert(awaitItem() == HomeUiState.Error("null"))
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Multiple flow emissions update state`() = runTest{
        every { getAllPetsUseCase.invoke() } returns flow {
            emit(emptyList())
            emit(DummyPetDataSource.dogList)
            emit(DummyPetDataSource.dogList.filter { it.id % 2 == 0 })
        }
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        viewModel.uiState.test {
            assert(awaitItem() == HomeUiState.Loading)
            advanceUntilIdle()
            assert(awaitItem() == HomeUiState.Success(DummyPetDataSource.dogList))
            advanceUntilIdle()
            assert(awaitItem() == HomeUiState.Success(DummyPetDataSource.dogList.filter { it.id % 2 == 0}))
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `UseCase execution on init`() = runTest {
        every { getAllPetsUseCase.invoke() } returns flowOf(DummyPetDataSource.dogList)
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        advanceUntilIdle()
        verify(exactly = 1) { getAllPetsUseCase.invoke() }
    }

    @Test
    fun `Manual trigger of getAllPets`() = runTest{
        every { getAllPetsUseCase.invoke() } returns flowOf(DummyPetDataSource.dogList)
        viewModel = HomeViewModel(getAllPetsUseCase, mockk(), mockk(), mockk())
        viewModel.getAllPets()
        assert(viewModel.uiState.value == HomeUiState.Loading)
        advanceUntilIdle()
        assert(viewModel.uiState.value == HomeUiState.Success(DummyPetDataSource.dogList))
    }

}