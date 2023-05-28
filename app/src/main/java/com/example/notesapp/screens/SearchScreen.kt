package com.example.notesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun SearchScreen(navController: NavController){
    Column(
        Modifier
            .fillMaxSize(),
    ) {
        SearchTop(navController = rememberNavController())
        Results()
    }
}


@Composable
fun SearchTop(navController:NavController){
    var searchTerm by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .height(25.dp)
                .offset(y = 10.dp)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
        }

        TextField(
            value = searchTerm,
            onValueChange = {
                searchTerm = it
            },
            placeholder = { Text("Search") },
            colors = TextFieldDefaults.textFieldColors(
             //   backgroundColor = Color.White
            ),
            trailingIcon = {
                if (searchTerm.isNotEmpty()) {
                    IconButton(
                        onClick = { searchTerm="" }
                    ) {
                        Icon(Icons.Filled.Clear, contentDescription = "Localized description")
                    }
                }
            }
        )
    }
}

@Composable
fun Results(){
    Column(
        Modifier
            .padding(top = 20.dp, start = 20.dp)
            .fillMaxWidth()) {
        Text("result2")
    }
}




