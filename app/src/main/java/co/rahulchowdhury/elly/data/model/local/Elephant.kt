package co.rahulchowdhury.elly.data.model.local

data class Elephant(
    private val name: String,
    private val affiliation: String,
    private val species: String,
    private val sex: String,
    private val isFictional: Boolean,
    private val dateOfBirth: String,
    private val dateOfDeath: String,
    private val wikiLink: String,
    private val image: String,
    private val note: String
)
