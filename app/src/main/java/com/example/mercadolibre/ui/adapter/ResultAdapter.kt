package com.example.mercadolibre.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercadolibre.data.models.search.Result
import com.example.mercadolibre.databinding.ItemProductoBinding

/**
 * Clase que adapta el recyclerview de [SitiesFragment]
 * @author Axel Sanchez
 */
class ResultAdapter(
    private var mItems: List<Result?>,
    private var itemClick: (Result?) -> Unit) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result?, itemClick: (Result?) -> Unit) {

            item?.let { result ->
                itemView.setOnClickListener { itemClick(result) }

                binding.title.text = result.title

                binding.price.text = "$${result.price}"

                if(result.thumbnail.isNotEmpty())
                    Glide.with(itemView.context)
                        .load(item.thumbnail)
                        .into(binding.image)
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

    fun setItems(newItems: List<Result?>) {
        mItems = newItems
    }

    fun getItems(): List<Result?> {
        return mItems
    }
}