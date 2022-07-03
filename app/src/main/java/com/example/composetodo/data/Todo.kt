package com.example.composetodo.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Todo {
    var name: String by mutableStateOf("")

    var completed: Boolean by mutableStateOf(false)
}