package com.example.notesapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.notesapp.components.DefaultContent
import com.example.notesapp.data.Folder
import com.example.notesapp.data.FolderViewModel
import com.example.notesapp.data.NoteViewModel
import kotlinx.coroutines.flow.Flow
import java.util.*


@Composable
fun HomeScreen1(
    navController: NavController,
    id: Int?
) {
    if (id != null) {
        MyApp1(navController = navController, folderId = id, viewModel())
    }
}


@Composable
fun MyApp1(navController: NavController,folderId:Int,folderViewModel: FolderViewModel,modifier: Modifier = Modifier) {

    var folder=folderViewModel.getFolderById(folderId = folderId)
    var listLayout by remember { mutableStateOf(true) }
    Surface(color = Color.Blue.copy(alpha = .1f)) {
        Box(modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                MainTopBar1(listLayout, navController, onLayoutToggle = { newListLayout ->
                    listLayout = newListLayout
                }, folder =folder)
                if(listLayout){
                    NotesList1(viewModel(),navController=navController,folderId=folderId)
                } else {
                    NotesGrid1(viewModel(), navController = navController,folderId=folderId)
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = {navController.navigate(route="add_screen/"+folderId)}
                    ,shape = CircleShape, modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                    containerColor = Color.hsl(270f,0.5f,0.75f), contentColor = Color.White) {
                    Icon(Icons.Filled.Add, "Create Note",modifier=Modifier.size(55.dp))
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteCustom1(note: com.example.notesapp.data.Note, navController: NavController,noteViewModel: NoteViewModel,folderId: Int) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(15),
        modifier = Modifier
            .padding(bottom = 8.dp, top = 8.dp, start = 5.dp)
            .combinedClickable(
                onClick = {
                    navController.navigate("note_screen/" + note.id + "/" + folderId)
                },
                onLongClick = {
                    noteViewModel.delete(note)
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
fun NotesList1(noteViewModel: NoteViewModel, navController: NavController,folderId:Int) {
    val notesList by noteViewModel.getNotesByFolder(folderId).collectAsState(initial = emptyList())
    if(notesList.isEmpty()){
        DefaultContent()
    }else {
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(notesList) { note ->
                NoteCustom1(
                    note = note,
                    navController = navController,
                    noteViewModel = noteViewModel,
                    folderId=folderId
                )

            }

        }
    }
}


@Composable
fun NotesGrid1(noteViewModel: NoteViewModel, navController: NavController,folderId:Int) {
    val notesList by noteViewModel.getNotesByFolder(folderId).collectAsState(initial = emptyList())
    if(notesList.isEmpty()){
        DefaultContent()
    }else {
        Spacer(modifier=Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(notesList.size) {note->
                NoteCustom(
                    note = notesList[note],
                    navController = navController,
                    noteViewModel = noteViewModel
                )
                }
            }
        }
}
@Composable
fun MainTopBar1(
    layout: Boolean,
    navController: NavController,
    onLayoutToggle: (Boolean) -> Unit,
    folder: Flow<Folder?>
){
    val folderState by folder.collectAsState(initial = null)
    CenterAlignedTopAppBar(
        title = {
            Text(
                text =  folderState?.title ?: "",
                overflow = TextOverflow.Ellipsis,
                fontSize =30.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screen.Folder.route) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
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
            IconButton(onClick = { onLayoutToggle(!layout) }) {
                Icon(
                    painter = if (!layout) painterResource(R.drawable.baseline_view_list_24) else painterResource(R.drawable.baseline_grid_view_24),
                    contentDescription = "Localized description",
                    Modifier
                        .fillMaxSize()
                )
            }

        }
    )
}
