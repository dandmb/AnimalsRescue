package com.dmb25.jpcompose_practice.di

import com.dmb25.jpcompose_practice.domain.usecase.DeletePetByIdUseCase
import com.dmb25.jpcompose_practice.domain.usecase.GetAllPetsUseCase
import com.dmb25.jpcompose_practice.domain.usecase.GetPetByIdUseCase
import com.dmb25.jpcompose_practice.domain.usecase.UpdatePetUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetAllPetsUseCase(get()) }
    single { GetPetByIdUseCase(get()) }
    single { DeletePetByIdUseCase(get()) }
    single { UpdatePetUseCase(get()) }
}