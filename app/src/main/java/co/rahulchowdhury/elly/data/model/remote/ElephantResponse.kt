package co.rahulchowdhury.elly.data.model.remote

import co.rahulchowdhury.elly.data.model.local.Elephant
import com.google.gson.annotations.SerializedName

data class ElephantResponse(
    @SerializedName("_id")
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
) {
    fun toElephant() =
        Elephant(
            id = id,
            name = name,
            affiliation = affiliation,
            species = species,
            sex = sex,
            isFictional = fictional.toBoolean(),
            dateOfBirth = dob,
            dateOfDeath = dod,
            wikiLink = wikilink,
            image = image,
            note = note
        )
}
