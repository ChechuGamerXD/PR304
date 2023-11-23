package com.jesusaledo.pr304

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusaledo.pr304.ui.theme.PR304Theme
import kotlin.random.Random
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PR304Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Adivinar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Adivinar() {
    var numUsr by rememberSaveable {
        mutableStateOf("")
    }
    var secure by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Adivina el número del 1 al 10")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = numUsr,
            onValueChange = {numUsr = it; secure = false },
            label = { Text("Adivina el número") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                secure = numUsr.isNotBlank() && numUsr.toIntOrNull() != null && numUsr.toInt() in 0..10
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(bottom = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Adivinar")
        }
        if (secure) {
            val random = Random.nextInt(1, 11)
            if (numUsr.toInt() == 0)
                exitProcess(0)
            else if (numUsr.toInt() == random)
                Text("Acertaste, el número era $random")
            else
                Text("Has fallado, el número era $random")
        }
        else
            Text("Introduce un número entre 1 y 10 o \n0 para cerrar la aplicación")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PR304Theme {
        Adivinar()
    }
}