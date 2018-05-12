package com.kotlinandroidstarter.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinandroidstarter.app.R
import kotlinx.android.synthetic.main.fragment_c.*


class CFragment : BaseFragment() {
    
    private val TAG: String = AFragment::class.java.simpleName
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_c, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        textFragmentC.text = "Fragment C"
        
    }
    
}
