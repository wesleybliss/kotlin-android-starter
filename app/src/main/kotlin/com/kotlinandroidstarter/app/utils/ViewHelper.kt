package com.kotlinandroidstarter.app.utils

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


object ViewHelper {
    
    fun setupRecyclerView(context: Context, recyclerView: RecyclerView) {
        
        val layoutManager = LinearLayoutManager(context)
        
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        
    }
    
}