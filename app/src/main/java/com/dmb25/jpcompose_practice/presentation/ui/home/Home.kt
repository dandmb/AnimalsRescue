package com.dmb25.jpcompose_practice.presentation.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.presentation.ui.home.components.AnimalItem


@Composable
fun Home(modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier,
    ){
        items(DummyPetDataSource.dogList.size){
            AnimalItem(pet = DummyPetDataSource.dogList[it], onItemClick = {})
        }
    }
}