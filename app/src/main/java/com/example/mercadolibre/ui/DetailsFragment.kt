package com.example.mercadolibre.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mercadolibre.data.models.search.Result
import com.example.mercadolibre.databinding.FragmentDetailsBinding
import com.example.mercadolibre.ui.customs.BaseFragment

const val ARG_RESULT = "result"

/**
 * Fragment para mostrar los datos de un producto
 * @author Axel Sanchez
 */
class DetailsFragment : BaseFragment() {

    var result: Result? = null

    private var fragmentDetailsBinding: FragmentDetailsBinding? = null
    private val binding get() = fragmentDetailsBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            result = it.getParcelable(ARG_RESULT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        result?.let {
            binding.title.text = it.title
            Glide.with(requireContext()).load(it.thumbnail).into(binding.image)
            if (it.available_quantity > 0) binding.availableQuantity.text =
                "Unidades disponibles: ${it.available_quantity}"

            binding.price.text = "$${it.realPrice}"

            binding.soldProducts.text =
                "${it.seller.seller_reputation.transactions.completed} ventas"

            if (it.shipping.free_shipping) binding.freeShipping.showView(true)
            else binding.freeShipping.showView(false)

            if (it.realOriginalPrice > 0.0f) {
                binding.originalPrice.text = "$${it.original_price}"
                binding.originalPrice.paintFlags =
                    binding.originalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else binding.originalPrice.showView(false)

            binding.nameSeller.text = it.seller.eshop.nick_name

            Glide.with(requireContext()).load(it.seller.eshop.eshop_logo_url)
                .into(binding.logoSeller)

            binding.adressSeller.text =
                "${it.seller_address.city.name}, ${it.seller_address.state.name}, ${it.seller_address.country.name}"

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDetailsBinding = null
    }

    override fun onBackPressFragment() = false

    companion object {
        @JvmStatic
        fun newInstance(result: Result) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_RESULT, result)
            }
        }
    }
}