package com.example.notesapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.R


@Composable
fun DefaultContent (){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable._832413),
            contentDescription = "",
            modifier = Modifier.size(130.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Your notes will appear here", style = MaterialTheme.typography.titleSmall)
    }
}

@Preview(showSystemUi = true)
@Composable
fun done(){
    DefaultContent()
}