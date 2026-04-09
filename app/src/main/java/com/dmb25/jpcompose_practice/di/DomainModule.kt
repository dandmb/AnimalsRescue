package com.dmb25.jpcompose_practice.di

import com.dmb25.jpcompose_practice.domain.usecase.DeletePetById
import com.dmb25.jpcompose_practice.domain.usecase.GetAllPetsUseCase
import com.dmb25.jpcompose_practice.domain.usecase.GetPetUseCaseById
import com.dmb25.jpcompose_practice.domain.usecase.UpdatePetUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetAllPetsUseCase(get()) }
    single { GetPetUseCaseById(get()) }
    single { DeletePetById(get()) }
    single { UpdatePetUseCase(get()) }
}