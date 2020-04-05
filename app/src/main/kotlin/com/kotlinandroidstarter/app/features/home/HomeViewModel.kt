package com.kotlinandroidstarter.app.features.home

import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.extensions.getString
import com.kotlinandroidstarter.app.shared.ui.StatefulBaseViewModel

class HomeViewModel : StatefulBaseViewModel() {
    
    val title = getString(R.string.home_title)
    
}
