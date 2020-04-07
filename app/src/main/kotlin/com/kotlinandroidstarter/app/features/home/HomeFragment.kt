package com.kotlinandroidstarter.app.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gammagamma.logging.plank
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.adapters.UsersAdapter
import com.kotlinandroidstarter.app.databinding.FragmentHomeBinding
import com.kotlinandroidstarter.app.extensions.confirm
import com.kotlinandroidstarter.app.shared.ui.BaseFragment
import com.kotlinandroidstarter.app.utils.ViewHelper
import com.kotlinandroidstarter.app.utils.toast
import com.shopify.livedataktx.observe
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    
    private val vm: HomeViewModel by viewModel()
    
    private val adapter by lazy {
        UsersAdapter(listOf()) {
            plank("Tapped item %s", it)
            confirm(
                getString(R.string.home_item_clicked_title),
                getString(R.string.home_item_clicked_message, it)) {
                toast("Roger that!")
            }
        }
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        
        super.onCreateView(inflater, container, savedInstanceState)
        
        binding.vm = vm
        
        return binding.root
        
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
    
        ViewHelper.setupRecyclerView(requireContext(), listItems)
        
        listItems.adapter = adapter
        
        vm.users.observe(this) {
            if (it.isNullOrEmpty()) return@observe
            adapter.setItems(it)
        }
        
        //vm.fetchUsers()
        
    }
    
}
