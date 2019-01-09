package com.kotlinandroidstarter.app.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.models.Thing
import com.kotlinandroidstarter.app.utils.inflate

class FragBAdapter(val things: MutableList<Thing>) : RecyclerView.Adapter<FragBViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragBViewHolder {
        return FragBViewHolder(parent.inflate(R.layout.row_user))
    }
    
    override fun getItemCount(): Int = things.size
    
    override fun onBindViewHolder(holder: FragBViewHolder, position: Int) {
        holder.bind(things[position])
    }
}

class FragBViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    
    fun bind(thing: Thing) {
        
        view.findViewById<TextView>(R.id.textName)
            .text = thing.name
        
    }
    
}
