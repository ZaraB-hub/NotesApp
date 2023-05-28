package com.example.notesapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notesapp.screens.InsertScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController,
    startDestination = Screen.Home.route){

        composable(route=Screen.Home.route){
            HomeScreen(navController=navController)
        }
        composable(route=Screen.Insert.route){
            InsertScreen(viewModel())
        }
        composable(route=Screen.Search.route){
            SearchScreen(navController=navController)
        }
        composable(
            route=Screen.Note.route
        ){
            NoteScreen(navController=navController)
        }

    }
}

