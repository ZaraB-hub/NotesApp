package com.example.notesapp

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
fun NoteScreen(navController: NavController,id:Int?){

    if (id != null) {
        NotePage(noteViewModel = viewModel(), id = id, navController = navController)
    }

}

@Composable
fun NotePage(noteViewModel: NoteViewModel,id:Int, navController:NavController, modifier: Modifier=Modifier){
    val note by noteViewModel.getNoteById(id).collectAsState(initial = null)
    var titleValue by remember { mutableStateOf(note?.title?:"title" ) } 
    var bodyValue by remember { mutableStateOf(note?.body ?: "body") }

    LaunchedEffect(note?.title) {
        titleValue = note?.title ?: "title"
        bodyValue=note?.body?:""
    }
    Log.d("1","${note?.title}")
    Log.d("2",titleValue)
        
    Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
        val focusManager = LocalFocusManager.current
            TopAppBar(navController = navController)

            BasicTextField(
                value = titleValue,
                onValueChange = { titleValue=it},
                cursorBrush = SolidColor(Color.Green),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
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
                        note!!.title=titleValue
                        note!!.body=bodyValue
                        noteViewModel.upsert(note!!)
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


@Preview (showBackground =true )
@Composable
fun prevTop(){
    TopAppBar(navController = rememberNavController())
}