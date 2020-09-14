package com.example.mercadolibre.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolibre.data.models.MyResponse.Producto
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibre.databinding.FragmentSearchBinding
import com.example.mercadolibre.ui.adapter.ProductoAdapter
import com.example.mercadolibre.ui.customs.BaseFragment
import com.example.mercadolibre.ui.interfaces.INavigationHost
import com.example.mercadolibre.viewmodel.SearchViewModel
import com.example.mercadolibre.viewmodel.SearchViewModelFactory
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

const val ARG_QUERY = "query"

/**
 * @author Axel Sanchez
 */
class SearchFragment : BaseFragment() {

    private val viewModelFactory: SearchViewModelFactory by inject()
    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    private lateinit var query: String

    private var fragmentSearchBinding: FragmentSearchBinding? = null
    private val binding get() = fragmentSearchBinding!!

    private lateinit var viewAdapter: ProductoAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_QUERY, "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getSearch(query)
        }
        setUpObserver()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpObserver() {
        val myObserver = Observer<List<Producto?>?> {
            it?.let { Productos ->
                binding.progress.cancelAnimation()
                binding.progress.showView(false)
                if (Productos.isNotEmpty()) {
                    binding.emptyState.showView(false)
                    binding.recyclerview.showView(true)
                    setAdapter(Productos)
                } else binding.emptyState.showView(true)
            }
        }
        viewModel.getSearchLiveData()
            .observe(viewLifecycleOwner, myObserver)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setAdapter(movies: List<Producto?>) {

        viewAdapter = ProductoAdapter(movies, itemClick)

        viewManager = LinearLayoutManager(this.requireContext())

        binding.recyclerview.apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP) var itemClick = { producto: Producto?, imageView: ImageView ->
        producto?.let {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", it.id)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                imageView,
                "search"
            )
            startActivity(intent, options.toBundle())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentSearchBinding = null
    }

    override fun onBackPressFragment() = false

    companion object {
        @JvmStatic
        fun newInstance(query: String) = SearchFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_QUERY, query)
            }
        }
    }
}