package com.usacheow.appdemo

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.corecommon.container.toTextSource
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.coreui.screen.ComposeFragment
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.BadgeTileState
import com.usacheow.coreuicompose.uikit.ButtonContent
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import com.usacheow.corecommon.R as CoreR
import com.usacheow.coreuitheme.R as CoreUiThemeR

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@AndroidEntryPoint
class DemoFragment : ComposeFragment() {

    @Inject
    lateinit var router: DemoRouter

    @Composable
    override fun Screen() {
        val coroutineScope = rememberCoroutineScope()
        val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

        var isDialogVisible by remember { mutableStateOf(false) }

        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            sheetShape = AppTheme.shapes.large,
            sheetContent = { ModalSheetContent() },
        ) {
            ModalBottomContent(
                showDialogClickListener = { isDialogVisible = true },
                showHideModalBottomSheetClickListener = {
                    coroutineScope.launch {
                        if (modalBottomSheetState.isVisible) {
                            modalBottomSheetState.hide()
                        } else {
                            modalBottomSheetState.show()
                        }
                    }
                },
            )
        }

        if (isDialogVisible) {
            AlertDialog(
                title = { Text("Material dialog") },
                text = { Text("Material dialog example") },
                confirmButton = {
                    TextButton(onClick = {
                        isDialogVisible = false
                    }) { ButtonContent(TextValue.Simple("Agree")) }
                },
                dismissButton = {
                    TextButton(onClick = {
                        isDialogVisible = false
                    }) { ButtonContent(TextValue.Simple("Disagree")) }
                },
                onDismissRequest = { isDialogVisible = false },
            )
        }
    }

    @Composable
    private fun ModalBottomContent(
        showDialogClickListener: () -> Unit,
        showHideModalBottomSheetClickListener: () -> Unit,
    ) {
        val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed),
        )

        var sheetPeekHeight by remember { mutableStateOf(0.dp) }
        val sheetPeekHeightAnimated = animateDpAsState(targetValue = sheetPeekHeight)

        BottomSheetScaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            scaffoldState = bottomSheetScaffoldState,
            topBar = {
                SimpleTopAppBar(
                    title = TextValue.Simple("Demo UIkit"),
                    contentPadding = getTopInset(),
                    scrollBehavior = scrollBehavior,
                )
            },
            sheetShape = AppTheme.shapes.large,
            sheetPeekHeight = sheetPeekHeightAnimated.value,
            sheetContent = { SheetContent() },
            content = {
                BottomContent(
                    it,
                    showDialogClickListener = showDialogClickListener,
                    showHideBottomSheetClickListener = {
                        sheetPeekHeight = if (sheetPeekHeight == 0.dp) 144.dp else 0.dp
                    },
                    showHideModalBottomSheetClickListener = showHideModalBottomSheetClickListener,
                )
            },
        )
    }

    @Composable
    private fun BottomContent(
        paddingValues: PaddingValues,
        showDialogClickListener: () -> Unit,
        showHideBottomSheetClickListener: () -> Unit,
        showHideModalBottomSheetClickListener: () -> Unit,
    ) {
        val headerModifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        val cardModifier = Modifier.padding(8.dp)

        val items = items(
            onBoardingArgs = onBoardingArgs(),
            showDialogClickListener = showDialogClickListener,
            showHideBottomSheetClickListener = showHideBottomSheetClickListener,
            showHideModalBottomSheetClickListener = showHideModalBottomSheetClickListener,
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp)
                .padding(paddingValues),
            contentPadding = getBottomInset(),
        ) {
            items(
                items = items,
                span = { if (it is HeaderTileState) GridItemSpan(2) else GridItemSpan(1) },
            ) {
                it.Content(when (it is HeaderTileState) {
                    true -> headerModifier
                    false -> cardModifier
                })
            }
        }
    }

    @Composable
    private fun SheetContent() {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Bottom", style = AppTheme.typography.displayLarge)
            Text(text = "sheet", style = AppTheme.typography.displayLarge)
            Text(text = "example", style = AppTheme.typography.displayLarge)
        }
    }

    @Composable
    private fun ModalSheetContent() {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Modal", style = AppTheme.typography.displayLarge)
            Text(text = "bottom", style = AppTheme.typography.displayLarge)
            Text(text = "sheet", style = AppTheme.typography.displayLarge)
            Text(text = "example", style = AppTheme.typography.displayLarge)
        }
    }

    @Composable
    private fun items(
        onBoardingArgs: OnBoardingFeatureProvider.OnBoardingArgs,
        showDialogClickListener: () -> Unit,
        showHideBottomSheetClickListener: () -> Unit,
        showHideModalBottomSheetClickListener: () -> Unit,
    ) = listOf(
        HeaderTileState.largePrimary(TextValue.Simple("Atoms")),
        BadgeTileState(
            header = TextValue.Simple("atom"),
            value = TextValue.Simple("1. Typography"),
            clickListener = { router.fromDemoToTypographyScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("atom"),
            value = TextValue.Simple("2. Palette"),
            clickListener = { router.fromDemoToPaletteScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("atom"),
            value = TextValue.Simple("3. Buttons"),
            clickListener = { router.fromDemoToButtonsScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("atom"),
            value = TextValue.Simple("4. Text Inputs"),
            clickListener = { router.fromDemoToTextInputsScreen() }
        ),

        HeaderTileState.largePrimary(TextValue.Simple("Molecules")),
        BadgeTileState(
            header = TextValue.Simple("molecule"),
            value = TextValue.Simple("1. Cell Tiles"),
            clickListener = { router.fromDemoToActionTilesScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("molecule"),
            value = TextValue.Simple("2. List Tiles"),
            clickListener = { router.fromDemoToListTilesScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("molecule"),
            value = TextValue.Simple("3. Tag Lists"),
            clickListener = { router.fromDemoToTagListScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("molecule"),
            value = TextValue.Simple("4. Information Tiles"),
            clickListener = { router.fromDemoToInformationTilesScreen() }
        ),

        HeaderTileState.largePrimary(TextValue.Simple("Organisms")),
        BadgeTileState(
            header = TextValue.Simple("organism"),
            value = TextValue.Simple("1. Message Tiles"),
            clickListener = { router.fromDemoToMessageScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("organism"),
            value = TextValue.Simple("2. Num Pad"),
            clickListener = { router.fromDemoToNumPadScreen() }
        ),

        HeaderTileState.largePrimary(TextValue.Simple("Templates")),
        BadgeTileState(
            header = TextValue.Simple("template"),
            value = TextValue.Simple("1. Alert Dialog"),
            clickListener = { showDialogClickListener() }
        ),
        BadgeTileState(
            header = TextValue.Simple("template"),
            value = TextValue.Simple("2. Modal Fragment"),
            clickListener = { router.fromDemoToExampleModalScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("template"),
            value = TextValue.Simple("3. Bottom Dialog"),
            clickListener = { router.fromDemoToExampleBottomDialogScreen() }
        ),
        BadgeTileState(
            header = TextValue.Simple("template"),
            value = TextValue.Simple("4. Bottom sheet"),
            clickListener = { showHideBottomSheetClickListener() }
        ),
        BadgeTileState(
            header = TextValue.Simple("template"),
            value = TextValue.Simple("5. Modal bottom sheet"),
            clickListener = { showHideModalBottomSheetClickListener() }
        ),
        BadgeTileState(
            header = TextValue.Simple("template"),
            value = TextValue.Simple("6. Onboarding Fragment"),
            clickListener = { router.toOnBoardingFlow(onBoardingArgs) }
        ),

        HeaderTileState.largePrimary(TextValue.Simple("Pages")),
        BadgeTileState(
            header = TextValue.Simple("page"),
            value = TextValue.Simple("1. Sign Up Fragment"),
            clickListener = { router.toSignUpFlow() }
        ),
        BadgeTileState(
            header = TextValue.Simple("page"),
            value = TextValue.Simple("2. Sign In Fragment"),
            clickListener = { router.toSignInFlow() }
        ),
        BadgeTileState(
            header = TextValue.Simple("page"),
            value = TextValue.Simple("3. Sign In With Phone Fragment"),
            clickListener = { router.toSignInWithPhoneFlow() }
        ),
        BadgeTileState(
            header = TextValue.Simple("page"),
            value = TextValue.Simple("4. Pin Code Fragment"),
            clickListener = { router.toPinCodeFlow() }
        ),
    )

    @Composable
    private fun onBoardingArgs() = OnBoardingFeatureProvider.OnBoardingArgs(
        mutableListOf(
            OnBoardingFeatureProvider.OnBoardingArgs.Page(
                defaultImageRes = CoreUiThemeR.drawable.ic_user,
                title = stringResource(CoreR.string.on_boarding_title_1).toTextSource(),
                description = stringResource(CoreR.string.on_boarding_description_1).toTextSource()
            ),
            OnBoardingFeatureProvider.OnBoardingArgs.Page(
                defaultImageRes = CoreUiThemeR.drawable.ic_user,
                title = stringResource(CoreR.string.on_boarding_title_2).toTextSource(),
                description = stringResource(CoreR.string.on_boarding_description_2).toTextSource()
            ),
            OnBoardingFeatureProvider.OnBoardingArgs.Page(
                defaultImageRes = CoreUiThemeR.drawable.ic_user,
                title = stringResource(CoreR.string.on_boarding_title_3).toTextSource(),
                description = stringResource(CoreR.string.on_boarding_description_3).toTextSource()
            )
        )
    )
}