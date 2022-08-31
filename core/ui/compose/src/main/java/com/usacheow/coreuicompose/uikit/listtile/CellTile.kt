package com.usacheow.coreuicompose.uikit.listtile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.addIf
import com.usacheow.coreuicompose.tools.doOnClick
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.other.Badge
import com.usacheow.coreuicompose.uikit.other.FadeText
import com.usacheow.coreuicompose.uikit.other.ShimmerTileCircle
import com.usacheow.coreuicompose.uikit.other.ShimmerTileConfig
import com.usacheow.coreuicompose.uikit.other.ShimmerTileLine
import com.usacheow.coreuitheme.compose.AppTheme

sealed class CellTileState : TileState {

    data class Data(
        val leftPart: LeftPart? = null,

        val hasBadge: Boolean = false,
        val additionalHeader: TextValue? = null,
        val header: TextValue? = null,
        val value: TextValue? = null,
        val maxValueLines: Int = Int.MAX_VALUE,
        val valueLarge: TextValue? = null,
        val description: TextValue? = null,
        val action: TextValue? = null,
        val additionalDescription: TextValue? = null,

        val rightPart: RightPart? = null,
        val onClick: (() -> Unit)? = null,
    ) : CellTileState()

    data class Shimmer(
        val needLeftCircle: Boolean = false,
        val needAdditionalHeaderLine: Boolean = false,
        val needHeaderLine: Boolean = false,
        val needValueLine: Boolean = false,
        val needValueLargeLine: Boolean = false,
        val needDescriptionLine: Boolean = false,
        val needActionLine: Boolean = false,
        val needAdditionalDescriptionLine: Boolean = false,
        val needRightCircle: Boolean = false,
        val needRightLine: Boolean = false,
    ) : CellTileState()

    data class Error(
        val data: MessageBannerState,
    ) : CellTileState()

    @Composable
    override fun Content(modifier: Modifier) {
        CellTile(modifier, this)
    }

    sealed class LeftPart {

        data class Icon(
            val icon: ImageValue,
            val builder: ImageRequest.Builder.() -> Unit = {},
        ) : LeftPart()

        data class GreyIcon(
            val icon: ImageValue,
            val builder: ImageRequest.Builder.() -> Unit = {},
        ) : LeftPart()

        data class GreyColoredIcon(
            val icon: ImageValue,
            val builder: ImageRequest.Builder.() -> Unit = {},
        ) : LeftPart()

        data class Logo(
            val source: ImageValue,
            val builder: ImageRequest.Builder.() -> Unit = {},
        ) : LeftPart()
    }

    sealed class RightPart {

        data class ActionIcon(val source: ImageValue, val clickListener: (() -> Unit)? = null) : RightPart()
        object CheckIcon : RightPart()
        data class Logo(val source: ImageValue) : RightPart()
        data class Switch(val isChecked: Boolean) : RightPart()
        data class Text(val text: TextValue) : RightPart()
    }
}

object CellTileConfig {

    val HorizontalPadding = AppTheme.specificValues.default_padding
    val RipplePadding = 0.dp
    val VerticalPadding = 12.dp
    val VerticalHeaderPadding = 8.dp
    val BadgePadding = 5.dp

    val SmallIconSize = 20.dp
    val IconSize = 40.dp
    val ActionIconSize = 32.dp
    val IconPadding = 10.dp
    val BetweenPadding = 12.dp

    val StartDividerPadding = IconSize + BetweenPadding + HorizontalPadding
    val SmallStartDividerPadding = SmallIconSize + BetweenPadding + HorizontalPadding

    @Composable
    fun additionalHeaderStyle() = AppTheme.specificTypography.labelLarge

    @Composable
    fun headerStyle() = AppTheme.specificTypography.bodyMedium

    @Composable
    fun valueStyle() = AppTheme.specificTypography.bodyLarge

    @Composable
    fun valueLargeStyle() = AppTheme.specificTypography.titleSmall

    @Composable
    fun descriptionStyle() = AppTheme.specificTypography.bodyMedium

    @Composable
    fun actionStyle() = AppTheme.specificTypography.bodyMedium

    @Composable
    fun additionalDescriptionStyle() = AppTheme.specificTypography.bodyMedium

    @Composable
    fun rightPartTextStyle() = AppTheme.specificTypography.bodyLarge
}

@Composable
fun CellTile(
    modifier: Modifier = Modifier,
    data: CellTileState,
) {
    when (data) {
        is CellTileState.Data -> Data(modifier, data)
        is CellTileState.Shimmer -> Shimmer(modifier, data)
        is CellTileState.Error -> Error(modifier, data)
    }
}

@Composable
private fun Data(
    modifier: Modifier = Modifier,
    data: CellTileState.Data,
) {
    CellTileContainer(
        modifier = modifier,
        hasBadge = data.hasBadge,
        clickListener = data.onClick,
    ) {
        LeftPart(data.leftPart)
        MiddlePart(
            additionalHeader = data.additionalHeader,
            header = data.header,
            value = data.value,
            maxValueLines = data.maxValueLines,
            valueLarge = data.valueLarge,
            description = data.description,
            action = data.action,
            additionalDescription = data.additionalDescription,
        )
        RightPart(data.rightPart)
    }
}

@Composable
private fun Shimmer(
    modifier: Modifier = Modifier,
    data: CellTileState.Shimmer,
) {
    CellTileContainer(
        modifier = modifier,
        hasBadge = false,
        clickListener = null,
    ) {
        if (data.needLeftCircle) ShimmerTileCircle(size = CellTileConfig.IconSize)
        MiddlePartContainer {
            if (data.needAdditionalHeaderLine) ShimmerTileLine(
                width = ShimmerTileConfig.WidthSmall,
                height = CellTileConfig.additionalHeaderStyle().lineHeight.value.dp,
            )
            if (data.needHeaderLine) ShimmerTileLine(
                width = ShimmerTileConfig.WidthSmall,
                height = CellTileConfig.headerStyle().lineHeight.value.dp,
            )
            if (data.needValueLine) ShimmerTileLine(
                width = ShimmerTileConfig.WidthLarge,
                height = CellTileConfig.valueStyle().lineHeight.value.dp,
            )
            if (data.needValueLargeLine) ShimmerTileLine(
                width = ShimmerTileConfig.WidthExtraLarge,
                height = CellTileConfig.valueLargeStyle().lineHeight.value.dp,
            )
            if (data.needDescriptionLine) ShimmerTileLine(
                width = ShimmerTileConfig.WidthMedium,
                height = CellTileConfig.descriptionStyle().lineHeight.value.dp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .addIf(data.needValueLargeLine) { padding(top = 4.dp) }
                    .addIf(!data.needActionLine && data.needAdditionalDescriptionLine) { padding(bottom = 6.dp) }
            )
            if (data.needActionLine) ShimmerTileLine(
                width = ShimmerTileConfig.WidthSmall,
                height = CellTileConfig.actionStyle().lineHeight.value.dp,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .addIf(data.needValueLargeLine || data.needDescriptionLine) { padding(top = 6.dp) }
                    .addIf(data.needAdditionalDescriptionLine) { padding(bottom = 6.dp) }
            )
            if (data.needAdditionalDescriptionLine) ShimmerTileLine(
                width = ShimmerTileConfig.WidthMedium,
                height = CellTileConfig.additionalDescriptionStyle().lineHeight.value.dp,
            )
        }
        if (data.needRightCircle) ShimmerTileCircle(size = CellTileConfig.IconSize)
        else if (data.needRightLine) ShimmerTileLine(
            width = ShimmerTileConfig.WidthExtraSmall,
            height = CellTileConfig.rightPartTextStyle().lineHeight.value.dp,
        )
    }
}

@Composable
private fun Error(
    modifier: Modifier = Modifier,
    data: CellTileState.Error,
) {
    CellTileContainer(
        modifier = modifier,
        hasBadge = false,
        clickListener = null,
    ) {
        data.data.Content(modifier = modifier)
    }
}

@Composable
fun CellTileContainer(
    modifier: Modifier = Modifier,
    verticalPadding: Dp = CellTileConfig.VerticalPadding,
    clickListener: (() -> Unit)? = null,
    hasBadge: Boolean = false,
    content: @Composable RowScope.() -> Unit,
) {
    val ripplePadding = CellTileConfig.RipplePadding
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(ripplePadding)
            .clip(AppTheme.shapes.small)
            .doOnClick(onClick = clickListener)
            .padding(vertical = verticalPadding - ripplePadding)
            .padding(end = CellTileConfig.HorizontalPadding - ripplePadding)
            .addIf(!hasBadge) { padding(start = CellTileConfig.HorizontalPadding - ripplePadding) },
    ) {
        if (hasBadge) Badge(
            modifier = Modifier.padding(
                end = CellTileConfig.BadgePadding,
                start = CellTileConfig.BadgePadding - ripplePadding,
            ),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CellTileConfig.BetweenPadding),
        ) {
            content()
        }
    }
}

@Composable
private fun LeftPart(data: CellTileState.LeftPart?) {
    data ?: return

    @Composable
    fun LeftIcon(
        icon: ImageValue,
        builder: ImageRequest.Builder.() -> Unit,
        tint: Color = Color.Unspecified,
        bgModifier: Modifier = Modifier,
    ) {
        Icon(
            painter = icon.get(builder),
            tint = tint,
            contentDescription = null,
            modifier = Modifier
                .size(CellTileConfig.IconSize)
                .then(bgModifier)
                .padding(CellTileConfig.IconPadding),
        )
    }

    when (data) {
        is CellTileState.LeftPart.Icon -> LeftIcon(
            icon = data.icon,
            builder = data.builder,
        )
        is CellTileState.LeftPart.GreyIcon -> LeftIcon(
            icon = data.icon,
            builder = data.builder,
            bgModifier = Modifier.background(AppTheme.specificColorScheme.surfaceVariant, CircleShape),
        )
        is CellTileState.LeftPart.GreyColoredIcon -> LeftIcon(
            icon = data.icon,
            builder = data.builder,
            tint = AppTheme.specificColorScheme.onSurfaceVariant,
            bgModifier = Modifier.background(AppTheme.specificColorScheme.surfaceVariant, CircleShape),
        )
        is CellTileState.LeftPart.Logo -> Image(
            painter = data.source.get(data.builder),
            contentDescription = null,
            modifier = Modifier.size(CellTileConfig.IconSize),
        )
    }
}

@Composable
private fun RowScope.MiddlePart(
    additionalHeader: TextValue?,
    header: TextValue?,
    value: TextValue?,
    maxValueLines: Int,
    valueLarge: TextValue?,
    description: TextValue?,
    action: TextValue?,
    additionalDescription: TextValue?,
) {
    MiddlePartContainer {
        additionalHeader?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolTertiary,
                style = CellTileConfig.additionalHeaderStyle(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        header?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolTertiary,
                style = CellTileConfig.headerStyle(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        value?.get()?.let {
            FadeText(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = CellTileConfig.valueStyle(),
                modifier = Modifier.fillMaxWidth(),
                maxLines = maxValueLines,
            )
        }
        valueLarge?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = CellTileConfig.valueLargeStyle(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        description?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolSecondary,
                style = CellTileConfig.descriptionStyle(),
                modifier = Modifier
                    .fillMaxWidth()
                    .addIf(valueLarge != null) { padding(top = 4.dp) }
                    .addIf(action == null && additionalDescription != null) { padding(bottom = 6.dp) },
            )
        }
        action?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = CellTileConfig.actionStyle(),
                modifier = Modifier
                    .fillMaxWidth()
                    .addIf(valueLarge != null || description != null) { padding(top = 6.dp) }
                    .addIf(additionalDescription != null) { padding(bottom = 6.dp) },
            )
        }
        additionalDescription?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolTertiary,
                style = CellTileConfig.additionalDescriptionStyle(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun RowScope.MiddlePartContainer(
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.weight(1f),
        content = content,
    )
}

@Composable
private fun RowScope.RightPart(data: CellTileState.RightPart?) {
    data ?: return
    when (data) {
        is CellTileState.RightPart.ActionIcon -> Icon(
            painter = data.source.get(),
            tint = AppTheme.specificColorScheme.onSurfaceVariant,
            contentDescription = null,
            modifier = Modifier
                .size(CellTileConfig.ActionIconSize)
                .align(Alignment.CenterVertically)
                .clip(AppTheme.shapes.medium)
                .doOnClick(onClick = data.clickListener)
                .padding(6.dp),
        )
        is CellTileState.RightPart.CheckIcon -> Icon(
            painter = AppTheme.specificIcons.done.get(),
            tint = AppTheme.specificColorScheme.secondary,
            contentDescription = null,
            modifier = Modifier
                .size(CellTileConfig.ActionIconSize)
                .padding(6.dp),
        )
        is CellTileState.RightPart.Logo -> Image(
            painter = data.source.get(),
            contentDescription = null,
            modifier = Modifier
                .size(CellTileConfig.IconSize)
                .align(Alignment.CenterVertically),
        )
        is CellTileState.RightPart.Switch -> Switch(
            checked = data.isChecked,
            onCheckedChange = null,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
        is CellTileState.RightPart.Text -> Text(
            text = data.text.get(),
            color = AppTheme.specificColorScheme.symbolSecondary,
            style = AppTheme.specificTypography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}