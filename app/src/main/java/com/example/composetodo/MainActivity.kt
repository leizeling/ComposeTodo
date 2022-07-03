package com.example.composetodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetodo.data.DataCenter
import com.example.composetodo.data.Todo
import com.example.composetodo.ui.theme.ComposeTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTodoTheme {
                MainScreen()


                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
            }
        }
    }

}


@Composable
private fun MainScreen() {
    var inputting by remember {
        mutableStateOf(false)
    }
    val animatedFabScale by animateFloatAsState(
        if (inputting) 0f else 1f
    )
    val animatedInputScale by animateFloatAsState(
        if (inputting) 1f else 0f
    )
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            inputting = true
        }, Modifier.scale(animatedFabScale)) {
            Icon(Icons.Filled.Add, contentDescription = "添加")
        }
    }, topBar = {
        TopAppBar() {
            Icon(Icons.Filled.Check, contentDescription = "随便")
        }
    }) {
        Box(Modifier.fillMaxSize()) {
            Todos(DataCenter.todos)
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .scale(animatedInputScale)
            ) {
                TextField(value = "", onValueChange = {}, Modifier.weight(1f))
                IconButton(onClick = {
                    inputting = false
                }) {
                    Icon(Icons.Filled.Send, "确认")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
private fun Todos(todos: List<Todo>) {
    Surface(Modifier.padding(16.dp), elevation = 8.dp, shape = RoundedCornerShape(8.dp)) {
        LazyColumn(Modifier.fillMaxWidth()) {
//            val todos = listOf("吃饭", "喝水", "睡觉")
            items(todos) { todo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = todo.completed, onCheckedChange = { changed ->
                        todo.completed = changed
                    })
                    Text(todo.name)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun TodosPreview() {
    val todos = listOf<Todo>(
        Todo().apply {
            name = "吃饭"
        },
        Todo().apply {
            name = "上厕所"
        },
        Todo().apply {
            name = "休息"
        }
    )
    Todos(DataCenter.todos)
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ComposeTodoTheme {
//        Greeting("Android")
//    }
//}