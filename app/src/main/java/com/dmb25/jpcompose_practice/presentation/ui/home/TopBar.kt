package com.dmb25.jpcompose_practice.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmb25.jpcompose_practice.R

@Composable
@Preview(showSystemUi = true)
fun TopBar(
    onToggle: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().statusBarsPadding().background(color = MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Hello Paul,",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Adopt a new friend", style = MaterialTheme.typography.bodySmall)
        }
        Row(modifier = Modifier.padding(
            top = 24.dp,
            end = 36.dp
        )) {
            PetSwitch(onToggle = {
                onToggle()
            })
        }
    }
}

@Composable
fun PetSwitch(
    onToggle: () -> Unit,
){
    val icon = if (isSystemInDarkTheme()){
        painterResource(id = R.drawable.switch_24px)

    }else{
        painterResource(id = R.drawable.switch_off_24px)

    }

    Icon(painter = icon,
        contentDescription = null,
        modifier = Modifier.size(24.dp).clickable(onClick = {onToggle()}),
        tint = MaterialTheme.colorScheme.onSurface
    )
}