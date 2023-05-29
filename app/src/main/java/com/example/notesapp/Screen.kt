package com.example.notesapp

sealed class Screen(val route:String){
    object Splash:Screen(route="splash_screen")
    object Home:Screen(route="home_screen")
    object Note:Screen(route = "note_screen/{id}")
    object Add:Screen(route = "add_screen")
    object Search:Screen(route="search_screen")
}