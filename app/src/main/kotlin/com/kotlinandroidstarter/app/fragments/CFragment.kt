package com.kotlinandroidstarter.app.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_c.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.viewmodels.SharedViewModel


class CFragment : LifecycleFragment() {
    
    private val TAG: String = AFragment::class.java.simpleName
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_c, container, false)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        ViewModelProviders
            .of(activity)
            .get(SharedViewModel::class.java)
            .getTestValue().observe(this, Observer {
                //someTextView.text = it
            })
        
    }
    
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        textFragmentC.text = "Fragment C"
        
        /*val model = ViewModelProviders.of(activity).get(SharedViewModel::class.java)
        model.setTestValue("Testing")*/
        
    }
    
}