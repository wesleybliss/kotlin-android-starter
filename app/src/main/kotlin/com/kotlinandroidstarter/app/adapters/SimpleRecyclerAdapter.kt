package com.kotlinandroidstarter.app.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlinandroidstarter.app.utils.inflate

class SimpleRecyclerAdapter(
    private val items: MutableList<String>,
    private val onItemClicked: (String, Int) -> Unit)
    : androidx.recyclerview.widget.RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder>() {
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        fun bind(item: String, listener: (String, Int) -> Unit) = with(itemView) {
            val text1 = itemView.findViewById<TextView>(android.R.id.text1)
            text1.text = item
            text1.setOnClickListener { listener(item, adapterPosition) }
        }
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(android.R.layout.simple_list_item_1))
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], onItemClicked)
    
    override fun getItemCount() = items.size
    
    fun setItems(newItems: MutableList<String>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    
}
