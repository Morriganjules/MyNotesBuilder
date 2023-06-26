package com.example.mynotesbuilder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.example.mynotesbuilder.ui.theme.MyNotesBuilderTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var title by remember {
                        mutableStateOf("")
                    }
                    var description by remember {
                        mutableStateOf("")
                    }

                    var selectedColor by remember {
                        mutableStateOf("")
                    }

                    val notes = remember { mutableStateListOf<Note>() }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        TextField(
                            value = title,
                            onValueChange = { newText ->
                                title = newText
                            },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        TextField(
                            value = description,
                            onValueChange = { newText ->
                                description = newText
                            },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        LazyRow{
                            items(5){
                                RadioButton(selected = selectedColor == Colors.RED.color, onClick = { selectedColor = Colors.RED.color })
                            }
                        }

                        Button(onClick = {
                            if (title.isNotBlank() && description.isNotBlank())
                                notes.add(
                                    Note.NoteBuilder()
                                        .setTitle(title)
                                        .setDescription(description)
                                        .build()
                                )
                            title = ""
                            description = ""
                        }) {
                            Text(text = "Agregar nota")
                        }
                        LazyColumn {
                            items(notes) {
                                NoteItem(note = it)
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 4.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ){
        Row() {

        }
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            note.title?.let { Text(text = it, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Monospace) }
            Spacer(modifier = Modifier.height(8.dp))
            note.description?.let { Text(text = it) }
        }
}
}

@Composable
fun ColorItem(){
  
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyNotesBuilderTheme {
    }
}