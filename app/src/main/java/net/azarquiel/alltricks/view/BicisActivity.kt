package net.azarquiel.alltricks.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.alltricks.R
import net.azarquiel.alltricks.adapter.AdapterBici
import net.azarquiel.alltricks.databinding.ActivityBicisBinding
import net.azarquiel.alltricks.model.Bici
import net.azarquiel.alltricks.model.Marca
import net.azarquiel.alltricks.viewmodel.BiciViewModel
import net.azarquiel.alltricks.viewmodel.MarcaViewModel

class BicisActivity : AppCompatActivity() {

    private lateinit var marca: Marca
    private lateinit var adapterBicis: AdapterBici
    private lateinit var biciViewModel: BiciViewModel
    private lateinit var binding: ActivityBicisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBicisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        marca = intent.getSerializableExtra("marca") as Marca
        binding.toolbar.title = marca.nombre

        initRV()
        getDatos()
    }

    private fun initRV() {
        adapterBicis = AdapterBici(this, R.layout.row_bici)
        binding.contentBicis.rvBicis.adapter = adapterBicis
        binding.contentBicis.rvBicis.layoutManager = LinearLayoutManager(this)
    }

    private fun getDatos() {
        biciViewModel = ViewModelProvider(this).get(BiciViewModel::class.java)
        biciViewModel.getBicisByMarca(marca.id).observe(this, Observer { bicis ->
            bicis?.let { adapterBicis.setBicis(it) }
        })
    }

    fun onClickBici(v: View) {
        val bici = v.tag as Bici
        val intent = Intent(this, DetalleBiciActivity::class.java)
        intent.putExtra("bici", bici)
        intent.putExtra("marca", marca)
        startActivity(intent)
    }
}