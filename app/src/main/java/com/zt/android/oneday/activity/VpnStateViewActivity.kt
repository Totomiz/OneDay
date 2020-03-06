package com.zt.android.oneday.activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.Formatter
import android.view.View
import com.zt.android.oneday.R
import com.zt.android.utils.log.DebugLog
import kotlinx.android.synthetic.main.activty_conn_state.*

/**
 * created by ZT on 2019/12/19.
 */
class VpnStateViewActivity : AppCompatActivity() {
    companion object {
        private val TAG = " TLifeCycActivity"

    }

    val myLife = MyLife()
    var isShow = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DebugLog.d(TAG, "onCreate() called with: savedInstanceState = [$savedInstanceState]")
        lifecycle.addObserver(myLife)
        setContentView(R.layout.activty_conn_state)
        myLife.lif = lifecycle
//        v1.setOnClickListener {
//            DebugLog.d(TAG, "setOnClickListener() called")
//            if (isShow/2==0) {
//                v3.visibility = View.GONE
//            } else {
//                v3.visibility = View.VISIBLE
//            }
//            isShow += 1
//        }
        findViewById<View>(R.id.state_v1).setOnClickListener {
            DebugLog.d(TAG, "setOnClickListener() called")
            if (isShow % 2 == 0) {
                v3.visibility = View.GONE
            } else {
                v3.visibility = View.VISIBLE
            }
            isShow += 1
        }
//        v2.setOnClickListener {
//
//        }
    }

    override fun onStart() {
        DebugLog.d(TAG, "onStart() called")
        super.onStart()
        DebugLog.d(TAG, "onStart() called")
    }

    override fun onStop() {
        DebugLog.d(TAG, "onStop() called")
        super.onStop()
        DebugLog.d(TAG, "onStop() called")
    }

    override fun onPause() {
        DebugLog.d(TAG, "onPause() called")
        super.onPause()
        DebugLog.d(TAG, "onPause() called")
    }

    override fun onResume() {
        DebugLog.d(TAG, "onResume() called")
        super.onResume()
        DebugLog.d(TAG, "onResume() called")
    }

    fun formatSizeBytes(context: Context, sizeBytes: Long): String {
        return Formatter.formatFileSize(context, sizeBytes)
    }

    override fun onDestroy() {
        DebugLog.d(TAG, "onDestroy() called pre")
        super.onDestroy()
        DebugLog.d(TAG, "onDestroy() called after")
    }

    class MyLife() : LifecycleObserver {
        var lif: Lifecycle? = null

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onClear() {
            DebugLog.d(TAG, "onClear() called ,${lif?.currentState}")
        }
    }
}