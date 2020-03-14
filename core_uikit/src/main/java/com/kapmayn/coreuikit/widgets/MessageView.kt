package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import com.kapmayn.coreuikit.utils.ext.makeGone
import com.kapmayn.coreuikit.utils.ext.makeVisible
import com.kapmayn.coreuikit.utils.ext.setVisible
import kotlinx.android.synthetic.main.view_message.view.messageButton
import kotlinx.android.synthetic.main.view_message.view.messageLayout
import kotlinx.android.synthetic.main.view_message.view.messageLoader
import kotlinx.android.synthetic.main.view_message.view.messageTextView

class MessageView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), Populatable<MessageModel> {

    override fun populate(model: MessageModel) {
        with(model) {
            setVisible(isLoaded || isLoading)
            messageLayout.setVisible(isLoaded)
            messageLoader.setVisible(isLoading)

            messageId?.let {
                messageTextView.text = context.getString(it)
            }
            buttonCallback?.let { action ->
                messageButton.makeVisible()
                messageButton.setOnClickListener {
                    action()
                    messageLayout.makeGone()
                    messageLoader.makeVisible()
                }
            } ?: messageButton.makeGone()
        }
    }
}

sealed class MessageModel(
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
    @StringRes open val messageId: Int? = null,
    open val buttonCallback: (() -> Unit)? = null
) : ViewType(R.layout.view_message)

class HiddenMessageModel : MessageModel()

class LoadingMessage : MessageModel(
    isLoading = true
)

class ShowMessageModel(
    @StringRes override val messageId: Int
) : MessageModel(
    isLoaded = true
)

data class RetryMessageModel(
    @StringRes override val messageId: Int,
    override val buttonCallback: () -> Unit
) : MessageModel(
    isLoaded = true,
    messageId = messageId,
    buttonCallback = buttonCallback
)