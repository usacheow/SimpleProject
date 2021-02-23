package com.usacheow.apptest

import android.app.IntentService
import android.app.Service
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.usacheow.apptest.databinding.FragmentTestBinding
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import kotlinx.coroutines.*

class TestFragment : SimpleFragment<FragmentTestBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentTestBinding::inflate,
    )

    companion object {
        fun newInstance() = TestFragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Test Screen"
        }

        binding.simpleServiceStartButton.setOnClickListener {
            val intent = Intent(requireActivity(), ServiceImpl::class.java)
            requireActivity().startService(intent)
        }
        binding.simpleServiceStopButton.setOnClickListener {
            val intent = Intent(requireActivity(), ServiceImpl::class.java)
            requireActivity().stopService(intent)
        }

        binding.intentServiceStartButton.setOnClickListener {
            val intent = Intent(requireActivity(), IntentServiceImpl::class.java)
            requireActivity().startService(intent)
        }
        binding.intentServiceStopButton.setOnClickListener {
            val intent = Intent(requireActivity(), IntentServiceImpl::class.java)
            requireActivity().stopService(intent)
        }

        binding.jobServiceStartButton.setOnClickListener {
            val componentName = ComponentName(requireActivity(), JobServiceImpl::class.java)
            val jobInfo = JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build()

            val jobScheduler = requireActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        }
        binding.jobServiceStopButton.setOnClickListener {
            val jobScheduler = requireActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(1)
        }

        binding.jobIntentServiceStartButton.setOnClickListener {
            val intent = Intent(requireActivity(), JobIntentServiceImpl::class.java)
            JobIntentServiceImpl.start(requireActivity(), intent)
        }
        binding.jobIntentServiceStopButton.setOnClickListener {
            val intent = Intent(requireActivity(), JobIntentServiceImpl::class.java)
            requireActivity().stopService(intent)
        }
    }
}

inline fun doWork(arg: () -> Int): Int {
    doNewWork(arg)
//    doOtherWork(arg)
    return arg()
}

inline fun doNewWork(arg: () -> Int): Int {
    return arg()
}

fun doOtherWork(arg: () -> Int): Int {
    return arg()
}

const val LOG_TAG = "myLogs"

class ServiceImpl : Service() {

    private val notificationHelper by lazy { NotificationHelper(this) }
    private var job: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        job = CoroutineScope(Dispatchers.IO).launch {
            (0..2).forEach {
                delay(1000)
                Log.d(LOG_TAG, "ServiceImpl step")

                notificationHelper.showSimpleNotification(
                    NotificationHelper.NotificationModel(
                        title = "ServiceImpl",
                        text = "next step $it",
                        intent = Intent()
                    )
                )
            }

            //вызовется onDestroy
            //stopSelf()
        }

        return START_STICKY
    }

    override fun onCreate() {
        Log.d(LOG_TAG, "onCreate")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")
        job?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LOG_TAG, "onBind")
        return null
    }

}

//не подразумевалось, что их можно остановить вручную
class IntentServiceImpl : IntentService("IntentServiceImpl") {

    private val notificationHelper by lazy { NotificationHelper(this) }

    override fun onHandleIntent(intent: Intent?) {
        (0..2).forEach {
            Thread.sleep(1000)
            Log.d(LOG_TAG, "IntentServiceImpl step")

            notificationHelper.showSimpleNotification(
                NotificationHelper.NotificationModel(
                    title = "IntentServiceImpl",
                    text = "next step $it",
                    intent = Intent()
                )
            )
        }
    }

    override fun onCreate() {
        Log.d(LOG_TAG, "onCreate")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LOG_TAG, "onBind")
        return null
    }

}

class JobServiceImpl : JobService() {

    override fun onCreate() {
        Log.d(LOG_TAG, "onCreate")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")
        super.onDestroy()
    }

    //вызывается, когда настало условие для выполнения
    override fun onStartJob(params: JobParameters?): Boolean {

        val intent = Intent(this, ServiceImpl::class.java)
        startService(intent)

        return true //true - если делегировали выполнение в другой поток
        //false - если всю работу уже выполнили
    }

    //вызывается, когда условия перестали выполняться или время на выполнение истекло
    override fun onStopJob(params: JobParameters?): Boolean {

        val intent = Intent(this, ServiceImpl::class.java)
        stopService(intent)

        return false //false - удалится из очереди, если не была периодической
        //true - задача снова попадёт в очередь на выполнением
    }

}

class JobIntentServiceImpl : JobIntentService() {

    private val notificationHelper by lazy { NotificationHelper(this) }

    companion object {
        fun start(context: Context, intent: Intent) {
            val componentName = ComponentName(context, JobIntentServiceImpl::class.java)
            enqueueWork(context, componentName, 2, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        (0..2).forEach {
            Thread.sleep(1000)
            Log.d(LOG_TAG, "JobIntentServiceImpl step")

            notificationHelper.showSimpleNotification(
                NotificationHelper.NotificationModel(
                    title = "JobIntentServiceImpl",
                    text = "next step $it",
                    intent = Intent()
                )
            )
        }
    }

    override fun onCreate() {
        Log.d(LOG_TAG, "onCreate")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")
        super.onDestroy()
    }

}