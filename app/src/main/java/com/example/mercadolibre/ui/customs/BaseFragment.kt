package com.example.mercadolibre.ui.customs

import android.view.View
import androidx.fragment.app.Fragment
import com.example.mercadolibre.ui.interfaces.IOnBackPressFragment

/**
 * Fragment base de la aplicación que implementa una interface de retroceso entre fragments
 * @author Axel Sanchez
 */
abstract class BaseFragment : Fragment() , IOnBackPressFragment {
    /**
     * Extension Function que permite mostrar cualquier vista
     */
    fun View.show() { this.visibility = View.VISIBLE }

    /**
     * Extension Function que permite ocultar cualquier vista
     */
    fun View.hide() { this.visibility = View.GONE }
}