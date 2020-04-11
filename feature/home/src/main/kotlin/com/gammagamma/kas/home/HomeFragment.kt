package com.gammagamma.kas.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gammagamma.home.R
import com.gammagamma.home.databinding.FragmentHomeBinding
import com.gammagamma.kas.ui.BaseFragment
import com.gammagamma.kas.ui.extensions.confirm
import com.gammagamma.kas.ui.extensions.initWithDefaults
import com.gammagamma.kas.ui.extensions.observe
import com.gammagamma.kas.ui.extensions.toast
import com.gammagamma.kas.logging.plank
import kotlinx.android.synthetic.main.content_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    
    private val vm: HomeViewModel by viewModel()
    
    private val adapter by lazy {
        UsersAdapter {
            plank("Tapped item %s", it)
            confirm(
                getString(R.string.home_item_clicked_title),
                getString(R.string.home_item_clicked_message, it)) {
                toast("Roger that!")
            }
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        
        super.onCreateView(inflater, container, savedInstanceState)
        
        binding.vm = vm
        binding.swipeRefresh.setOnRefreshListener { vm.fetchUsers() }
        
        binding.content.listItems.initWithDefaults(requireContext(), adapter)
        
        vm.users.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) adapter.setItems(it)
        }
        
        return binding.root
        
    }
    
}