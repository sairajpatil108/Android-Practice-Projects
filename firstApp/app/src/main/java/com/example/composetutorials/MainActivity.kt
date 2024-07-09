package com.example.composetutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorials.ui.theme.ComposetutorialsTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			ComposetutorialsTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Dice(modifier = Modifier.padding(innerPadding))
				}
			}
		}
	}
}

@Preview
@Composable
fun Dice(modifier: Modifier = Modifier) {
	var imageNumber by remember { mutableStateOf(1) }
	val animatedImageNumber by androidx.compose.animation.core.animateIntAsState(
		targetValue = imageNumber,
		animationSpec = tween(durationMillis = 500)
	)

	Column(
		modifier = Modifier
			.fillMaxSize(),
		verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = IntegerToImage(animatedImageNumber),
			contentDescription = "Dice",
			modifier = Modifier.size(400.dp)
		)

		Spacer(modifier = Modifier.height(26.dp))

		OutlinedButton(modifier = Modifier.size(200.dp),
			onClick = { imageNumber = (1..6).random() }) {
			Text("Roll Dice")
		}
	}
}

@Composable
fun IntegerToImage(num: Int): Painter {
	return when (num) {
		1 -> painterResource(id = R.drawable.dice_1)
		2 -> painterResource(id = R.drawable.dice_2)
		3 -> painterResource(id = R.drawable.dice_3)
		4 -> painterResource(id = R.drawable.dice_4)
		5 -> painterResource(id = R.drawable.dice_5)
		6 -> painterResource(id = R.drawable.dice_6)
		else -> painterResource(id = R.drawable.dice_1)
	}
}
