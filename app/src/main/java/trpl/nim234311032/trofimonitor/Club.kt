package trpl.nim234311032.trofimonitor

data class Club(
    val name: String,
    val premierLeague: Int,
    val faCup: Int,
    val eflCup: Int,
    val championsLeague: Int,
    val europaLeague: Int
) {
    val totalTrophies: Int
        get() = premierLeague + faCup + eflCup + championsLeague + europaLeague
}
