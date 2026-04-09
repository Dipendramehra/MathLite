package com.dipendra.mathlitedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dipendra.mathlite.DiagramView
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
        \\ \\
        \frac{a+b}{c}
        \\ \\
        \alpha + \beta = \gamma
        \\ \\
        \sin \theta = \frac{\text{opposite}}{\text{hypotenuse}}
        \\ \\
        \tan \theta = \frac{\sin \theta}{\cos \theta}
        \\ \\
        \begin{bmatrix}
        a & b \\
        c & d
        \end{bmatrix}
    """.trimIndent()

    val triangle = """
    {
      "type": "triangle",
      "points": [
        {"x":50, "y":300},
        {"x":200, "y":50},
        {"x":350, "y":300}
      ]
    }
    """.trimIndent()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                MathView(context).apply {
                    setLatex(latex)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        AndroidView(
            modifier = Modifier.fillMaxWidth().weight(1f),
            factory = { context ->
                DiagramView(context).apply {
                    setDiagram(triangle)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMathScreen() {
    MathLiteDemoTheme {
        MathScreen()
    }
}