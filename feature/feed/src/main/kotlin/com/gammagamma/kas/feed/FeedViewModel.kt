package com.gammagamma.kas.feed

import com.gammagamma.kas.ui.StatefulBaseViewModel
import com.gammagamma.kas.ui.extensions.getString

class FeedViewModel : StatefulBaseViewModel() {
    
    val title = getString(R.string.feed_title)
    
}
