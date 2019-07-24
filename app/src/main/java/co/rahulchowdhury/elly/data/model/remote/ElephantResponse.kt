package co.rahulchowdhury.elly.data.model.remote

data class ElephantResponse(
    val id: String,
    val name: String,
    val affiliation: String,
    val species: String,
    val sex: String,
    val fictional: String,
    val dob: String,
    val dod: String,
    val wikilink: String,
    val image: String,
    val note: String
)
