package trpl.nim234311032.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentName = "Ardhiyonda Wahyu Putra"
        val studentId = "234311032"

        val initialClubs = listOf(
            Club("Liverpool", 19, 8, 10, 6, 3),
            Club("Manchester United", 20, 12, 6, 3, 1),
            Club("Arsenal", 13, 14, 2, 0, 0), // Defaulting to 0 instead of null
            Club("Manchester City", 9, 7, 8, 1, 0),
            Club("Chelsea", 6, 8, 5, 2, 2),
            Club("Tottenham Hotspur", 2, 8, 4, 0, 0)
        )

        setContent {
            var clubs by remember { mutableStateOf(initialClubs) }
            var showAddClubForm by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StudentInfoScreen(studentName, studentId)

                if (showAddClubForm) {
                    AddClubScreen(onClubAdded = { newClub ->
                        clubs = clubs + newClub
                        showAddClubForm = false // Hide form after adding club
                    })
                } else {
                    ClubListScreen(clubs)

                    // Button to show the add club form
                    Button(
                        onClick = { showAddClubForm = true },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Tambah Data Klub")
                    }

                    // Display clubs with more than 30 trophies
                    Text(
                        text = "Klub yang memiliki lebih dari 30 trofi:",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    clubs.filter { it.totalTrophies > 30 }.forEach { club ->
                        Text(
                            text = "${club.name}: ${club.totalTrophies} trofi",
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudentInfoScreen(studentName: String, studentId: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Nama: $studentName",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = "NIM: $studentId",
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun ClubListScreen(clubs: List<Club>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(clubs) { club ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display the logo for each club
                logoResId(club.name)

                Spacer(modifier = Modifier.width(16.dp))

                // Display club information
                Column {
                    Text(
                        text = club.name,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Total Trofi: ${club.totalTrophies}",
                        fontSize = 14.sp
                    )

                    if (club.championsLeague > 0) {
                        Text(
                            text = "Trofi internasional: ${club.championsLeague}",
                            fontSize = 12.sp
                        )
                    } else {
                        Text(
                            text = "${club.name} belum pernah memenangkan trofi internasional.",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubList() {
    val clubs = listOf(
        Club("Liverpool", 19, 8, 10, 6, 3),
        Club("Manchester United", 20, 12, 6, 3, 1),
        Club("Arsenal", 13, 14, 2, 0, 0),
        Club("Chelsea", 6, 8, 5, 2, 2),
        Club("Manchester City", 9, 7, 8, 1, 0),
        Club("Tottenham Hotspur", 2, 8, 4, 0, 0)
    )
    ClubListScreen(clubs)
}

@Composable
fun logoResId(clubName: String) {
    val logoResId = when (clubName) {
        "Liverpool" -> R.drawable.liverpool
        "Manchester United" -> R.drawable.manchester_united
        "Arsenal" -> R.drawable.arsenal
        "Chelsea" -> R.drawable.chelsea
        "Manchester City" -> R.drawable.manchester_city
        "Tottenham Hotspur" -> R.drawable.tottenham_hotspur
        else -> R.drawable.foto2 // Fallback image
    }

    Image(
        painter = painterResource(id = logoResId),
        contentDescription = "$clubName Logo",
        modifier = Modifier.size(64.dp).padding(end = 8.dp)
    )
}
