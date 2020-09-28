package com.example.mercadolibre.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.mercadolibre.R
import com.example.mercadolibre.ui.customs.BaseFragment
import com.example.mercadolibre.ui.interfaces.INavigationHost

/**
 * Activity principal de nuestra aplicación
 * @author Axel Sanchez
 */
class MainActivity: AppCompatActivity(), INavigationHost {

    private val INTERVAL = 2000 //2 segundos para salir
    private var tiempoPrimerClick: Long = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            val fragment: Fragment = MainFragment()

            replaceTo(fragment, false)
        }
    }

    override fun navigateTo(fragment: Fragment, addToBackstack: Boolean) {

        val transaction = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
            .add(R.id.container, fragment)
        if (addToBackstack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun replaceTo(fragment: Fragment, addToBackstack: Boolean) {

        val transaction = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
            .replace(R.id.container, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun finish() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {

        val f = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment

        if (supportFragmentManager.backStackEntryCount > 0) {

            if (f.childFragmentManager.backStackEntryCount > 1) {
                f.childFragmentManager.popBackStack()
            } else {
                super.onBackPressed()
            }
        } else{
            if (tiempoPrimerClick + INTERVAL > System.currentTimeMillis()) {
                super.finish()
                return
            } else {
                Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show()
            }
            tiempoPrimerClick = System.currentTimeMillis()
        }
    }
}