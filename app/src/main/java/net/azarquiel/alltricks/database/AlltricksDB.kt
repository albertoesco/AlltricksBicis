package net.azarquiel.alltricks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.azarquiel.alltricks.model.Bici
import net.azarquiel.alltricks.model.Marca

@Database(entities = [Bici::class, Marca::class], version = 1)
abstract class AlltricksDB: RoomDatabase() {
    abstract fun biciDao(): BiciDao
    abstract fun marcaDao(): MarcaDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  AlltricksDB? = null

        fun getDatabase(context: Context): AlltricksDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlltricksDB::class.java,   "alltricks.sqlite"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
