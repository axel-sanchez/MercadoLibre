package com.example.mercadolibre.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercadolibre.common.hide
import com.example.mercadolibre.data.models.MyResponse.Product
import com.example.mercadolibre.databinding.ItemProductBinding

/**
 * Clase que adapta el recyclerview de [SearchFragment]
 * @author Axel Sanchez
 */
class ProductAdapter(
    private var mItems: List<Product?>,
    private val itemClick: (Product?, ImageView) -> Unit?) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product?, itemClick: (Product?, ImageView) -> Unit?) {

            item?.let { product ->
                itemView.setOnClickListener {
                    itemClick(product, binding.image)
                }

                product.title?.let { title ->
                    binding.title.text = title
                }?: kotlin.run { binding.title.hide() }

                product.price?.let { price ->
                    binding.price.text = "$$price"
                }?: kotlin.run { binding.price.hide() }

                product.thumbnail?.let { urlImage ->
                    if(urlImage.isNotEmpty())
                        Glide.with(itemView.context)
                            .load(urlImage)
                            .into(binding.image)
                }?: kotlin.run { binding.image.hide() }
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val recyclerRowBinding: ItemProductBinding =
            ItemProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(recyclerRowBinding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(
        mItems[position],
        itemClick
    )

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mItems.size
}