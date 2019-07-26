package co.rahulchowdhury.elly.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.concurrent.TimeUnit

@Entity(tableName = "elephants")
data class Elephant(
    @PrimaryKey
    val id: String,
    val name: String,
    val affiliation: String,
    val species: String,
    val sex: String,
    val isFictional: Boolean,
    val dateOfBirth: String,
    val dateOfDeath: String,
    val wikiLink: String,
    val image: String,
    val note: String,
    val lastFetchTime: Long
)

fun Elephant.isStale() =
    TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - lastFetchTime) > 12
