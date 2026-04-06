package com.dmb25.jpcompose_practice.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.presentation.ui.home.components.AnimalItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(onToggle: () -> Unit, onPetClick: (Int) -> Unit) {

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