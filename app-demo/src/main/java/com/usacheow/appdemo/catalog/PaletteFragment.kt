package com.usacheow.appdemo.catalog

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.MaterialColors
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.appdemo.databinding.ViewColorItemBinding
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.color
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
        ColorItem(CoreUiR.color.primary),
        ColorItem(CoreUiR.color.onPrimary),
        ColorItem(CoreUiR.color.primaryContainer),
        ColorItem(CoreUiR.color.onPrimaryContainer),
        ColorItem(CoreUiR.color.primaryInverse),
        SubtitleTileItem(TextSource.Simple("Secondary")),
        ColorItem(CoreUiR.color.secondary),
        ColorItem(CoreUiR.color.onSecondary),
        ColorItem(CoreUiR.color.secondaryContainer),
        ColorItem(CoreUiR.color.onSecondaryContainer),
        SubtitleTileItem(TextSource.Simple("Tertiary")),
        ColorItem(CoreUiR.color.tertiary),
        ColorItem(CoreUiR.color.onTertiary),
        ColorItem(CoreUiR.color.tertiaryContainer),
        ColorItem(CoreUiR.color.onTertiaryContainer),
        SubtitleTileItem(TextSource.Simple("Error")),
        ColorItem(CoreUiR.color.error),
        ColorItem(CoreUiR.color.onError),
        ColorItem(CoreUiR.color.errorContainer),
        ColorItem(CoreUiR.color.onErrorContainer),
        SubtitleTileItem(TextSource.Simple("Background")),
        ColorItem(CoreUiR.color.background),
        ColorItem(CoreUiR.color.onBackground),
        SubtitleTileItem(TextSource.Simple("Surface")),
        ColorItem(CoreUiR.color.surface),
        ColorItem(CoreUiR.color.onSurface),
        ColorItem(CoreUiR.color.surfaceVariant),
        ColorItem(CoreUiR.color.onSurfaceVariant),
        ColorItem(CoreUiR.color.surfaceInverse),
        ColorItem(CoreUiR.color.onSurfaceInverse),
        SubtitleTileItem(TextSource.Simple("Text/Icons")),
        ColorItem(CoreUiR.color.symbolPrimary),
        ColorItem(CoreUiR.color.symbolPrimaryInverse),
        ColorItem(CoreUiR.color.symbolSecondary),
        ColorItem(CoreUiR.color.symbolSecondaryInverse),
        ColorItem(CoreUiR.color.symbolTertiary),
        ColorItem(CoreUiR.color.symbolTertiaryInverse),
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
        val color = color(model.color)
        setCardBackgroundColor(color)
        nameTextView.text = resources.getResourceName(model.color)
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
    @ColorRes val color: Int,
) : ViewState(AppDemoR.layout.view_color_item)