package com.kotlinandroidstarter.app.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.gammagamma.domain.model.IUser
import kotlinx.android.synthetic.main.row_user.view.*
import com.kotlinandroidstarter.app.R
import com.gammagamma.kotlinandroidstarter.net.models.User
import com.kotlinandroidstarter.app.utils.inflate

class UsersAdapter(private var items: List<IUser>, val onClick: (IUser) -> Unit)
    : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        fun bind(item: IUser, listener: (IUser) -> Unit) = with(itemView) {
            textName.text = item.fullName
            textEmail.text = item.email
            setOnClickListener { listener(item) }
        }
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.row_user))
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], onClick)
    
    override fun getItemCount() = items.size
    
    fun setItems(newItems: List<IUser>) {
        items = newItems
        notifyDataSetChanged()
    }
    
}
