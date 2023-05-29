package com.example.notesapp.screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.NoteViewModel
import com.example.notesapp.old.Note
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddScreen(navController: NavController){

        NotePage(noteViewModel = viewModel(), navController = navController)

}

@Composable
fun NotePage(noteViewModel: NoteViewModel, navController:NavController, modifier: Modifier=Modifier){
    var titleValue by remember { mutableStateOf("Title" ) }
    var bodyValue by remember { mutableStateOf( "...") }




    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
    ) {
        TopAppBar(navController = navController)
        val focusManager = LocalFocusManager.current
        BasicTextField(
            value = titleValue,
            onValueChange = { titleValue=it},
            cursorBrush = SolidColor(Color.Green),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            textStyle = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(400))
        )
        Spacer(modifier = Modifier.padding(8.dp))
        BasicTextField(
            value = bodyValue,
            onValueChange = { bodyValue=it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
            textStyle = TextStyle(fontSize = 16.sp,),
            modifier= Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(
                onClick = {
                    var note=com.example.notesapp.data.Note(title = titleValue,body=bodyValue)
                    noteViewModel.upsert(note)
                },
            ) {
                Text("Save")
            }
        }
    }
}




@Composable
fun TopAppBar(navController: NavController){
    Row(modifier =Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier
            .width(25.dp)
            .height(25.dp)
            .offset(y = 8.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack, contentDescription = "Localized description",
            )
        }
        OptionMenu(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun OptionMenu(modifier: Modifier=Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .wrapContentSize()) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert,contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = { /* Handle edit! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Share") },
                onClick = { /* Handle settings! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Share,
                        contentDescription = null
                    )
                })

        }
    }
}

