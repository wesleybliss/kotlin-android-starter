package com.gammagamma.kas.kitchensink

import com.gammagamma.kas.domain.ui.IViewModelEvents
import com.gammagamma.kas.ui.BaseViewModel
import com.gammagamma.kas.ui.R
import com.gammagamma.kas.ui.extensions.getColor
import com.gammagamma.kas.ui.extensions.mediatorLiveDataOf
import com.gammagamma.kas.ui.extensions.mutableLiveDataOf

class KitchenSinkViewModel : BaseViewModel() {
    
    enum class Events : IViewModelEvents {
        REQUEST_PERMISSIONS
    }
    
    enum class PermissionStatus {
        UNKNOWN,
        DENIED,
        GRANTED
    }
    
    val storagePermissionStatus = mutableLiveDataOf(PermissionStatus.UNKNOWN)
    
    val storagePermissionStatusText = mediatorLiveDataOf("") {
        addSource(storagePermissionStatus) {
            value = when (it) {
                PermissionStatus.DENIED -> "DENIED"
                PermissionStatus.GRANTED -> "GRANTED"
                else -> ""
            }
        }
    }
    
    val storagePermissionStatusTextColor = mediatorLiveDataOf(getColor(R.color.colorPrimaryText)) {
        addSource(storagePermissionStatus) {
            value = getColor(when (it) {
                PermissionStatus.DENIED -> R.color.colorDangerText
                PermissionStatus.GRANTED -> R.color.colorSuccessText
                else -> R.color.colorPrimaryText
            })
        }
    }
    
    fun onRequestExternalPermissionClick() {
        dispatchEvent(Events.REQUEST_PERMISSIONS)
    }
    
}
