package com.kapmayn.coreui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.kapmayn.core.base.IPopulatable
import com.kapmayn.coreui.R
import com.kapmayn.coreui.utils.makeGone
import com.kapmayn.coreui.utils.makeVisible
import com.kapmayn.coreui.utils.setVisible
import kotlinx.android.synthetic.main.view_message.view.messageButton
import kotlinx.android.synthetic.main.view_message.view.messageLayout
import kotlinx.android.synthetic.main.view_message.view.messageLoader
import kotlinx.android.synthetic.main.view_message.view.messageTextView

class MessageView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), IPopulatable<MessageModel> {

    init {
        View.inflate(context, R.layout.view_message, this)
    }

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
)

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