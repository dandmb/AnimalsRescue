package com.dmb25.jpcompose_practice.di

import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.data.repository.PetRepositoryImpl
import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import org.koin.dsl.module

val dataModule = module {
    single { DummyPetDataSource }
    single<PetRepository> { PetRepositoryImpl(get()) }
}