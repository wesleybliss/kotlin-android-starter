package com.gammagamma.kas.home

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gammagamma.kas.sqldelight.data.User
import com.gammagamma.kas.ui.extensions.inflate
import kotlinx.android.synthetic.main.row_user.view.*

class UsersAdapter(
    private var items: List<User>? = listOf(),
    var onClick: ((User) -> Unit)? = null
) : /*RecyclerView.Adapter<UsersAdapter.ViewHolder>()*/ ListAdapter<User, UsersAdapter.ViewHolder>(DIFF_CALLBACK) {
    
    companion object {
    
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(oldUser: User, newUser: User) : Boolean {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.id == newUser.id
                }
                override fun areContentsTheSame(oldUser: User, newUser: User) : Boolean {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return (oldUser as User.Impl) == newUser as User.Impl
                }
            }
        
    }
    
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
