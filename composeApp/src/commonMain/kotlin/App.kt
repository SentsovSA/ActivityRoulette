import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.lighthousegames.logging.logging
import viewmodel.ActivityViewModel
import viewmodel.ActivityViewModel.Companion.log

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val randomActivity = getViewModel(Unit, viewModelFactory { ActivityViewModel() })
        val randomActivityState by randomActivity.uiState.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (randomActivityState.info.activity != "") {
                Text(
                    text = "Activity: ",
                    modifier = Modifier.padding(5.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                (if (randomActivityState.info.accessibility != 0.0) randomActivityState.info.accessibility else "")?.let {
                    Text(
                        text = it.toString(),
                        color = Color.Black,
                        modifier = Modifier.padding(5.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
                (if (randomActivityState.info.activity != "") randomActivityState.info.activity else "")?.let {
                    Text(
                        text = it,
                        color = Color.Black
                    )
                }
                (if (randomActivityState.info.key != "") randomActivityState.info.key else "")?.let {
                    Text(
                        text = it,
                        color = Color.Black
                    )
                }
                (if (randomActivityState.info.link != "") randomActivityState.info.link else "")?.let {
                    Text(
                        text = it,
                        color = Color.Black
                    )
                }
                (if (randomActivityState.info.participants != 0) randomActivityState.info.participants else "")?.let {
                    Text(
                        text = it.toString(),
                        color = Color.Black
                    )
                }
                (if (randomActivityState.info.price != 0.0) randomActivityState.info.price else "")?.let {
                    Text(
                        text = it.toString(),
                        color = Color.Black
                    )
                }
                (if (randomActivityState.info.type != "") randomActivityState.info.type else "")?.let {
                    Text(
                        text = it,
                        color = Color.Black
                    )
                }
                Button(
                    onClick = {
                        randomActivity.refresh()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                    elevation = ButtonDefaults.elevation(10.dp)
                ) {
                    Text(
                        text = "Refresh",
                        color = Color.White
                    )
                }
            }
        }
    }
}


