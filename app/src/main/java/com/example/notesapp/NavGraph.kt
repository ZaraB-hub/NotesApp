package com.example.notesapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notesapp.screens.AddScreen
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
        composable(route=Screen.Insert.route,
        arguments= listOf(navArgument("id"){
            type= NavType.IntType
        })
        ){

            val NoteId=it.arguments?.getInt("id")
            InsertScreen(viewModel(),navController=navController,id=NoteId)
        }
        composable(route=Screen.Search.route){
            SearchScreen(navController=navController)
        }
        composable(route=Screen.Add.route){
            AddScreen(navController=navController)
        }
        composable(route=Screen.Note.route,
            arguments= listOf(navArgument("id"){
                type= NavType.IntType
            })
        ){
            val noteId=it.arguments?.getInt("id")
            NoteScreen(navController=navController,id=noteId)
        }

    }
}

