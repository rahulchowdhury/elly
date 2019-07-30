package co.rahulchowdhury.elly.data.model.remote

import co.rahulchowdhury.elly.data.model.local.Elephant
import com.google.gson.annotations.SerializedName

data class ElephantResponse(
    @SerializedName("_id")
    val id: String,
    val name: String? = "",
    val affiliation: String? = "",
    val species: String? = "",
    val sex: String? = "",
    val fictional: String? = "",
    val dob: String? = "",
    val dod: String? = "",
    val wikilink: String? = "",
    val image: String? = "",
    val note: String? = ""
)

fun ElephantResponse.toElephant() =
    Elephant(
        id = id,
        name = name ?: "N/A",
        affiliation = affiliation ?: "N/A",
        species = species ?: "N/A",
        sex = sex ?: "N/A",
        isFictional = fictional?.toBoolean() ?: false,
        dateOfBirth = dob ?: "N/A",
        dateOfDeath = dod ?: "N/A",
        wikiLink = wikilink ?: "N/A",
        image = image ?: "N/A",
        note = note ?: "N/A",
        lastFetchTime = System.currentTimeMillis()
    )
