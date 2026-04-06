package com.dmb25.jpcompose_practice.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.dmb25.jpcompose_practice.domain.model.Owner
import com.dmb25.jpcompose_practice.domain.model.Pet


@Composable
fun AnimalItem(
    modifier: Modifier = Modifier,
    pet: Pet,
    onItemClick: (Pet) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClick(pet) }),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = pet.image),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterStart
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pet.name,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = buildString {
                        append(pet.age)
                        append(" | ")
                        append("Domestic Short Hair")
                    }, color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = pet.location,
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 0.dp, end = 12.dp, bottom = 0.dp
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GenderTag(gender = pet.gender, modifier = Modifier)
                Text(
                    text = "Adoptable", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun GenderTag(gender: String, modifier: Modifier) {
    val color = if (gender == "Male") Color.Blue else Color.Red
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = .2f))
    ) {
        Text(
            text = gender,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodySmall,
            color = color
        )
    }
}


var owner = Owner(name = "Said", "Scientist", R.drawable.blue_dog)

var pet = Pet(
    id = 0,
    name = "Hachiko",
    age = "Adult",
    gender = "Male",
    color = "Brown",
    breed = "Chihuahua",
    location = "Toronto CA",
    image = R.drawable.orange_dog,
    description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    owner = owner
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimalPreview() {
    AnimalItem(
        pet = pet,
        modifier = Modifier,
        onItemClick = {}
    )
}