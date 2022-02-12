package com.usacheow.appdemo.catalog

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.MaterialColors
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.appdemo.databinding.ViewColorItemBinding
import com.usacheow.core.resource.TextSource
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.ThemeColorsAttrs
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.colorByAttr
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.helper.toPx
import com.usacheow.coreui.uikit.molecule.SubtitleTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as AppDemoR
import com.usacheow.coreui.R as CoreUiR

@AndroidEntryPoint
class PaletteFragment : SimpleFragment<FragmentListBinding>() {

    @Inject
    lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    private val items = listOf(
        SubtitleTileItem(TextSource.Simple("Primary")),
        ColorItem(ThemeColorsAttrs.primary),
        ColorItem(ThemeColorsAttrs.onPrimary),
        ColorItem(ThemeColorsAttrs.primaryContainer),
        ColorItem(ThemeColorsAttrs.onPrimaryContainer),
        ColorItem(ThemeColorsAttrs.primaryInverse),
        SubtitleTileItem(TextSource.Simple("Secondary")),
        ColorItem(ThemeColorsAttrs.secondary),
        ColorItem(ThemeColorsAttrs.onSecondary),
        ColorItem(ThemeColorsAttrs.secondaryContainer),
        ColorItem(ThemeColorsAttrs.onSecondaryContainer),
        SubtitleTileItem(TextSource.Simple("Tertiary")),
        ColorItem(ThemeColorsAttrs.tertiary),
        ColorItem(ThemeColorsAttrs.onTertiary),
        ColorItem(ThemeColorsAttrs.tertiaryContainer),
        ColorItem(ThemeColorsAttrs.onTertiaryContainer),
        SubtitleTileItem(TextSource.Simple("Error")),
        ColorItem(ThemeColorsAttrs.error),
        ColorItem(ThemeColorsAttrs.onError),
        ColorItem(ThemeColorsAttrs.errorContainer),
        ColorItem(ThemeColorsAttrs.onErrorContainer),
        SubtitleTileItem(TextSource.Simple("Background")),
        ColorItem(ThemeColorsAttrs.background),
        ColorItem(ThemeColorsAttrs.onBackground),
        SubtitleTileItem(TextSource.Simple("Surface")),
        ColorItem(ThemeColorsAttrs.surface),
        ColorItem(ThemeColorsAttrs.onSurface),
        ColorItem(ThemeColorsAttrs.surfaceVariant),
        ColorItem(ThemeColorsAttrs.onSurfaceVariant),
        ColorItem(ThemeColorsAttrs.surfaceInverse),
        ColorItem(ThemeColorsAttrs.onSurfaceInverse),
        SubtitleTileItem(TextSource.Simple("Text/Icons")),
        ColorItem(ThemeColorsAttrs.symbolPrimary),
        ColorItem(ThemeColorsAttrs.symbolPrimaryInverse),
        ColorItem(ThemeColorsAttrs.symbolSecondary),
        ColorItem(ThemeColorsAttrs.symbolSecondaryInverse),
        ColorItem(ThemeColorsAttrs.symbolTertiary),
        ColorItem(ThemeColorsAttrs.symbolTertiaryInverse),
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.widgetsListView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.title = "Palette"
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

        binding.widgetsListView.updatePadding(left = 16.toPx, right = 16.toPx)
        binding.widgetsListView.layoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

                override fun getSpanSize(position: Int): Int = when (items[position]) {
                    is SubtitleTileItem -> 2
                    else -> 1
                }
            }
        }
        binding.widgetsListView.adapter = ViewStateAdapter(items)
    }
}

class ColorItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialCardView(context, attrs), Populatable<ColorItem> {

    private val binding by lazy { ViewColorItemBinding.bind(this) }

    override fun populate(model: ColorItem) = with(binding) {
        val color = colorByAttr(model.colorAttrRes)
        setCardBackgroundColor(color)
        nameTextView.text = resources.getResourceName(model.colorAttrRes)
            .split("/")
            .lastOrNull()
            ?.removePrefix("symbol")
            .toLowerCaseFirstSymbol()
        codeTextView.text = "#%s".format(Integer.toHexString(color)).uppercase()

        val textColorRes = when (MaterialColors.isColorLight(color)) {
            true -> CoreUiR.color.black
            false -> CoreUiR.color.white
        }
        val textColor = MaterialColors.harmonize(color(textColorRes), color)
        nameTextView.setTextColor(textColor)
        codeTextView.setTextColor(textColor)
    }

    private fun String?.toLowerCaseFirstSymbol(): String? {
        if (this.isNullOrBlank()) {
            return null
        }
        return "%s%s".format(this[0].lowercaseChar(), this.substring(1))
    }
}

data class ColorItem(
    @AttrRes val colorAttrRes: Int,
) : ViewState(AppDemoR.layout.view_color_item)