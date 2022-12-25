package com.example.sampleapp.application.presentation.extensions

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.Mavericks

fun Fragment.navigate(@IdRes id: Int, args: Parcelable? = null) {
    findNavController().navigate(id, Bundle().apply { putParcelable(Mavericks.KEY_ARG, args) })
}

fun Fragment.showToast(message: String? = null, toastLength: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this.requireActivity(),
        message.orEmpty(),
        toastLength
    ).show()
}

fun Fragment.showToast(@StringRes stringRes: Int = 0, toastLength: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this.requireActivity(),
        if (stringRes != 0) getString(stringRes) else "",
        toastLength
    ).show()
}