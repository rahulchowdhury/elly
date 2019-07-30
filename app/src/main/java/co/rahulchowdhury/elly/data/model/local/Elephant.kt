package co.rahulchowdhury.elly.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.rahulchowdhury.elly.util.Constants
import java.util.concurrent.TimeUnit

@Entity(tableName = "elephants")
data class Elephant(
    @PrimaryKey
    val id: String,
    val name: String,
    val affiliation: String = "",
    val species: String = "",
    val sex: String = "",
    val isFictional: Boolean = false,
    val dateOfBirth: String = "",
    val dateOfDeath: String = "",
    val wikiLink: String = "",
    val image: String = "",
    val note: String = "",
    val lastFetchTime: Long
)

fun Elephant.isStale() =
    TimeUnit
        .MILLISECONDS
        .toHours(System.currentTimeMillis() - lastFetchTime) > Constants.Time.FRESHNESS_PERIOD_IN_HOURS
