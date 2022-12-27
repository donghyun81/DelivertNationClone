package com.example.deliverynationclone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deliverynationclone.databinding.FragmentDibsOnBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DibsOnFragment : Fragment() {

    private var _binding: FragmentDibsOnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDibsOnBinding.inflate(inflater, container, false)
        return binding.root
    }
}