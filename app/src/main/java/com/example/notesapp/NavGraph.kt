package com.example.notesapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController,
    startDestination = Screen.Home.route){
//        composable(
//            route=Screen.Splash.route
//        ){
//            SplashScreen(navController=navController)
//        }
        composable(
            route=Screen.Home.route
        ){
            HomeScreen(navController=navController)
        }
        composable(
            route=Screen.Note.route
        ){
            NoteScreen(navController=navController)
        }

    }
}