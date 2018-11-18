package com.kotlinandroidstarter.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.adapters.UsersAdapter
import com.kotlinandroidstarter.app.api.ApiClient
import com.kotlinandroidstarter.app.utils.ConfirmDialog
import com.kotlinandroidstarter.app.utils.ViewHelper
import com.kotlinandroidstarter.app.utils.toast
import timber.log.Timber

class AFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_a, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        ViewHelper.setupRecyclerView(context!!, listItems)
        
        listItems.adapter = UsersAdapter(mutableListOf()) {

            Timber.d("Tapped item %s", it)

            ConfirmDialog(context!!, "Item Clicked", "Item: $it") {
                toast("Roger that!")
            }

        }

        ApiClient.fetchUsers(
            { toast("Failed to fetch users because $it") },
            { (listItems.adapter as UsersAdapter).setItems(it.toMutableList()) })
        
    }
    
}
