package com.example.mercadolibre.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mercadolibre.databinding.FragmentMainBinding
import com.example.mercadolibre.helpers.NetworkHelper

/**
 * Primer fragment en mostrarse en la aplicación y que sirve para realizar la búsqueda de productos
 * @author Axel Sanchez
 */
class MainFragment: Fragment() {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val binding get() = fragmentMainBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (binding.search.text.isNullOrEmpty()) Toast.makeText(context, "Debe ingresar su búsqueda", Toast.LENGTH_SHORT).show()
            else{
                if (id == EditorInfo.IME_ACTION_SEARCH || id == EditorInfo.IME_NULL) {

                    if (NetworkHelper.isOnline(requireContext())) {
                        val action = MainFragmentDirections.toSearchFragment(binding.search.text.toString())
                        findNavController().navigate(action)
                    } else {
                        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(binding.search.windowToken, 0)
                        Toast.makeText(requireContext(), "Por favor revise su conexión a internet e intente nuevamente", Toast.LENGTH_SHORT)
                            .show()
                    }

                    return@OnEditorActionListener true
                }
            }
            false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }
}