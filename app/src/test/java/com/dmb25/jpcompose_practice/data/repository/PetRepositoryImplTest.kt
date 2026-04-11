package com.dmb25.jpcompose_practice.data.repository

import app.cash.turbine.test
import com.dmb25.jpcompose_practice.R
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.model.Owner
import com.dmb25.jpcompose_practice.domain.model.Pet
import com.dmb25.jpcompose_practice.domain.repository.PetRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PetRepositoryImplTest {

    private lateinit var dummyPet: DummyPetDataSource
    private lateinit var repository: PetRepository

    @Before
    fun setUp() {
        dummyPet = DummyPetDataSource
        repository = PetRepositoryImpl(dummyPet)
    }

    val owner = Owner(name = "Said", "Developer", R.drawable.blue_dog)
    val pet = Pet(
        id = 12,
        name = "Hiro",
        age = "Adult",
        gender = "Male",
        color = "Brown",
        breed = "Chihuahua",
        location = "Toronto CA",
        image = R.drawable.orange_dog,
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
        owner = owner
    )

    @Test
    fun `when the valid pet is given, add the pet`() = runTest {

        repository.addPet(pet).test {
            assert(awaitItem() == "ADDING")
            assert(awaitItem() == "ADDED")
            awaitComplete()
        }
        assert(dummyPet.dogList.contains(pet))
    }

    @Test
    fun `when the pet is being added, should return ADDING`() = runTest {
        repository.addPet(pet).test {
            assert(awaitItem() == "ADDING")
        }
    }

    @Test
    fun `when the pet is added, should return ADDED`() = runTest {
        repository.addPet(pet).test {
            awaitItem()
            val result = awaitItem()
            assert(result == "ADDED")
            awaitComplete()
        }
    }


    @Test
    fun `when the id is valid, should return the pet`() = runTest {
        repository.getPet(12)?.let { assert(it == pet) }
    }

    @Test
    fun `when the id is invalid, should return null`() = runTest {
        val pet = repository.getPet(100)
        assert(pet == null)
    }


    @Test
    fun `when the id is valid, delete the pet`() = runTest {
        val pet = DummyPetDataSource.dogList[0]
        repository.deletePet(pet.id)
        assert(!dummyPet.dogList.contains(pet))
    }

    @Test
    fun `when the id is invalid, should not delete the pet`() = runTest {
        val pet = DummyPetDataSource.dogList[0]
        repository.deletePet(100)
        assert(dummyPet.dogList.contains(pet))
    }

    @Test
    fun `when the valid pet is given, update the pet`() = runTest {
        val pet = DummyPetDataSource.dogList[0]
        val updatedPet = repository.updatePet(pet)
        assert(updatedPet == pet)
    }

    @Test
    fun `when the invalid pet is given, should return null`() = runTest {
        val updatePet = repository.updatePet(pet)
        assert(updatePet == null)
    }

    @Test
    fun `when getAllPets is called, should return all pets`() = runTest {
        repository.getPets().test {
            awaitItem()
            val result = awaitItem()
            assert(DummyPetDataSource.dogList == result)
            awaitComplete()
        }
    }

    @Test
    fun `when getAllPets is called, should return empty list`() = runTest {
        repository.getPets().test {
            val result = awaitItem()
            awaitItem()
            assert(result.isEmpty())
            awaitComplete()
        }
    }

}