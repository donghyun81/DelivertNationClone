package com.example.deliverynationclone.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.deliverynationclone.R

inline fun <reified T : Fragment> FragmentManager.replace() {
    commit {
        replace<T>(R.id.fc_main)
        setReorderingAllowed(true)
    }
}