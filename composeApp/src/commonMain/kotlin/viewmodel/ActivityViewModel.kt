package viewmodel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.request
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.read
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import model.RandomActivity
import org.lighthousegames.logging.logging

@Serializable
data class RandomActivityUIState(
    val info: RandomActivity = RandomActivity("", "", 0, 0.0, "", "", 0.0),
)

class ActivityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<RandomActivityUIState>(RandomActivityUIState())
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun onCleared() {
        httpClient.close()
    }

    init {
        updateInfo()
    }

    companion object {
        val log = logging()
    }

    private fun updateInfo() {
        viewModelScope.launch {
            val info = getInfo()
            _uiState.update {
                it.copy(info = info)
            }
        }
    }

    fun refresh() = updateInfo()

    private suspend fun getInfo(): RandomActivity {
        return try {
            httpClient
                .get("http://www.boredapi.com/api/activity/")
                .body()
        } catch (e: Exception) {
            log.i{e}
            RandomActivity("", "", 0, 0.0, "", "", 0.0)
        }
    }
}