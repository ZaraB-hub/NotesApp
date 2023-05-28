package com.example.notesapp

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.NoteViewModel
import com.example.notesapp.old.Note
import com.example.notesapp.old.NoteRepository
import com.example.notesapp.old.notesList
import com.example.notesapp.ui.theme.NotesAppTheme
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController
){
    MyApp(navController=navController)
}

@Composable
fun MyApp(navController: NavController,modifier: Modifier = Modifier) {

    val noteRepository= NoteRepository(notesList)

    Surface(color = Color.Blue.copy(alpha = .1f)) {
        Box(modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                MainTopBar(navController=navController)
                NotesList(viewModel(),navController=navController)
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = {
                    val newNote = noteRepository.addNewNote()
                    navController.currentBackStackEntry?.savedStateHandle?.set("note",newNote)
                    navController.navigate(route=Screen.Note.route)
                                               }
                    , shape = CircleShape, modifier = Modifier
                    .width(60.dp)
                    .height(60.dp), containerColor = Color.hsl(270f,0.5f,0.75f), contentColor = Color.White) {
                    Icon(Icons.Filled.Add, "Create Note",modifier=Modifier.size(55.dp))
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteCustom(note: com.example.notesapp.data.Note, navController: NavController) {
    val context  = LocalContext.current


    Surface(
        color = Color.White,
        shape = RoundedCornerShape(15),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .combinedClickable(
                onClick = {

                },
                onLongClick = {

                }
            )



    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Text(
                text = "${note.title}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text ="${note.timestamp}", fontSize = 20.sp, color = Color.Black.copy(alpha = 0.5f))
                Text(
                    text = "${note.body}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

        }
    }
}

@Composable
fun NotesList(noteViewModel: NoteViewModel, navController: NavController) {
    val notesList by noteViewModel.allNotes.collectAsState(initial = emptyList())

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(notesList) { note ->
            NoteCustom(note = note, navController = navController)
        }
    }
}
@Composable
fun MainTopBar(navController: NavController){
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Notes",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize =25.sp
            )
        },
        navigationIcon = {
            var expanded by remember { mutableStateOf(false) }
            IconButton(onClick = { expanded=true }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    Modifier
                        .fillMaxSize()

                )
            }

        },
        actions = {
            IconButton(onClick = { navController.navigate(route=Screen.Search.route) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Localized description",
                    Modifier
                        .fillMaxSize()

                )
            }
            OptionMenu()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomNoteObjectPreview() {
    NotesAppTheme {
        NoteCustom(note = com.example.notesapp.data.Note(id = 1,
            title = "Things to bye",
            body = "bye",
            ),
        navController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ColumnPreview() {
    NotesAppTheme {
        MyApp(navController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bottom(){
               Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(250.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Localized description")
                }
                FloatingActionButton(onClick = { /*TODO*/ }, shape = CircleShape, modifier = Modifier
                    .width(60.dp)
                    .height(60.dp), containerColor = Color.hsl(270f,0.5f,0.75f), contentColor = Color.White) {
                    Icon(Icons.Filled.Add, "Create Note",modifier=Modifier.size(55.dp))
                }
            }

        }


@Preview (showSystemUi = true)
@Composable
fun NavDrawer(
) {
    Box(modifier = Modifier
        .fillMaxHeight()
        .width(300.dp)
        .padding(top = 20.dp, end = 100.dp) ){
        Column (modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){
            Text(text = "NotesApp", fontSize = 20.sp,modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            Divider(thickness = 0.5.dp,color = Color.Gray)
            Spacer(modifier = Modifier.padding(7.dp))
            Text(text = "Notes",modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Create/Edit folders",modifier = Modifier.padding(start = 10.dp))
        }
    }

}