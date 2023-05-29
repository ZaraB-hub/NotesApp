package com.example.notesapp.screens

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.NoteViewModel

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

    Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            val focusManager = LocalFocusManager.current
        note?.let { TopAppBar(navController = navController,noteViewModel=noteViewModel,note= it) }

        Spacer(modifier=Modifier.height(4.dp))
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.titleLarge,
            placeholder = {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.LightGray)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            value = titleValue,
            onValueChange = { newTitle -> titleValue = newTitle })
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
                textStyle = TextStyle(fontSize = 16.sp),
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

@Preview
@Composable
fun Prev(){
    NotePage(noteViewModel = viewModel() , id =3, navController = rememberNavController())
}


@Composable
fun TopAppBar(navController: NavController,noteViewModel: NoteViewModel,note: com.example.notesapp.data.Note){
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
        OptionMenu(modifier = Modifier.fillMaxSize(),noteViewModel=noteViewModel,note=note, navController = navController)
    }
}

@Composable
fun OptionMenu(modifier: Modifier=Modifier,noteViewModel: NoteViewModel,note: com.example.notesapp.data.Note,navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
                onClick = { noteViewModel.delete(note)
                            navController.popBackStack()},
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Share") },
                onClick = {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        val noteText = "${note.title}\n\n${note.body}"
                        putExtra(Intent.EXTRA_TEXT, noteText)
                        type = "text/plain"
                    }
                    val title= "Share this note with:"
                    val chooser: Intent = Intent.createChooser(sendIntent, title)
                   startActivity(context,chooser,null)


                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Share,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Make a copy") },
                onClick = { noteViewModel.delete(note)
                    navController.popBackStack()},
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Create,
                        contentDescription = null
                    )
                })

        }
    }
}


