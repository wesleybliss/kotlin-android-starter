package com.kotlinandroidstarter.app.features.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.databinding.FragmentAboutBinding
import com.kotlinandroidstarter.app.shared.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment : BaseFragment<FragmentAboutBinding>(R.layout.fragment_about) {
    
    private val vm: AboutViewModel by viewModel()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        
        super.onCreateView(inflater, container, savedInstanceState)
        
        binding.vm = vm
        
        return binding.root
        
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
        
    }
    
}
