package com.example.passion.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.passion.R
import com.example.passion.data.models.User
import com.example.passion.databinding.FragmentLoginsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_logins) {

    private lateinit var _binding: FragmentLoginsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginsBinding.bind(view)
        _binding.loginBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                User(
                    _binding.emailEt.editText?.text.toString(),
                    _binding.passwordEt.editText?.text.toString()
                )
            )
            findNavController().navigate(action)
        }

    }

}