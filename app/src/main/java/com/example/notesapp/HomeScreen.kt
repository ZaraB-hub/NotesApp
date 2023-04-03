package com.example.notesapp

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.OptionMenu
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController
){
    MyApp(navController=navController)
}

data class Notes(val id:Int,val title:String,val body:String,val timestamp:Date){
    val formattedTimestamp: String
        get() = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(timestamp)
}

val items = listOf(
    Notes(id = 1,
        title = "Things to buy",
        body = "djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj",
        timestamp = Date()),

)


@Composable
fun MyApp(navController: NavController,modifier: Modifier = Modifier) {
    Surface(color = Color.Blue.copy(alpha = .1f)) {
        Box(modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                MainTopBar()
                NotesList(notes = items,navController=navController)
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = { navController.navigate(route=Screen.Note.route) }, shape = CircleShape, modifier = Modifier
                    .width(60.dp)
                    .height(60.dp), containerColor = Color.hsl(270f,0.5f,0.75f), contentColor = Color.White) {
                    Icon(Icons.Filled.Add, "Create Note",modifier=Modifier.size(55.dp))
                }
            }
        }
    }
}

@Composable
fun noteCustom(note: Notes,modifier: Modifier=Modifier,navController: NavController) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(15),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { navController.navigate(route = Screen.Note.route) }

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
                Text(text = "${note.formattedTimestamp}", fontSize = 20.sp, color = Color.Black.copy(alpha = 0.5f))
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
fun NotesList(notes:List<Notes>,modifier: Modifier=Modifier,navController: NavController){
    LazyColumn(contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(items){
                note->noteCustom(note = note, navController = navController)
            // Divider(color=Color.LightGray.copy(alpha = 0.8f), thickness = 2.dp)
        }
    }
}
@Composable
fun MainTopBar(){
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
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    Modifier
                        .fillMaxSize()

                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
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
fun DefaultPreview() {
    NotesAppTheme {
        noteCustom(note =Notes(id = 1,
            title = "Things to bye",
            body = "bye djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj",
            timestamp = Date()),
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
    Surface(color= Color.Yellow) {
        Column(verticalArrangement = Arrangement.Bottom) {
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
    }

}

