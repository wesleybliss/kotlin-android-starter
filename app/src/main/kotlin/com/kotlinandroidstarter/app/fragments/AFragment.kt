package com.kotlinandroidstarter.app.fragments


import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.adapters.UsersAdapter
import com.kotlinandroidstarter.app.di.Injectable
import com.kotlinandroidstarter.app.utils.ConfirmDialog
import com.kotlinandroidstarter.app.utils.ViewHelper
import com.kotlinandroidstarter.app.utils.toast
import com.kotlinandroidstarter.app.viewmodels.UsersViewModel
import kotlinx.android.synthetic.main.fragment_a.*
import javax.inject.Inject


class AFragment : BaseFragment(), Injectable {
    
    private val TAG = AFragment::class.java.simpleName

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var usersViewModel: UsersViewModel
//    private lateinit var binding: AFragmentBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_a, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        ViewHelper.setupRecyclerView(context!!, listItems)
        
        listItems.adapter = UsersAdapter(mutableListOf(), {
            
            Log.d(TAG, "Tapped item $it")
            
            ConfirmDialog(context!!, "Item Clicked", "Item: $it", {
                toast("Roger that!")
            })
            
        })
        
        usersViewModel.fetchUsers()
        
    }
    
}
