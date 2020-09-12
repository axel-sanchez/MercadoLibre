package com.example.mercadolibre.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mercadolibre.databinding.FragmentMainBinding
import com.example.mercadolibre.ui.customs.BaseFragment
import com.example.mercadolibre.ui.interfaces.INavigationHost

class MainFragment: BaseFragment() {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val binding get() = fragmentMainBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH || id == EditorInfo.IME_NULL) {

                (activity as INavigationHost).replaceTo(SearchFragment.newInstance(binding.search.text.toString()), true)

                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }

    override fun onBackPressFragment() = false
}