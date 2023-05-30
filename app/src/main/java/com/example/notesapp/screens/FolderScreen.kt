package com.example.notesapp.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.notesapp.MainTopBar
import com.example.notesapp.NotesGrid
import com.example.notesapp.NotesList
import com.example.notesapp.Screen
import com.example.notesapp.components.DefaultContent
import com.example.notesapp.data.Folder
import com.example.notesapp.data.FolderViewModel
import com.example.notesapp.data.NoteViewModel

@Composable
fun FolderScreen(navController: NavController,modifier:Modifier=Modifier){
                Box(
                        modifier
                                .fillMaxSize()
                                .padding(10.dp)) {
                        Column(Modifier.fillMaxSize()) {
                                Text(
                                        "Your folders",
                                        fontSize = 35.sp,
                                        fontWeight = FontWeight.Bold
                                )
                                Spacer(Modifier.height(5.dp))
                                FolderList(viewModel(),navController=navController)
                        }
                        Row(
                                modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp, vertical = 15.dp),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.End
                        ) {
                                Text(text = "New Folder",
                                fontSize = 25.sp,
                                color = Color.hsl(270f,.5f,0.3f),
                                )
                                }
                        }
                }




@Composable
fun FolderCustom(navController: NavController,folder: Folder,folderViewModel: FolderViewModel) {
        Text(
                text = folder.title,
                modifier = Modifier
                        .border(
                                width = 1.dp,
                                color = Color.LightGray,
                        )
                        .padding(5.dp)
                        .fillMaxWidth()
                        .clickable { navController.navigate("home1_screen/" + folder.id) },
                fontSize = 27.sp,
                fontWeight = FontWeight.SemiBold
        )
}


@Composable
fun FolderList(folderViewModel: FolderViewModel,navController: NavController) {
        val folderList by folderViewModel.allFolder.collectAsState(initial = emptyList())
        LazyColumn(
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                        items(folderList) { folder ->
                                FolderCustom(
                                        folder=folder,
                                        navController = navController,
                                        folderViewModel = folderViewModel
                                )
                        }

                }
        }

