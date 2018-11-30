package com.kotlinandroidstarter.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_a.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.adapters.UsersAdapter
import com.kotlinandroidstarter.app.utils.ConfirmDialog
import com.kotlinandroidstarter.app.utils.ViewHelper
import com.kotlinandroidstarter.app.utils.toast
import com.kotlinandroidstarter.app.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class AFragment : Fragment() {
    
    private val vm: SharedViewModel by viewModel()
    
    private val adapter by lazy {
        UsersAdapter(mutableListOf()) {
            Timber.d("Tapped item %s", it)
            ConfirmDialog(context!!, "Item Clicked", "Item: $it") {
                toast("Roger that!")
            }
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_a, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        ViewHelper.setupRecyclerView(context!!, listItems)
        
        listItems.adapter = adapter
        
        vm.users.observe(this, Observer {
            adapter.setItems(it.toMutableList())
        })
        
        vm.fetchUsers()
        
    }
    
}
