package com.example.mercadolibre.ui

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.mercadolibre.data.models.MyResponse.Producto
import com.example.mercadolibre.databinding.FragmentDetailsBinding
import com.example.mercadolibre.viewmodel.DetailsViewModel
import com.example.mercadolibre.viewmodel.DetailsViewModelFactory
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

const val ARG_ID_PRODUCTO = "id_producto"

/**
 * Fragment para mostrar los datos de un producto
 * @author Axel Sanchez
 */
class DetailsActivity: AppCompatActivity() {

    var idProducto = ""
    private var fragmentDetailsBinding: FragmentDetailsBinding? = null
    private lateinit var binding: FragmentDetailsBinding

    private val viewModelFactory: DetailsViewModelFactory by inject()
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(DetailsViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.image.transitionName = "search"

        idProducto = intent.extras!!.getString("id")?.let { it }?:""

        lifecycleScope.launch {
            viewModel.getLocalProduct(idProducto)
        }

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val myObserver = Observer<Producto?> { producto ->

            producto?.let {
                it.title?.let { title ->
                    binding.title.text = title
                } ?: binding.title.showView(false)

                it.thumbnail?.let { urlImagen ->
                    Glide.with(this)
                        .load(urlImagen)
                        .into(binding.image)
                } ?: binding.image.showView(false)

                it.available_quantity?.let { availableQuatity ->
                    if (availableQuatity > 0) binding.availableQuantity.text = "Unidades disponibles: $availableQuatity"
                } ?: binding.availableQuantity.showView(false)

                it.price?.let { price ->
                    binding.price.text = "$${price.toFloat()}"
                } ?: binding.price.showView(false)

                it.shipping?.let { shipping -> shipping.free_shipping }
                    ?.let { freeShipping ->
                        if (freeShipping) binding.freeShipping.showView(true)
                        else binding.freeShipping.showView(false)
                    } ?: binding.freeShipping.showView(false)

                it.original_price?.let { originalPrice ->
                    if (originalPrice.toFloat() > 0.0f) {
                        binding.originalPrice.text = "$${originalPrice}"
                        binding.originalPrice.paintFlags = binding.originalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else binding.originalPrice.showView(false)
                } ?: binding.originalPrice.showView(false)

                it.seller?.let { seller ->

                    seller.seller_reputation?.let { reputation -> reputation.transactions }
                        ?.let { transactions -> transactions.completed }
                        ?.let { completed ->
                            binding.soldProducts.text = "$completed ventas"
                        } ?: binding.soldProducts.showView(false)

                    seller.eshop?.let { eshop ->
                        eshop.nick_name?.let { name ->
                            binding.nameSeller.text = name
                        } ?: binding.nameSeller.showView(false)

                        eshop.eshop_logo_url?.let { logoUrl ->
                            Glide.with(this)
                                .load(logoUrl)
                                .into(binding.logoSeller)
                        } ?: binding.logoSeller.showView(false)
                    } ?: kotlin.run {
                        binding.nameSeller.showView(false)
                        binding.logoSeller.showView(false)
                    }
                } ?: kotlin.run {
                    binding.soldProducts.showView(false)
                    binding.nameSeller.showView(false)
                    binding.logoSeller.showView(false)
                }

                it.seller_address?.let { address ->
                    var addressSeller = ""
                    address.city?.let { city -> city.name }
                        ?.let { name -> addressSeller += name }
                    address.state?.let { state -> state.name }
                        ?.let { name -> addressSeller += ", $name" }
                    address.country?.let { country -> country.name }
                        ?.let { name -> addressSeller += ", $name" }
                    binding.adressSeller.text = addressSeller
                } ?: binding.adressSeller.showView(false)
            }

        }
        viewModel.getLocalProductLiveData().observe(this, myObserver)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBackPressed() {
        finishAfterTransition()
    }

    private fun View.showView(show: Boolean){
        if(show) this.visibility = View.VISIBLE
        else this.visibility = View.GONE
    }
}