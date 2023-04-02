package com.example.notesapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

@Composable
fun NoteScreen(
){
    NotePage()
}

@Composable
fun NotePage(modifier: Modifier=Modifier){
    var title by remember { mutableStateOf("Title") }
    var body by remember { mutableStateOf("A paragraph is a self-contained unit of discourse in writing dealing with a particular point or idea. Though not required by the orthographic conventions of any language with a writing system, paragraphs are a conventional means of organizing extended segments") }
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        TopAppBar()
        Text("1/4/2023, 16:43",modifier=Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,color= Color.DarkGray)
        BasicTextField(
            value = title,
            onValueChange = {title=it},
            cursorBrush = SolidColor(Color.Green),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = null),
            textStyle = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(400) )
        )
        Spacer(modifier = Modifier.padding(8.dp))
        BasicTextField(value =body, onValueChange ={body=it},            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = null),
            textStyle = TextStyle(fontSize = 16.sp,  )
        )



    }
}
@Composable
fun TopAppBar(){
    Row(modifier =Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(onClick = { /* doSomething() */ }, modifier = Modifier
            .width(25.dp)
            .height(25.dp)) {
            Icon(
                Icons.Filled.ArrowBack, contentDescription = "Localized description",
                modifier = Modifier.fillMaxSize())
        }
        IconButton(onClick = { /* doSomething() */ }, modifier = Modifier
            .width(25.dp)
            .height(25.dp)) {
            Icon(
                Icons.Filled.Menu, contentDescription = "Localized description",
                modifier = Modifier.fillMaxSize())
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun preview() {
    NotePage()
}