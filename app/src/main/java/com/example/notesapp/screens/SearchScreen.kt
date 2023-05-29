package com.example.notesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.NoteViewModel


@Composable
fun SearchScreen(navController: NavController){
    var searchTerm by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize(),
    ) {
        SearchTop(navController = navController, searchTerm = searchTerm) { newSearchTerm ->
            searchTerm = newSearchTerm
        }
        Results(viewModel(),navController, searchTerm = searchTerm)
    }
}


@Composable
fun SearchTop(navController: NavController, searchTerm: String, onSearchTermChange: (String) -> Unit) {
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
        val focusManager= LocalFocusManager.current
        TextField(
            value = searchTerm,
            onValueChange = onSearchTermChange,
            placeholder = { Text("Search") },
            trailingIcon = {
                if (searchTerm.isNotEmpty()) {
                    IconButton(
                        onClick = { onSearchTermChange("") }
                    ) {
                        Icon(Icons.Filled.Clear, contentDescription = "Localized description")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })

            )
    }
}

@Composable
fun Results(
    noteViewModel: NoteViewModel,
    navController: NavController,
    searchTerm: String
) {
    val notesList by noteViewModel.getNotesByTitle(searchTerm).collectAsState(initial = emptyList())

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(notesList) { note ->
            Column {
                NoteCustom(
                    note = note,
                    navController = navController,
                    noteViewModel = noteViewModel
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black)
                )
            }
        }
    }
}




