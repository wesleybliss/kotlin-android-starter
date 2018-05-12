package com.kotlinandroidstarter.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinandroidstarter.app.R
import kotlinx.android.synthetic.main.fragment_b.*


class BFragment : BaseFragment() {
    
    private val TAG: String = BFragment::class.java.simpleName
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_b, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        textFragmentB.text = "Fragment B"
        
    }
    
}
