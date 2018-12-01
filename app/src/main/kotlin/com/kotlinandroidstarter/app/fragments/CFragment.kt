package com.kotlinandroidstarter.app.fragments

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_c.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CFragment : Fragment() {

    private val vm: SharedViewModel by viewModel()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_c, container, false)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        vm.getTestValue().observe(this, Observer {
            //someTextView.text = it
        })
        
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        textFragmentC.text = getString(R.string.fragment_c_body)
        
    }
    
}
