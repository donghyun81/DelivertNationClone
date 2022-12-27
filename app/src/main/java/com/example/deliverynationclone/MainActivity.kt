package com.example.deliverynationclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import com.example.deliverynationclone.databinding.ActivityMainBinding
import com.example.deliverynationclone.ext.replace
import com.example.deliverynationclone.fragment.*
import com.example.deliverynationclone.viewmodel.DeliveryHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityMainBinding

    private val deliveryHomeViewModel: DeliveryHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbMain)

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


    override fun onBackStackChanged() {
        changeAppBar()
    }

}