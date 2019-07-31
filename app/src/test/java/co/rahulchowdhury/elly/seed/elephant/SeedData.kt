package co.rahulchowdhury.elly.seed.elephant

import co.rahulchowdhury.elly.data.model.local.Elephant

val seedElephant = Elephant(
    id = "1",
    name = "Elly",
    affiliation = "Happy Feet",
    lastFetchTime = 0,
    image = "elly-profile.jpg"
)

val seedElephants = listOf(
    seedElephant,
    Elephant(
        id = "2",
        name = "Jack",
        lastFetchTime = 0
    )
)
