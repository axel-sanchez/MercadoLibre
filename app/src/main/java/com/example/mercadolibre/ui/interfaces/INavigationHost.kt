package com.example.mercadolibre.ui.interfaces

import androidx.fragment.app.Fragment

/**
 * Interface destinada a la navegación entre fragments
 * @author Axel Sanchez
 */
interface INavigationHost {

    fun navigateTo(fragment: Fragment, addToBackstack: Boolean)

    fun replaceTo(fragment: Fragment, addToBackstack: Boolean)

    fun finish()
}