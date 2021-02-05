package com.example.mercadolibre.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibre.R
import com.example.mercadolibre.common.hide
import com.example.mercadolibre.common.show
import com.example.mercadolibre.data.models.ResponseDTO.Product
import com.example.mercadolibre.databinding.FragmentSearchBinding
import com.example.mercadolibre.ui.adapter.ProductAdapter
import com.example.mercadolibre.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment que contiene un recyclerview de productos obtenidos de una búsqueda
 * @author Axel Sanchez
 */
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var query: String

    private var needUpdate = true

    private var fragmentSearchBinding: FragmentSearchBinding? = null
    private val binding get() = fragmentSearchBinding!!

    private lateinit var viewAdapter: ProductAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        query = SearchFragmentArgs.fromBundle(requireArguments()).query
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setQuery(query)
        setUpObserver()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpObserver() {
        viewModel.getSearchLiveData()
            .observe(viewLifecycleOwner, {
                it?.let { products ->
                    binding.progress.cancelAnimation()
                    binding.progress.hide()
                    if (products.isNotEmpty()) {
                        binding.emptyState.hide()
                        binding.recyclerview.show()
                        setAdapter(products)
                    } else{
                        binding.emptyState.show()
                        binding.recyclerview.hide()
                    }
                }?: kotlin.run {
                    binding.progress.cancelAnimation()
                    binding.progress.hide()
                    binding.emptyState.show()
                    binding.recyclerview.hide()
                    Toast.makeText(requireContext(), getString(R.string.failSearch), Toast.LENGTH_SHORT).show()
                }
            })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setAdapter(products: List<Product?>) {

        viewAdapter = ProductAdapter(products, itemClick)

        viewManager = LinearLayoutManager(this.requireContext())

        binding.recyclerview.apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    val itemClick = { product: Product?, imageView: ImageView ->
        product?.let {
            val bundle = bundleOf("idProduct" to it.id)
            val extras = FragmentNavigatorExtras(
                imageView to "imageView"
            )
            findNavController().navigate(R.id.toDetailsFragment, bundle, null, extras)
        }
    }

    override fun onResume() {
        super.onResume()
        if(needUpdate){
            binding.recyclerview.hide()
            binding.progress.playAnimation()
            binding.progress.show()
        }
    }

    override fun onPause() {
        super.onPause()
        needUpdate = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentSearchBinding = null
    }
}