package com.kotlinandroidstarter.app.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_user.view.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.utils.inflate

class UsersAdapter(private val items: MutableList<User>, val onClick: (User) -> Unit)
    : androidx.recyclerview.widget.RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        fun bind(item: User, listener: (User) -> Unit) = with(itemView) {
            textName.text = item.name
            textEmail.text = item.email
            setOnClickListener { listener(item) }
        }
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.row_user))
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], onClick)
    
    override fun getItemCount() = items.size
    
    fun setItems(newItems: MutableList<User>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    
}
