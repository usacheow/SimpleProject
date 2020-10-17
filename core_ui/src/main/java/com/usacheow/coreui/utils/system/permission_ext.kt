package com.usacheow.coreui.utils.system

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/*  Single permission request for Fragment

    val permissionRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        when {
            it -> onGrantedAction()
            else -> onDeniedAction()
        }
    }

    permissionRequest.launch(REQUIRED_PERMISSION)
*/

/*  Multiple permissions request for Fragment

    Fragment.checkPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS, ::onGrantedAction)

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (requireContext().arePermissionsGranted(REQUIRED_PERMISSIONS)) {
                onGrantedAction()
            } else {
                onDeniedAction()
            }
        }
    }
*/

fun Context.arePermissionsGranted(permissions: Array<String>) = permissions.all {
    ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}

fun Context.isPermissionGranted(permission: String) = ContextCompat
    .checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Fragment.checkPermissions(requiredPermissions: Array<String>, codeResult: Int, onGrantedAction: () -> Unit) {
    if (requireContext().arePermissionsGranted(requiredPermissions)) {
        onGrantedAction()
    } else {
        requestPermissions(requiredPermissions, codeResult)
    }
}