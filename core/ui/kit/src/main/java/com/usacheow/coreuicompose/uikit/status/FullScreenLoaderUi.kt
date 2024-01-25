package com.usacheow.coreuicompose.uikit.status

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.usacheow.coreuitheme.compose.AppTheme

@Composable
fun FullScreenLoaderUi(isLoading: Boolean) {
    Crossfade(targetState = isLoading) {
        if (it) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.specificColorScheme.scrim),
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}