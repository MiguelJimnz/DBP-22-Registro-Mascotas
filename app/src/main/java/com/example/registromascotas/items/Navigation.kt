import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registromascotas.items.ScreenA
import com.example.registromascotas.items.ScreenB





@Composable
fun Navigation() {
val navController: NavHostController = rememberNavController()

NavHost(navController, startDestination = "ScreenA") {
    composable("ScreenA") {
        ScreenA(navController)
    }
    composable("ScreenB") {
        ScreenB(navController)
    }
}
}
