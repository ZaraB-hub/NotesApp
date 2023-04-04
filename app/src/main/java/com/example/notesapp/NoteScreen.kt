package com.example.notesapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NoteScreen(navController: NavController){
    val sentNote = navController.previousBackStackEntry?.savedStateHandle?.get<Note>("note")
    NotePage(sentNote,navController=navController)
    }

@Composable
fun NotePage(note:Note?,navController:NavController,modifier: Modifier=Modifier){
    var title by remember { mutableStateOf(note?.title ?: "Title") }
    var body by remember { mutableStateOf(note?.body ?: "...") }
    var timestamp= note?.timestamp ?: Date()



        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            TopAppBar(navController = navController)
            Text(
                SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(timestamp),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                cursorBrush = SolidColor(Color.Green),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = null),
                textStyle = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(400))
            )
            Spacer(modifier = Modifier.padding(8.dp))
            BasicTextField(
                value = body,
                onValueChange = { body = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = null),
                textStyle = TextStyle(fontSize = 16.sp,),
                modifier= Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = {
                        if (note != null) {
                            note.title = title
                            note.body = body
                            note.timestamp = Date()
                        }

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
@Preview(showSystemUi = true)
@Composable
fun prevNotes(){
    NotePage(null,navController = rememberNavController())
}

@Preview (showBackground =true )
@Composable
fun prevTop(){
    TopAppBar(navController = rememberNavController())
}