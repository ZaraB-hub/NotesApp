package com.example.notesapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun type(){
    var titleValue by remember{ mutableStateOf("") }
    TextField(
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color.White,
//
//        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {}),
        textStyle = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(400)),
        placeholder = {
            Text(
                text = "Title",
                color=Color.DarkGray
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        value = titleValue,
        onValueChange = { newTitle -> titleValue = newTitle })
}