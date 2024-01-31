package net.azarquiel.alltricks.database

import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.alltricks.model.Bici
import net.azarquiel.alltricks.model.Marca

class BiciRepository(application: Application) {

    val biciDao = AlltricksDB.getDatabase(application).biciDao()
    // select
    fun getAllBicis(): LiveData<List<Bici>> {
        return biciDao.getAllBicis()
    }
    fun getBicisByMarca(marca : Int): LiveData<List<Bici>> {
        return biciDao.getBicisByMarca(marca)
    }
    fun getBiciById(id : Int): LiveData<Bici> {
        return biciDao.getBiciById(id)
    }

    // update
    fun updateBici(id: Int, fav: Int) {
        biciDao.updateBici(id, fav)
    }
}

class MarcaRepository(application: Application) {

    val marcaDao = AlltricksDB.getDatabase(application).marcaDao()
    // select
    fun getAllMarcas(): LiveData<List<Marca>> {
        return marcaDao.getAllMarcas()
    }
    fun getMarcaById(id : Int): LiveData<Marca> {
        return marcaDao.getMarcaById(id)
    }
}
