package com.example.deliverynationclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.deliverynationclone.databinding.ActivityMainBinding
import com.example.deliverynationclone.ext.replace
import com.example.deliverynationclone.ext.replaceSlide
import com.example.deliverynationclone.fragment.*
import com.example.deliverynationclone.viewmodel.DeliveryHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    private val deliveryHomeViewModel: DeliveryHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbMain)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        initDeliveryHome()

    }


    private fun initDeliveryHome() {
        deliveryHomeViewModel.setMenu(R.menu.menu_delivery_home)
        supportFragmentManager.replace<DeliveryHomeFragment>()
        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    private fun changeAppBar() {
        val menuTriple = when (supportFragmentManager.findFragmentById(binding.fcMain.id)) {
            is DeliveryHomeFragment -> {
                Triple("", R.menu.menu_delivery_home, true)
            }
            is SearchFragment -> {
                Triple("", 0, true)
            }
            is DibsOnFragment -> {
                Triple("찜", 0, true)
            }
            is OrderHistoryFragment -> {
                Triple("주문내역", 0, true)
            }
            is MyDeliveryNationFragment -> {
                Triple("My배민", 0, true)
            }
            else -> {
                Triple("바보", R.menu.menu_delivery_home, false)
            }
        }
        setToolbarItem(menuTriple.first, menuTriple.second, menuTriple.third)
    }

    private fun setToolbarItem(title: String, menuResource: Int, backEnabled: Boolean) {
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(backEnabled)
            actionBar.title = title
        }
        deliveryHomeViewModel.setMenu(menuResource)
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (deliveryHomeViewModel.getMenu()) {
            R.menu.menu_delivery_home -> {
                menuInflater.inflate(deliveryHomeViewModel.getMenu(), menu)
                menu?.let {
                    val home = it.getItem(0)
                    val homeView = home.actionView
                    homeView.setOnClickListener {
                        supportFragmentManager.replaceSlide<DibsOnFragment>(
                            "DeliveryHomeToHome"
                        )
                    }

                    val cart = it.getItem(0)
                    val cartView = cart.actionView
                    cartView.setOnClickListener {
                        supportFragmentManager.replaceSlide<MyDeliveryNationFragment>(
                            "DeliveryHomeToCart"
                        )
                    }
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        with(supportFragmentManager) {
            popBackStack()
        }
        return true
    }

    override fun onBackStackChanged() {
        changeAppBar()
    }

}