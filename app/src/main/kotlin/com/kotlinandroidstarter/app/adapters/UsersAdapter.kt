package com.kotlinandroidstarter.app.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.utils.inflate
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_user.view.*


class UsersAdapter(val items: MutableList<User>)
    : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    
    private val clickSubject = PublishSubject.create<User>()
    val clickEvents: Observable<User> = clickSubject
    
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        init {
            itemView.clicks().map { items[layoutPosition] }.subscribe(clickSubject)
        }
        
        fun bind(item: User) = with(itemView) {
            textName.text = item.name
            textEmail.text = item.email
        }
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.row_user))
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])
    
    override fun getItemCount() = items.size
    
    fun setItems(newItems: MutableList<User>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    
}