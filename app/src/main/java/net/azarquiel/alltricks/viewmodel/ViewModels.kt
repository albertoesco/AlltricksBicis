package net.azarquiel.alltricks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.alltricks.database.BiciRepository
import net.azarquiel.alltricks.database.MarcaRepository
import net.azarquiel.alltricks.model.Bici
import net.azarquiel.alltricks.model.Marca

class BiciViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: BiciRepository = BiciRepository(application)

    fun getAllBicis(): LiveData<List<Bici>>{
        return repository.getAllBicis()
    }

    fun getBicisByMarca(marca: Int): LiveData<List<Bici>>{
        return repository.getBicisByMarca(marca)
    }

    fun getBiciById(id: Int): LiveData<Bici>{
        return repository.getBiciById(id)
    }

    fun updateBici(id: Int, fav: Int){
        GlobalScope.launch() {
            repository.updateBici(id,fav)
            launch(Dispatchers.Main) {
            }
        }
    }
}

class MarcaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: MarcaRepository = MarcaRepository(application)

    fun getAllMarcas(): LiveData<List<Marca>>{
        return repository.getAllMarcas()
    }

    fun getMarcaById(id: Int): LiveData<Marca>{
        return repository.getMarcaById(id)
    }
}
