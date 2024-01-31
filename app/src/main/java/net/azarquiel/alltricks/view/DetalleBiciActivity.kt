package net.azarquiel.alltricks.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import net.azarquiel.alltricks.R
import net.azarquiel.alltricks.adapter.AdapterBici
import net.azarquiel.alltricks.databinding.ActivityDetalleBiciBinding
import net.azarquiel.alltricks.model.Bici
import net.azarquiel.alltricks.model.Marca
import net.azarquiel.alltricks.viewmodel.BiciViewModel

class DetalleBiciActivity : AppCompatActivity() {

    private lateinit var marca: Marca
    private lateinit var bici: Bici
    private lateinit var binding: ActivityDetalleBiciBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalleBiciBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Alltricks"

        binding.contentdetallebici.ivDetalleFav.setOnClickListener{
            onClickFav()
        }

        bici = intent.getSerializableExtra("bici") as Bici
        marca = intent.getSerializableExtra("marca") as Marca
        setData()
    }

    private fun onClickFav() {
        bici.fav = if (bici.fav == 0) 1 else 0

        val adapterBicis = AdapterBici(this, R.layout.row_bici)
        var biciViewModel = ViewModelProvider(this).get(BiciViewModel::class.java)
        biciViewModel.updateBici(bici.id, bici.fav)
        binding.contentdetallebici.ivDetalleFav.setImageResource(if (bici.fav == 0) android.R.drawable.star_big_off else android.R.drawable.star_big_on)
    }

    private fun setData() {
        binding.contentdetallebici.tvDescripcion.text = bici.descripcion
        binding.contentdetallebici.tvPrecio.text = bici.precio
        binding.contentdetallebici.tvMarca.text = marca.nombre
        binding.contentdetallebici.ivDetalleFav.setImageResource(if (bici.fav == 0) android.R.drawable.star_big_off else android.R.drawable.star_big_on)
        var foto = findViewById<ImageView>(R.id.ivFoto)
        Picasso.get().load(bici.foto).into(foto)
    }
}