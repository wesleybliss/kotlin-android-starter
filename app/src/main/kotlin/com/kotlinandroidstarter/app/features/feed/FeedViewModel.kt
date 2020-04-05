package com.kotlinandroidstarter.app.features.feed

import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.extensions.getString
import com.kotlinandroidstarter.app.shared.ui.StatefulBaseViewModel

class FeedViewModel : StatefulBaseViewModel() {
    
    val title = getString(R.string.feed_title)
    
}
