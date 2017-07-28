package com.kotlinandroidstarter.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_b.*
import com.kotlinandroidstarter.app.R


class BFragment : Fragment() {
    
    private val TAG: String = BFragment::class.java.simpleName
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_b, container, false)
    
    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)
    
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        textFragmentB.text = "Fragment B"
        
    }
    
}
