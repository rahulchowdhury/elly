package co.rahulchowdhury.elly.data.source.local.elephant

import androidx.room.Database
import androidx.room.RoomDatabase
import co.rahulchowdhury.elly.data.model.local.Elephant

@Database(entities = [Elephant::class], version = 1)
abstract class ElephantDatabase : RoomDatabase() {
    abstract fun elephantDao(): ElephantDao
}
