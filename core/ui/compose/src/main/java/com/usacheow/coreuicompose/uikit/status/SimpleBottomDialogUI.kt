package com.usacheow.coreuicompose.uikit.status

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.inset
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.listtile.HeaderTileState
import com.usacheow.coreuicompose.uikit.other.SheetIndicator
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleBottomDialogUI(
    header: TextValue,
    content: LazyListScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .background(AppTheme.specificColorScheme.surface)
            .inset(insetAllExcludeTop()),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        ) {
            stickyHeader {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    SheetIndicator()
                    HeaderTileState.Data(value = header)
                        .Content(modifier = Modifier)
                }
            }
            content()
        }
    }
}