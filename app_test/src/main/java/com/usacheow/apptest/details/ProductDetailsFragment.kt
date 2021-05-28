package com.usacheow.apptest.details

import android.content.res.Resources
import android.os.Bundle
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getTopInset
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE
import com.google.android.material.card.MaterialCardView
import com.usacheow.apptest.R
import com.usacheow.apptest.databinding.FragmentProductDetailsBinding
import com.usacheow.coreui.adapter.InfinityViewTypesAdapter
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.navigation
import com.usacheow.coreui.utils.view.toPx
import kotlin.math.abs
import kotlin.math.absoluteValue

class ProductDetailsFragment : SimpleFragment<FragmentProductDetailsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentProductDetailsBinding::inflate,
    )

    private val viewModel by viewModels<DetailsViewModel>()

    private var isTransitionAtEndState = false

    private val productsAdapter = InfinityViewTypesAdapter()
    private val warningsAdapter = ViewTypesAdapter()
    private val operationsAdapter = ViewTypesAdapter()

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.toolbarStub.updatePadding(bottom = insets.getTopInset())
        binding.toolbar.updatePadding(top = insets.getTopInset())
        binding.navigationBarStub.updateLayoutParams<FrameLayout.LayoutParams> { height = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom }
        return insets
    }

    override fun subscribe() {
        viewModel.productType.observe(lifecycle) {
            binding.toolbarTitleView.text = it
        }
        viewModel.balance.observe(lifecycle) {
            binding.balanceView.text = it
        }
        viewModel.state.observe(lifecycle) {
            when (it) {
                is DetailsState.Data -> {
                    productsAdapter.update(it.products)
                    binding.viewPager.setCurrentItem(it.selectedProductIndex, false)
                }
            }
        }
        viewModel.additionalState.observe(lifecycle) {
            when (it) {
                is DetailsAdditionalState.Data -> {
                    warningsAdapter.update(it.warnings)
                    operationsAdapter.update(it.operations)

                    binding.operationsView.post {
                        val canScrollScreen = binding.operationsView.bottom >= binding.root.height
                        binding.motionLayout.getTransition(R.id.operationsTransition).setEnable(canScrollScreen)
                    }
                }
                is DetailsAdditionalState.Loading -> {
                    warningsAdapter.update(it.warnings)
                    operationsAdapter.update(it.operations)

                    binding.motionLayout.getTransition(R.id.operationsTransition).setEnable(false)
                }
            }
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.toolbar.navigation(R.drawable.ic_back) {}
        binding.toolbar.inflateMenu(R.menu.menu_settings)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {

                }
            }
            return@setOnMenuItemClickListener true
        }

        setupLists()
        setupViewPager()

        binding.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                isTransitionAtEndState = true
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                isTransitionAtEndState = true
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                isTransitionAtEndState = true
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                isTransitionAtEndState = p1 == R.id.operations
            }
        })
    }

    private fun setupLists() {
        binding.warningsView.layoutManager = LinearLayoutManager(requireContext())
        binding.warningsView.adapter = warningsAdapter

        binding.operationsListView.layoutManager = LinearLayoutManager(requireContext())
        binding.operationsListView.adapter = operationsAdapter
    }

    private val sideProductVisiblePartPx = 40.toPx.toFloat()
    private val sideProductTopOffsetPx = -20.toPx.toFloat()
    private val selectedProductElevationPx = 32.toPx.toFloat()
    private val sideProductElevationPx = 8.toPx.toFloat()
    private val productImageHeightPercentage = 0.15
    private val productImageRatioHeight = 2
    private val productImageRatioWidth = 3
    private val sideMarginsCount = 2

    private fun setupViewPager() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            private var swipeDirection: SwipeDirection? = null
            private var lastPositionOffset: Float? = null

            override fun onPageScrollStateChanged(state: Int) {
                if (state == SCROLL_STATE_IDLE) {
                    swipeDirection = null
                    lastPositionOffset = null
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (positionOffset == 0f) {
                    return
                }
                onPageScrolled(positionOffset)

                if (lastPositionOffset == null) {
                    lastPositionOffset = positionOffset
                } else {
                    swipeDirection = when {
                        positionOffset > (lastPositionOffset ?: positionOffset) -> SwipeDirection.NEXT
                        else -> SwipeDirection.PREV
                    }
                    lastPositionOffset = null
                }

                if (positionOffset in leftNonAnimationRangeBorder..rightNonAnimationRangeBorder) {
                    val nextPosition = when (swipeDirection) {
                        SwipeDirection.NEXT -> position + 1
                        else -> position
                    }
                    viewModel.onProductSelecting(nextPosition)
                }
            }
        })

        val horizontalMarginPx = Resources.getSystem().displayMetrics.heightPixels
            .times(productImageHeightPercentage)
            .times(productImageRatioWidth)
            .div(productImageRatioHeight)
            .minus(Resources.getSystem().displayMetrics.widthPixels)
            .absoluteValue
            .div(sideMarginsCount)
        val pageTranslationX = sideProductVisiblePartPx + horizontalMarginPx.toFloat()
        binding.viewPager.setPageTransformer { page, position ->
            page.translationX = -pageTranslationX * position
            page.translationY = sideProductTopOffsetPx * abs(position)

            (page as ProductCardTileView).productElevation = -(selectedProductElevationPx - sideProductElevationPx) * abs(position) + selectedProductElevationPx
        }
        binding.viewPager.addItemDecoration(HorizontalMarginItemDecoration(horizontalMarginPx))
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.adapter = productsAdapter
//        binding.dotsView.setViewPager2(binding.viewPager)
        binding.dotsView.attachToPager(binding.viewPager)
    }

    private val rangeLength = 1f
    private val rangeCenter = 0.5f

    private val nonAnimationRangeRadius = 0.1f
    private val leftNonAnimationRangeBorder = rangeCenter - nonAnimationRangeRadius
    private val rightNonAnimationRangeBorder = rangeCenter + nonAnimationRangeRadius

    private val defaultTranslationY = 0f
    private val defaultTranslationX = 0f
    private val defaultAlpha = 1f

    private val balanceAnimationRangeRadius = 0.3f
    private val balanceLeftAnimationRangeBorder = rangeCenter - balanceAnimationRangeRadius
    private val balanceRightAnimationRangeBorder = rangeCenter + balanceAnimationRangeRadius
    private val balanceCoefficient = rangeLength / (balanceAnimationRangeRadius - nonAnimationRangeRadius)
    private val balanceTranslationXVelocity = 100.toPx

    private val shortcutsAnimationRangeRadius = 0.4f
    private val shortcutsLeftAnimationRangeBorder = rangeCenter - shortcutsAnimationRangeRadius
    private val shortcutsRightAnimationRangeBorder = rangeCenter + shortcutsAnimationRangeRadius
    private val shortcutsCoefficient = rangeLength / (shortcutsAnimationRangeRadius - nonAnimationRangeRadius)

    private val bottomPartAnimationRangeRadius = 0.3f
    private val bottomPartLeftAnimationRangeBorder = rangeCenter - bottomPartAnimationRangeRadius
    private val bottomPartRightAnimationRangeBorder = rangeCenter + bottomPartAnimationRangeRadius
    private val bottomPartCoefficient = rangeLength / (bottomPartAnimationRangeRadius - nonAnimationRangeRadius)
    private val bottomPartTranslationYVelocity = 40.toPx

    private fun onPageScrolled(positionOffset: Float) {
        binding.motionLayout.progress = 0f
        updateBalanceState(positionOffset)
        updateShortcutsAndTitleState(positionOffset)
        updateBottomPartState(positionOffset)
    }

    private fun updateBalanceState(positionOffset: Float) {
        val percentage = positionOffset.minus(rangeCenter)
            .absoluteValue
            .minus(nonAnimationRangeRadius)
            .times(balanceCoefficient)

        val (translationX, alpha) = when (positionOffset) {
            in balanceLeftAnimationRangeBorder..rangeCenter -> {
                (percentage - 1) * balanceTranslationXVelocity / 2f to percentage
            }

            in rangeCenter..balanceRightAnimationRangeBorder -> {
                (1 - percentage) * balanceTranslationXVelocity / 2f to percentage
            }

            else -> {
                defaultTranslationX to defaultAlpha
            }
        }

        binding.balanceView.translationX = translationX
        binding.balanceView.alpha = alpha
        binding.nameView.translationX = translationX
        binding.nameView.alpha = alpha
    }

    private fun updateShortcutsAndTitleState(positionOffset: Float) {
        val percentage = positionOffset.minus(rangeCenter)
            .absoluteValue
            .minus(nonAnimationRangeRadius)
            .times(shortcutsCoefficient)

        val alpha = when (positionOffset) {
            in shortcutsLeftAnimationRangeBorder..shortcutsRightAnimationRangeBorder -> percentage

            else -> defaultAlpha
        }
        binding.toolbarTitleView.alpha = alpha
        binding.shortcutsView.alpha = alpha
    }

    private fun updateBottomPartState(positionOffset: Float) {
        val percentage = positionOffset.minus(rangeCenter)
            .absoluteValue
            .minus(nonAnimationRangeRadius)
            .times(bottomPartCoefficient)

        when (positionOffset) {
            in bottomPartLeftAnimationRangeBorder..bottomPartRightAnimationRangeBorder -> {
                binding.warningsView.translationY = (1 - percentage) * bottomPartTranslationYVelocity
                binding.operationsView.translationY = (1 - percentage) * bottomPartTranslationYVelocity
            }

            else -> {
                binding.warningsView.translationY = defaultTranslationY
                binding.operationsView.translationY = defaultTranslationY
            }
        }
    }
}

enum class SwipeDirection {
    PREV,
    NEXT
}