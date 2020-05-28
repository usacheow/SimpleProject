package  com.usacheow.simpleapp.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.usacheow.simpleapp.mainscreen.MainScreenActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationIntent = Intent(context, MainScreenActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val model = NotificationHelper.NotificationModel(
            title = "Title",
            text = "Message",
            intent = notificationIntent
        )

        NotificationHelper(context).showSimpleNotification(model)
    }
}