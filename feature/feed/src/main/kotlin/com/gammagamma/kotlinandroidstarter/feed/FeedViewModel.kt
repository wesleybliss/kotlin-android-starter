package com.gammagamma.kotlinandroidstarter.feed

import com.gammagamma.kotlinandroidstarter.ui.StatefulBaseViewModel
import com.gammagamma.kotlinandroidstarter.ui.extensions.getString

class FeedViewModel : StatefulBaseViewModel() {
    
    val title = getString(R.string.feed_title)
    
}
