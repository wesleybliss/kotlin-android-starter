package com.gammagamma.kas.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gammagamma.kas.feed.databinding.FragmentFeedBinding
import com.gammagamma.kas.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed) {
    
    private val vm: FeedViewModel by viewModel()
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        
        super.onCreateView(inflater, container, savedInstanceState)
        
        binding.vm = vm
        
        return binding.root
        
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
        
    }
    
}
