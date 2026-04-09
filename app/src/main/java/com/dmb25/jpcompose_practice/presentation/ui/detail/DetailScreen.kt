package com.dmb25.jpcompose_practice.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmb25.jpcompose_practice.R
import com.dmb25.jpcompose_practice.data.local.DummyPetDataSource
import com.dmb25.jpcompose_practice.domain.model.Pet
import com.dmb25.jpcompose_practice.presentation.ui.detail.components.ButtonAdaptMe
import com.dmb25.jpcompose_practice.presentation.ui.home.components.GenderTag
import com.dmb25.jpcompose_practice.presentation.ui.home.components.LoadingComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onNavigateUp: () -> Unit,
    viewModel: DetailViewModel
) {
    val state = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detail",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onNavigateUp()
                            }
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when(state){
                is DetailUiState.Success -> {
                    item {
                        AnimalImage(pet = state.pet)
                    }
                    item {
                        PetStory(pet = state.pet)
                    }

                    item {
                        PetDescription(pet = state.pet)
                    }
                    item {
                        PetInfo(pet = state.pet)
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        OwnerInfo(state.pet)
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonAdaptMe()
                    }
                }
                is DetailUiState.Loading -> {
                    item{ LoadingComponent() }
                }
                is DetailUiState.Error -> {
                    item {
                        Text(text = state.message)
                    }
                }

            }
        }

    }
}


@Composable
fun OwnerInfo(pet: Pet) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.height(60.dp)) {
            Image(
                painter = painterResource(pet.owner.image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.height(60.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = pet.owner.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = pet.owner.basicInfo, style = MaterialTheme.typography.bodySmall)
            }
        }
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_messenger),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }


    }
}

@Composable
@Preview(showBackground = true)
fun OwnerInfoPreview() {
    OwnerInfo(pet = DummyPetDataSource.dogList[0])
}


@Composable
fun PetDescription(pet: Pet) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = pet.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PetDescriptionPreview() {
    PetDescription(pet = DummyPetDataSource.dogList[0])
}

@Composable
fun PetStory(pet: Pet) {
    Row(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = pet.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Icon(
                    tint = Color.Red,
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Adult", style = MaterialTheme.typography.bodySmall)
            }
            Text(text = "Adoptable", style = MaterialTheme.typography.bodySmall)
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenderTag(gender = pet.gender, modifier = Modifier)
            Text(text = "Dog", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun AnimalImage(pet: Pet) {
    Image(
        painter = painterResource(pet.image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PetInfo(pet: Pet) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Pet Info",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoCard(
                title = pet.age,
                subTitle = "Age",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                title = pet.color,
                subTitle = "Color",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                title = pet.breed,
                subTitle = "Breed",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PetInfoPreview() {
    PetInfo(pet = DummyPetDataSource.dogList[0])
}


@Composable
private fun InfoCard(modifier: Modifier = Modifier, title: String, subTitle: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, color = Color.Gray.copy(.40f))
        Text(
            text = subTitle,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
@Preview(showSystemUi = true)
fun DetailScreenPreview() {
//    DetailScreen(onNavigateUp = {}, 1)
}