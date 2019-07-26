package co.rahulchowdhury.elly.data.source.local.elephant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import co.rahulchowdhury.elly.data.model.local.Elephant

@Dao
interface ElephantDao {
    @Insert(onConflict = REPLACE)
    fun save(elephant: Elephant)

    @Query("SELECT * FROM elephants WHERE name = :elephantName")
    fun load(elephantName: String): LiveData<Elephant>
}
