package com.example.notesapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteViewModel

@Composable
fun InsertScreen(noteViewModel:NoteViewModel,navController:NavController,id:Int?) {

    val titleState = remember { mutableStateOf("") }
    val bodyState = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = titleState.value,
            onValueChange = { titleState.value = it },
            label = { Text(text = "Title") }
        )
        OutlinedTextField(
            value = bodyState.value,
            onValueChange = { bodyState.value = it },
            label = { Text(text = "Body") }
        )

        Button(
            onClick = {
                      Log.d("text","text")
                val newNote = Note(
                    title = titleState.value,
                    body = bodyState.value
                )

                 noteViewModel.upsert(newNote)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Insert Note")
        }
    }
}