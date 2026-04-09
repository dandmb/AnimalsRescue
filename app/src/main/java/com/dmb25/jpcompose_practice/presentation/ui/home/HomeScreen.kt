package com.dmb25.jpcompose_practice.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
<<<<<<< HEAD:app/src/main/java/com/dmb25/jpcompose_practice/presentation/ui/home/HomeScreen.kt
=======
import androidx.compose.ui.unit.dp
>>>>>>> bf3c217 (navigation):app/src/main/java/com/dmb25/jpcompose_practice/presentation/ui/home/Home.kt
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.presentation.ui.home.components.AnimalItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onToggle: () -> Unit, onPetClick: (Int) -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Column {
                        Text(
                            text = "Hi There,",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Adopt a new friend",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    PetSwitch(onToggle = {
                        onToggle()
                    })
                }
            )
        }) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
        ) {
            itemsIndexed(DummyPetDataSource.dogList) { index, pet ->
                AnimalItem(pet = pet, onItemClick = {
                    onPetClick(index)
                })
            }
        }
    }

}

<<<<<<< HEAD:app/src/main/java/com/dmb25/jpcompose_practice/presentation/ui/home/HomeScreen.kt
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onToggle = {}, onPetClick = {})
=======
@Composable
@Preview(showSystemUi = true)
fun HomePreview() {
    Home(onToggle = {}, onPetClick = {})
>>>>>>> bf3c217 (navigation):app/src/main/java/com/dmb25/jpcompose_practice/presentation/ui/home/Home.kt
}