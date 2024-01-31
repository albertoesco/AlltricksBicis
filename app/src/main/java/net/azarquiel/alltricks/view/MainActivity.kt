package net.azarquiel.alltricks.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.alltricks.R
import net.azarquiel.alltricks.adapter.AdapterMarca
import net.azarquiel.alltricks.model.Marca
import net.azarquiel.alltricks.util.Util
import net.azarquiel.alltricks.viewmodel.BiciViewModel
import net.azarquiel.alltricks.viewmodel.MarcaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var rvMarcas: RecyclerView
    private lateinit var adapterMarcas: AdapterMarca
    private lateinit var biciViewModel: BiciViewModel
    private lateinit var marcaViewModel: MarcaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Util.inyecta(this, "alltricks.sqlite")
        initRV()
        getDatos()
    }

    private fun initRV() {
        rvMarcas = findViewById(R.id.rvMarcas)
        adapterMarcas = AdapterMarca(this, R.layout.row_marca)
        rvMarcas.adapter = adapterMarcas
        rvMarcas.layoutManager = LinearLayoutManager(this)
    }

    private fun getDatos() {
        marcaViewModel = ViewModelProvider(this).get(MarcaViewModel::class.java)
        marcaViewModel.getAllMarcas().observe(this, Observer { marcas ->
            // Update the cached copy of the marcas in the adapter.
            marcas?.let { adapterMarcas.setMarcas(it) }
        })
    }

    fun onClickMarca(v: View) {
        val marca = v.tag as Marca

        val intent = Intent(this, BicisActivity::class.java)
        intent.putExtra("marca", marca)
        startActivity(intent)
    }
}