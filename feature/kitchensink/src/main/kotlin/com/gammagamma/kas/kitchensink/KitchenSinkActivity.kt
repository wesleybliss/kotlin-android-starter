package com.gammagamma.kas.kitchensink

import android.Manifest
import android.os.Bundle
import com.gammagamma.kas.kitchensink.databinding.ActivityKitchenSinkBinding
import com.gammagamma.kas.logging.plank
import com.gammagamma.kas.ui.BaseToolbarActivity
import com.gammagamma.kas.ui.extensions.hasPermission
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.alertdialog.alertDialog
import splitties.alertdialog.message
import splitties.alertdialog.negativeButton
import splitties.alertdialog.positiveButton

class KitchenSinkActivity : BaseToolbarActivity<ActivityKitchenSinkBinding>(R.layout.activity_kitchen_sink) {
    
    private val vm: KitchenSinkViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        setTitle(R.string.kitchensink_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        binding.vm = vm
        
        val hasExternalPermission = hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        vm.storagePermissionStatus.value = when (hasExternalPermission) {
            true -> KitchenSinkViewModel.PermissionStatus.GRANTED
            false -> KitchenSinkViewModel.PermissionStatus.DENIED
        }
        
        vm.observeEvent<KitchenSinkViewModel.Events>(this) {
            when (it) {
                KitchenSinkViewModel.Events.REQUEST_PERMISSIONS -> checkExternalStoragePermission()
            }
        }
        
    }
    
    private fun checkExternalStoragePermission() {
        
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    plank("External storage read permission GRANTED")
                    vm.storagePermissionStatus.value = KitchenSinkViewModel.PermissionStatus.GRANTED
                }
                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    plank("External storage read permission DENIED")
                    vm.storagePermissionStatus.value = KitchenSinkViewModel.PermissionStatus.DENIED
                }
                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                    plank("External storage read permission RATIONAL")
                    alertDialog { 
                        title = "Permission Required"
                        message = "We need access to your storage to continue"
                        negativeButton(R.string.default_no) {
                            token?.cancelPermissionRequest()
                        }
                        positiveButton(R.string.default_yes) {
                            token?.continuePermissionRequest()
                        }
                    }.show()
                }
            }).check()
        
    }
    
}
