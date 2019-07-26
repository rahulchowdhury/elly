package co.rahulchowdhury.elly.data.model.local

data class Elephant(
    val name: String,
    val affiliation: String,
    val species: String,
    val sex: String,
    val isFictional: Boolean,
    val dateOfBirth: String,
    val dateOfDeath: String,
    val wikiLink: String,
    val image: String,
    val note: String
)
