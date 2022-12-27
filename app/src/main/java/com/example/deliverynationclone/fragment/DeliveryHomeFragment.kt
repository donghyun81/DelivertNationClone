package com.example.deliverynationclone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deliverynationclone.databinding.FragmentDeliveryHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryHomeFragment : Fragment() {

    // onDestroyView를 통해서 null처리가 필요하기 때문에 null을 받을수 있지만
    // null exception을 발생할 여지가 있기 때문에 _binding을 대입 받아서 사용한다

    private var _binding:FragmentDeliveryHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


}