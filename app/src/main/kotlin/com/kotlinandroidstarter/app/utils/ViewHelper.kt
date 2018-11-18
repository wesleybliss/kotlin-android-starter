package com.kotlinandroidstarter.app.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object ViewHelper {
    
    fun setupRecyclerView(context: Context, recyclerView: RecyclerView) {
        
        val layoutManager = LinearLayoutManager(context)
        
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        
    }
    
}
