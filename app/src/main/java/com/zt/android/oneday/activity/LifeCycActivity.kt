package com.zt.android.oneday.activity

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.OnLifecycleEvent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.zt.android.utils.log.DebugLog

/**
 * created by ZT on 2019/12/19.
 */
class LifeCycActivity:AppCompatActivity() {
    companion object{
        private val TAG = " TLifeCycActivity"

    }
    val myLife=MyLife()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DebugLog.d(TAG, "onCreate() called with: savedInstanceState = [$savedInstanceState]")
        lifecycle.addObserver(myLife)
        myLife.lif=lifecycle
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

    override fun onDestroy() {
        DebugLog.d(TAG, "onDestroy() called pre")
        super.onDestroy()
        DebugLog.d(TAG, "onDestroy() called after")
    }

    class MyLife():LifecycleObserver{
        var lif:Lifecycle?=null

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onClear(){
            DebugLog.d(TAG, "onClear() called ,${lif?.currentState}")
        }
    }
}