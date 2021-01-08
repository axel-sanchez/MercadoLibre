package com.example.mercadolibre.ui

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.mercadolibre.common.hide
import com.example.mercadolibre.common.show
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.databinding.FragmentDetailsBinding
import com.example.mercadolibre.viewmodel.DetailsViewModel
import org.koin.android.ext.android.inject

/**
 * Fragment para mostrar los datos de un producto
 * @author Axel Sanchez
 */
class DetailsFragment: Fragment() {

    var idProduct = ""

    private val viewModelFactory: DetailsViewModel.DetailsViewModelFactory by inject()
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(DetailsViewModel::class.java)
    }

    private var fragmentMyBinding: FragmentDetailsBinding? = null
    private val binding get() = fragmentMyBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMyBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMyBinding = null
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idProduct = DetailsFragmentArgs.fromBundle(requireArguments()).idProduct

        viewModel.getLocalProduct(idProduct)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val myObserver = Observer<MyResponse.Product?> { producto ->

            producto?.let {
                it.title?.let { title ->
                    binding.title.text = title
                } ?: binding.title.hide()

                it.thumbnail?.let { urlImage ->
                    Glide.with(this)
                        .load(urlImage)
                        .into(binding.image)
                } ?: binding.image.hide()

                it.available_quantity?.let { availableQuatity ->
                    if (availableQuatity > 0) binding.availableQuantity.text = "Unidades disponibles: $availableQuatity"
                } ?: binding.availableQuantity.hide()

                it.price?.let { price ->
                    binding.price.text = "$${price.toFloat()}"
                } ?: binding.price.hide()

                it.shipping?.let { shipping -> shipping.free_shipping }
                    ?.let { freeShipping ->
                        if (freeShipping) binding.freeShipping.show()
                        else binding.freeShipping.hide()
                    } ?: binding.freeShipping.hide()

                it.original_price?.let { originalPrice ->
                    if (originalPrice.toFloat() > 0.0f) {
                        binding.originalPrice.text = "$${originalPrice}"
                        binding.originalPrice.paintFlags = binding.originalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else binding.originalPrice.hide()
                } ?: binding.originalPrice.hide()

                it.seller?.let { seller ->

                    seller.seller_reputation?.let { reputation -> reputation.transactions }
                        ?.let { transactions -> transactions.completed }
                        ?.let { completed ->
                            binding.soldProducts.text = "$completed ventas"
                        } ?: binding.soldProducts.hide()

                    seller.eshop?.let { eshop ->
                        eshop.nick_name?.let { name ->
                            binding.nameSeller.text = name
                        } ?: binding.nameSeller.hide()

                        eshop.eshop_logo_url?.let { logoUrl ->
                            Glide.with(this)
                                .load(logoUrl)
                                .into(binding.logoSeller)
                        } ?: binding.logoSeller.hide()
                    } ?: kotlin.run {
                        binding.nameSeller.hide()
                        binding.logoSeller.hide()
                    }
                } ?: kotlin.run {
                    binding.soldProducts.hide()
                    binding.nameSeller.hide()
                    binding.logoSeller.hide()
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
                } ?: binding.adressSeller.hide()
            }

        }
        viewModel.getLocalProductLiveData().observe(this, myObserver)
    }
}