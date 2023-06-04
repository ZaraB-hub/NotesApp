package com.example.notesapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notesapp.screens.*


@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController,
    startDestination = Screen.Home1.route){


        composable(route=Screen.Search.route){
            SearchScreen(navController=navController)
        }
        composable(route = Screen.Add.route,
            arguments = listOf(navArgument("folderId") {
                type = NavType.IntType
            })
        ) {
            val folderId = it.arguments?.getInt("folderId")
            AddScreen(navController = navController, folderId = folderId)
        }
        composable(route=Screen.Folder.route){
            FolderScreen(navController=navController, viewModel())
        }
        composable(route=Screen.Note.route,
            arguments= listOf(navArgument("id"){
                type= NavType.IntType
            }, navArgument("folderId"){
                type= NavType.IntType
            }
        )){
            val noteId=it.arguments?.getInt("id")
            val folderId=it.arguments?.getInt("folderId")
            NoteScreen(navController=navController,id=noteId,folderId=folderId)
        }

        composable(route=Screen.Home1.route,
            arguments= listOf(navArgument("id"){
                type= NavType.IntType
                defaultValue=1
            })
        ){
            val Id=it.arguments?.getInt("id")
            HomeScreen1(navController=navController,id=Id)
        }

    }
}

