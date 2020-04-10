package com.gammagamma.kotlinandroidstarter.home

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.gammagamma.home.R
import com.gammagamma.kotlinandroidstarter.domain.model.IUser
import com.gammagamma.kotlinandroidstarter.ui.extensions.inflate
import kotlinx.android.synthetic.main.row_user.view.*

class UsersAdapter(
    private var items: List<IUser>? = listOf(),
    var onClick: ((IUser) -> Unit)? = null
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        fun bind(item: IUser, listener: ((IUser) -> Unit)? = null) = with(itemView) {
            textName.text = item.name
            textEmail.text = item.email
            setOnClickListener { listener?.invoke(item) }
        }
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.row_user))
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.get(position)?.let { item -> holder.bind(item, onClick) }
    }
    
    override fun getItemCount() = items?.size ?: 0
    
    fun setItems(newItems: List<IUser>) {
        items = newItems
        notifyDataSetChanged()
    }
    
}
