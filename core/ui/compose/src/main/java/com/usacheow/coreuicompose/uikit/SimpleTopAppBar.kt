package com.usacheow.coreuicompose.uikit

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuitheme.R

@Composable
fun SimpleTopAppBar(
    title: TextValue,
    @DrawableRes navigationIcon: Int = R.drawable.ic_back,
    navigationIconClickListener: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (navigationIconClickListener != null) {
                Icon(
                    painter = painterResource(navigationIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = navigationIconClickListener)
                        .padding(8.dp),
                )
            }
        },
        title = {
            Text(title.get())
        },
    )
}