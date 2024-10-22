package trpl.nim234311032.trofimonitor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddClubScreen(onClubAdded: (Club) -> Unit) {
    var clubName by remember { mutableStateOf("") }
    var premierLeagueTrophies by remember { mutableStateOf("") }
    var faCupTrophies by remember { mutableStateOf("") }
    var eflCupTrophies by remember { mutableStateOf("") }
    var championsLeagueTrophies by remember { mutableStateOf("") }
    var europaLeagueTrophies by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = clubName,
            onValueChange = { clubName = it },
            label = { Text("Club Name") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = premierLeagueTrophies,
            onValueChange = { premierLeagueTrophies = it },
            label = { Text("Premier League Trophies") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = faCupTrophies,
            onValueChange = { faCupTrophies = it },
            label = { Text("FA Cup Trophies") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = eflCupTrophies,
            onValueChange = { eflCupTrophies = it },
            label = { Text("EFL Cup Trophies") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = championsLeagueTrophies,
            onValueChange = { championsLeagueTrophies = it },
            label = { Text("Champions League Trophies") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = europaLeagueTrophies,
            onValueChange = { europaLeagueTrophies = it },
            label = { Text("Europa League Trophies") },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                val club = Club(
                    name = clubName,
                    premierLeague = premierLeagueTrophies.toIntOrNull() ?: 0,
                    faCup = faCupTrophies.toIntOrNull() ?: 0,
                    eflCup = eflCupTrophies.toIntOrNull() ?: 0,
                    championsLeague = championsLeagueTrophies.toIntOrNull() ?: 0,
                    europaLeague = europaLeagueTrophies.toIntOrNull() ?: 0
                )
                onClubAdded(club)
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Add Club")
        }
    }
}
