import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.registromascotas.items.ScreenA
import com.example.registromascotas.items.ScreenB
import com.example.registromascotas.items.ScreenEditMascota
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: MascotaViewModel = viewModel()



    NavHost(navController = navController, startDestination = "screenA") {
        composable("screenA") {
            ScreenA(navController, viewModel)
        }
        composable("screenB") {
            ScreenB(navController, viewModel)
        }
        composable("screenEdit/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            ScreenEditMascota(navController, viewModel, index)
        }
    }
}
data class Mascota(
    val nombre: String,
    val raza: String,
    val tamaño: String,
    val edad: String,
    val fotoUrl: String
)

class MascotaViewModel : ViewModel() {
    var listaMascotas by mutableStateOf(listOf<Mascota>())
        private set

    fun agregarMascota(mascota: Mascota) {
        listaMascotas = listaMascotas + mascota
    }

    fun eliminarMascota(index: Int) {
        listaMascotas = listaMascotas.toMutableList().also { it.removeAt(index) }
    }

    fun editarMascota(index: Int, nuevaMascota: Mascota) {
        listaMascotas = listaMascotas.toMutableList().also { it[index] = nuevaMascota }
    }
}
