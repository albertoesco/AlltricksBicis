package net.azarquiel.alltricks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.alltricks.R
import net.azarquiel.alltricks.model.Bici
import net.azarquiel.alltricks.model.Marca

class AdapterMarca(val context: Context,
                       val layout: Int
) : RecyclerView.Adapter<AdapterMarca.ViewHolder>() {

    private var dataList: List<Marca> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setMarcas(marcas: List<Marca>) {
        this.dataList = marcas
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Marca){
            val tvrowmarca = itemView.findViewById(R.id.tvRowMarca) as TextView

            tvrowmarca.text = dataItem.nombre

            itemView.tag = dataItem
        }
    }
}

class AdapterBici(val context: Context,
                       val layout: Int
) : RecyclerView.Adapter<AdapterBici.ViewHolder>() {

    private var dataList: List<Bici> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setBicis(bicis: List<Bici>) {
        this.dataList = bicis
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Bici){
            val tvrowbici = itemView.findViewById(R.id.tvRowBici) as TextView
            val ivrowbici = itemView.findViewById(R.id.ivRowBici) as ImageView
            val ivFav = itemView.findViewById(R.id.ivRowBiciFav) as ImageView

            tvrowbici.text = dataItem.descripcion
            ivFav.setImageResource(if (dataItem.fav == 0) android.R.drawable.star_off else android.R.drawable.star_on)

            // load image from url
            var url = dataItem.foto
            Picasso.get()
                .load(url)
                .into(ivrowbici)

            itemView.tag = dataItem
        }
    }
}