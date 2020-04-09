package com.gammagamma.kotlinandroidstarter.about

import com.gammagamma.about.R
import com.gammagamma.kotlinandroidstarter.ui.StatefulBaseViewModel
import com.gammagamma.kotlinandroidstarter.ui.extensions.getString

class AboutViewModel : StatefulBaseViewModel() {
    
    val title = getString(R.string.about_title)
    
}
