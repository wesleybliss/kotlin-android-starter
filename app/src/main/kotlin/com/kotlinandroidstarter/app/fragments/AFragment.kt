package com.kotlinandroidstarter.app.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_a.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.adapters.UsersAdapter
import com.kotlinandroidstarter.app.api.ApiClient
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.utils.ConfirmDialog
import com.kotlinandroidstarter.app.utils.ViewHelper
import com.kotlinandroidstarter.app.utils.toast


class AFragment : Fragment() {
    
    private val TAG: String = AFragment::class.java.simpleName
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_a, container, false)
    
    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)
    
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        ViewHelper.setupRecyclerView(context, listItems)
        
        listItems.adapter = UsersAdapter(mutableListOf<User>(), {
            
            Log.d(TAG, "Tapped item " + it)
            
            ConfirmDialog(context, "Item Clicked", "Item: $it", {
                toast("Roger that!")
            })
            
        })
        
        ApiClient.fetchUsers(
            { toast("Failed to fetch users because $it") },
            { (listItems.adapter as UsersAdapter).setItems(it.toMutableList()) })
        
    }
    
}
