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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapp.ui.theme.NotesAppTheme


@Composable
fun HomeScreen(
    navController: NavController
){
    MyApp(modifier = Modifier.clickable { navController.navigate(route = Screen.Note.route) })
}

data class Notes(val id:Int,val title:String,val body:String)
val items = listOf(
    Notes(id = 1,
        title = "Things to bye",
        body = "djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj"),
    Notes(id = 1,
        title = "Things to bye",
        body = "djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj"),
    Notes(id = 1,
        title = "Things to bye",
        body = "djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj"),
    Notes(id = 1,
        title = "Things to bye",
        body = "djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj"),
    Notes(id = 1,
        title = "Things to bye",
        body = "djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj"),
)


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface(color = Color.Blue.copy(alpha = .1f)) {
        Box(modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                Text(
                    text = "Folder Name",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 9.dp)
                )
                NotesList(notes = items)
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = { /*TODO*/ }, shape = CircleShape, modifier = Modifier
                    .width(60.dp)
                    .height(60.dp), containerColor = Color.hsl(270f,0.5f,0.75f), contentColor = Color.White) {
                    Icon(Icons.Filled.Add, "Create Note",modifier=Modifier.size(55.dp))
                }
            }
        }
    }
}

@Composable
fun noteCustom(note: Notes,modifier: Modifier=Modifier) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(15),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)

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
                Text(text = "02/20/2000", fontSize = 20.sp, color = Color.Black.copy(alpha = 0.5f))
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
fun NotesList(notes:List<Notes>,modifier: Modifier=Modifier){
    LazyColumn(contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(items){
                note->noteCustom(note = note)
            // Divider(color=Color.LightGray.copy(alpha = 0.8f), thickness = 2.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAppTheme {
        noteCustom(note =Notes(id = 1,
            title = "Things to bye",
            body = "bye djfjsdjdkjfkdjgkdjfgjdkfjgfdkgj"))
    }
}



@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ColumnPreview() {
    NotesAppTheme {
        MyApp()
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

