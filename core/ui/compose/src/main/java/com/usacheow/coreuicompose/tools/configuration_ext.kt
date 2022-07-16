package com.usacheow.coreuicompose.tools

import android.content.res.Configuration

val Configuration.isPortrait get() = orientation == Configuration.ORIENTATION_PORTRAIT

val Configuration.isLandscape get() = orientation == Configuration.ORIENTATION_LANDSCAPE