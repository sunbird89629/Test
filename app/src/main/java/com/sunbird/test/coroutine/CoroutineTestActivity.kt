package com.sunbird.test.coroutine

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.test.databinding.ActivityCoroutineTestBinding
import kotlinx.coroutines.*
import org.xutils.common.util.LogUtil
import kotlin.coroutines.resume

class CoroutineTestActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityCoroutineTestBinding.inflate(layoutInflater)
    }

    private val mainScope by lazy {
        MainScope()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_coroutine_test)
        setContentView(mBinding.root)
        GlobalScope.launch(Dispatchers.Main) {
            for (i in 10 downTo 1) {
                mBinding.tvTest.text = "Countdown ${i}..."
                LogUtil.i("Countdown ${i}-->current thread name:${Thread.currentThread().name}")
                delay(1000)
            }
            mBinding.tvTest.text = "Done!"
        }
        LogUtil.i("onCreated end--->current thread name:${Thread.currentThread().name}")


        //第一种控制流
        requestDataAndUpdateUI()
        //第二种控制流
        requestTowDependencyDataAndReturn()
        //第三种控制流
        requestDirectTowDataAndUpdateUI()

        mBinding.tvTest1.text = "data is loading please wait"
        mBinding.tvTest2.text = "data is loading please wait"
        mBinding.tvTest3.text = "data is loading please wait"

        mBinding.btnLaunchMainScope.setOnClickListener {
            mainScope.launch {
                mBinding.btnLaunchMainScope.text = "start"
                for (i in 10 downTo 1) {
                    delay(1000)
                    mBinding.btnLaunchMainScope.text = "current is ${i}"
                }
                mBinding.btnLaunchMainScope.text = "start main scope"
            }.asAutoDisposable(it)
        }

        mBinding.btnShowDialog.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val result = alert("test title", "message test")
                Toast.makeText(this@CoroutineTestActivity, "result:${result}", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    //通过launch在主线程中创建一个携程，Dispatcher.Main表示在主线程中启动。默认是在IO线程总启动，等待子线程执行完成更新UI
    private fun requestDataAndUpdateUI() = GlobalScope.launch(Dispatchers.Main) {
        mBinding.tvTest1.visibility = View.VISIBLE

        //获取数据，async默认的启动模式是在IO线程中
        val deferred = async {
            delay(2000)
            "data loading has finished"
        }

        //deffered.await()子线程给主线程发送中断，中断现在的事情，去做其他事情
        mBinding.tvTest1.text = deferred.await()
    }

    private fun requestTowDependencyDataAndReturn() = GlobalScope.launch(Dispatchers.Main) {
        //进入携程
        mBinding.tvTest2.visibility = View.VISIBLE
        //withContext相当于async{}.await()
        //await底层原理即给Main线程发送中断不要干这件事情了，去干其他的，即在这里携程挂起
        mBinding.tvTest2.text = withContext(Dispatchers.Default) {
            delay(4000)
            "task1 has finished"
        }

        //第一个任务执行返回后，继续执行第二个任务
        mBinding.tvTest2.text = withContext(Dispatchers.Main) {
            delay(4000)
            "task2 has finished"
        }
    }


    /**
     * 并发请求两条数据
     */
    private fun requestDirectTowDataAndUpdateUI() = GlobalScope.launch(Dispatchers.Main) {
        //进入携程
        mBinding.tvTest3.visibility = View.VISIBLE
        val task1 = async(Dispatchers.Default) {
            delay(2000)
            "task1 has finished"
        }

        val task2 = async(Dispatchers.Default) {
            delay(2000)
            "task2 has finished"
        }

        mBinding.tvTest3.text = "${task1.await()} + ${task2.await()}"
    }

    override fun onDestroy() {
        super.onDestroy()

        //用完销毁
        mainScope.cancel()
    }

    @ExperimentalCoroutinesApi
    suspend fun Context.alert(title: String, message: String): Boolean =
        suspendCancellableCoroutine { continuation ->
            AlertDialog.Builder(this@CoroutineTestActivity)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                    continuation.resume(false)
                }.setPositiveButton("Yes") { dialog, which ->
                    dialog.dismiss()
                    continuation.resume(true)
                }.setOnCancelListener {
                    continuation.resume(false)
                }.create().also { dialog ->
                    continuation.invokeOnCancellation {
                        dialog.dismiss()
                    }
                }.show()
        }
}
