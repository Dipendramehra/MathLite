package com.dipendra.mathlitedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dipendra.mathlite.MathView
import com.dipendra.mathlitedemo.ui.theme.MathLiteDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MathLiteDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MathScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MathScreen(modifier: Modifier = Modifier) {

    val latex = """
        \int_0^1 x^2 \, dx = \frac{1}{3}
        <br><br>
        \frac{a+b}{c}
        <br><br>
        \alpha + \beta = \gamma
        <br><br>
        \sin \theta = \frac{\text{opposite}}{\text{hypotenuse}}
        <br><br>
        \tan \theta = \frac{\sin \theta}{\cos \theta}
        <br><br>
        \begin{bmatrix}
        a & b \\
        c & d
        \end{bmatrix}
    """.trimIndent()

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            MathView(context).apply {
                setLatex(latex)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMathScreen() {
    MathLiteDemoTheme {
        MathScreen()
    }
}