package com.example.mercadolibre.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercadolibre.data.models.MyResponse.Producto
import com.example.mercadolibre.databinding.ItemProductoBinding

/**
 * Clase que adapta el recyclerview de [SitiesFragment]
 * @author Axel Sanchez
 */
class ProductoAdapter(
    private var mItems: List<Producto?>,
    private var itemClick: (Producto?, ImageView) -> Unit?) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Producto?, itemClick: (Producto?, ImageView) -> Unit?) {

            item?.let { producto ->
                itemView.setOnClickListener {
                    itemClick(producto, binding.image)
                }

                producto.title?.let { title ->
                    binding.title.text = title
                }?: kotlin.run { binding.title.visibility = View.GONE }

                producto.price?.let { price ->
                    binding.price.text = "$$price"
                }?: kotlin.run { binding.price.visibility = View.GONE }

                producto.thumbnail?.let { urlImagen ->
                    if(urlImagen.isNotEmpty())
                        Glide.with(itemView.context)
                            .load(item.thumbnail)
                            .into(binding.image)
                }?: kotlin.run { binding.image.visibility = View.GONE }
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val recyclerRowBinding: ItemProductoBinding =
            ItemProductoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(recyclerRowBinding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(
        mItems[position],
        itemClick
    )

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mItems.size

    fun setItems(newItems: List<Producto?>) {
        mItems = newItems
    }

    fun getItems(): List<Producto?> {
        return mItems
    }
}