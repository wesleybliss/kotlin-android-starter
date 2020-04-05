package com.kotlinandroidstarter.app.features.about

import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.extensions.getString
import com.kotlinandroidstarter.app.shared.ui.StatefulBaseViewModel

class AboutViewModel : StatefulBaseViewModel() {
    
    val title = getString(R.string.about_title)
    
}
