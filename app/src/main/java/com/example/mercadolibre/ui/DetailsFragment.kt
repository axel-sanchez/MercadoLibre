package com.example.mercadolibre.ui

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.mercadolibre.common.hide
import com.example.mercadolibre.common.show
import com.example.mercadolibre.databinding.FragmentDetailsBinding
import com.example.mercadolibre.domain.DetailsUseCase
import com.example.mercadolibre.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.android.ext.android.inject
import javax.inject.Inject

/**
 * Fragment para mostrar los datos de un producto
 * @author Axel Sanchez
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    var idProduct: String? = ""

    @Inject
    lateinit var detailsUseCase: DetailsUseCase

    private val viewModel: DetailsViewModel by viewModels(
        factoryProducer = { DetailsViewModel.DetailsViewModelFactory(detailsUseCase, idProduct?:"") }
    )

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

        idProduct = arguments?.getString("idProduct")

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        idProduct?.let {
            setUpViewModel()
        }
    }

    private fun setUpViewModel() {
        viewModel.getLocalProductLiveData()
            .observe(this, { product ->
                product?.let {
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
            })
    }
}