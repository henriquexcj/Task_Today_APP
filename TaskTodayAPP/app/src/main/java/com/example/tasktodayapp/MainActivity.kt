package com.example.tasktodayapp

import android.graphics.Color
import android.graphics.fonts.FontStyle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import androidx.activity.ComponentActivity
import com.google.android.material.bottomappbar.BottomAppBar
import org.w3c.dom.Text
import java.lang.reflect.Modifier
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent()
        }
    }
}

@Composable
fun MainScreenContent(drawerState: DrawerState){
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {Text(text = "TaskTodayApp")},
                navigationIcon = {
                    Iconbutton(onClick = {
                        CouroutineScope(Dispatchers.Default).launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                                imageVector = Icons.Defalt.Menu,
                                contentDescription = "Drawer Menu"
                        )
                    }
                    }
                }
            )
        },
        drawerBackgroundColor = Color.RED,
        drewerGesturesEnable = drawerState.isOpen,
        drawerContent = {
            Box(
                    modifier = Modifier
                            .background(Color.MAGENTA)
                            .height(16.dp)
            ){
                Text(text = "Opcões!!")
            }
            Column(){
                Text(text = "Opcão de Menu 1")
                Text(text = "Opcão de Menu 2")
                Text(text = "Opcão de Menu 3")
            }
        }
        content = {
            paddingValues -> Log.i("paddinValues", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color.YELLOW)
                    .fillMaxSize()
            ){
                MySearchField(modificador = Modifier.fillMaxWidth())
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Preparar Aula LazyList/LazyColum",
                    taskDetails = "É bem melhor usar lazilist ao inves de colum",
                    deadEndDate = Date()
                )
                MyTaskWidget(modificador = Modifier.fillMaxWidth,
                    taskName = "Prova de Matemática",
                    taskDetails = "Estudar Cálculo capítulo 1 e 2",
                    deadEndDate = Date()
                )
                Text("Task01")
                Text("Task02")
                Text("Task03")
                Text("Task04")
            }
        },
        bottomBar = {
            BottomAppBar(
                content = { Text("asdf")}
            )
        }
    )
}

@Composable
fun MySearchField(modificador: Modifier){
    TextField(value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = {Text(text = "Pesquisar por tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        }
    )
}

@Composable
fun MyTaskWidget(
    modificador: Modifier,
    taskName: String,
    taskDetails: String,
    deadEndDate: Date
){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Icons of a pendent task"
            )
            Text(
                text = dateFormatter.format(deadEndDate),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )
        }
        Column(
            modifier = modificador
                .border(width = 1.dp, color = Color.BLACK)
                .padding(3.dp)
        ) {
            Text(
                text = taskName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = taskDetails,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    MainScreenContent()
}