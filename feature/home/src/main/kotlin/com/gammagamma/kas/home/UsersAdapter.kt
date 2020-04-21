package com.gammagamma.kas.home

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.gammagamma.kas.sqldelight.data.User
import com.gammagamma.kas.ui.extensions.inflate
import kotlinx.android.synthetic.main.row_user.view.*

class UsersAdapter(
    private var items: List<User>? = listOf(),
    var onClick: ((User) -> Unit)? = null
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        fun bind(item: User, listener: ((User) -> Unit)? = null) = with(itemView) {
            textId.text = item.id.toString()
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
    
    fun setItems(newItems: List<User>) {
        items = newItems
        notifyDataSetChanged()
    }
    
}
