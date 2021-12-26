package com.usacheow.appdemo.catalog

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.AttributeSet
import androidx.annotation.StyleRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.appdemo.databinding.ViewTextItemBinding
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.molecule.SubtitleTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt
import com.usacheow.appdemo.R as AppDemoR
import com.usacheow.coreui.R as CoreUiR

@AndroidEntryPoint
class TypographyFragment : SimpleFragment<FragmentListBinding>() {

    @Inject
    lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.widgetsListView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.title = "Typography"
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewStateAdapter(
            listOf(
                SubtitleTileItem(TextSource.Simple("Display"), actionText = TextSource.Simple("Size")),
                TextItem(CoreUiR.style.Simple_Text_Display_L),
                TextItem(CoreUiR.style.Simple_Text_Display_M),
                TextItem(CoreUiR.style.Simple_Text_Display_S),
                SubtitleTileItem(TextSource.Simple("Headline")),
                TextItem(CoreUiR.style.Simple_Text_Headline_L),
                TextItem(CoreUiR.style.Simple_Text_Headline_M),
                TextItem(CoreUiR.style.Simple_Text_Headline_S),
                SubtitleTileItem(TextSource.Simple("Title")),
                TextItem(CoreUiR.style.Simple_Text_Title_L),
                TextItem(CoreUiR.style.Simple_Text_Title_M),
                TextItem(CoreUiR.style.Simple_Text_Title_S),
                SubtitleTileItem(TextSource.Simple("Body")),
                TextItem(CoreUiR.style.Simple_Text_Body_L),
                TextItem(CoreUiR.style.Simple_Text_Body_M),
                TextItem(CoreUiR.style.Simple_Text_Body_S),
                SubtitleTileItem(TextSource.Simple("Label")),
                TextItem(CoreUiR.style.Simple_Text_Label_L),
                TextItem(CoreUiR.style.Simple_Text_Label_M),
                TextItem(CoreUiR.style.Simple_Text_Label_S),
            )
        )
    }
}

class TextItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayoutCompat(context, attrs), Populatable<TextItem> {

    private val binding by lazy { ViewTextItemBinding.bind(this) }

    override fun populate(model: TextItem) {
        binding.nameView.setTextAppearance(model.style)
        binding.sizeView.setTextAppearance(model.style)
        binding.nameView.text = resources.getResourceName(model.style)
            .split("/")
            .lastOrNull()
            ?.removePrefix("Simple.Text.")
            ?.replace('.', ' ')
        binding.sizeView.text = binding.nameView.textSize.toSp.roundToInt().toString()
    }

    private fun String.splitName(divider: String): String {
        if (!contains(divider)) {
            return this
        }

        return split(divider).toMutableList()
            .apply { add(1, divider) }
            .reduce { acc, s -> "%s %s".format(acc, s) }
    }

    private val Float.toSp get() = this / Resources.getSystem().displayMetrics.scaledDensity
}

data class TextItem(
    @StyleRes val style: Int,
) : ViewState(AppDemoR.layout.view_text_item)