package com.gammagamma.kas.about

import com.gammagamma.about.R
import com.gammagamma.kas.ui.StatefulBaseViewModel
import com.gammagamma.kas.ui.extensions.getString

class AboutViewModel : StatefulBaseViewModel() {
    
    val title = getString(R.string.about_title)
    
}
