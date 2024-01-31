package net.azarquiel.alltricks.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import net.azarquiel.alltricks.model.Bici
import net.azarquiel.alltricks.model.Marca

@Dao
interface BiciDao {
    @Query("SELECT * FROM bici ORDER BY marca ASC")
    fun getAllBicis(): LiveData<List<Bici>>

    @Query("SELECT * FROM bici WHERE marca = :marca")
    fun getBicisByMarca(marca: Int): LiveData<List<Bici>>

    @Query("SELECT * FROM bici WHERE id = :id")
    fun getBiciById(id:Int): LiveData<Bici>

    @Query("UPDATE bici SET fav = :fav WHERE id = :id")
    fun updateBici(id: Int, fav:Int)
}

@Dao
interface MarcaDao {
    @Query("SELECT * from marca ORDER BY nombre ASC")
    fun getAllMarcas(): LiveData<List<Marca>>

    @Query("SELECT * from marca where id = :id")
    fun getMarcaById(id:Int): LiveData<Marca>
}

