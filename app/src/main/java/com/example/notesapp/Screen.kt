package com.example.notesapp

sealed class Screen(val route:String){
    object Splash:Screen(route="splash_screen")
    object Home:Screen(route="home_screen")
    object Note:Screen(route = "note_screen/{id}/{folderId}")
    object Add:Screen(route = "add_screen/{folderId}")
    object Search:Screen(route="search_screen")
    object Folder:Screen(route="folder_screen")

    object Home1:Screen(route = "home1_screen/{id}")
}