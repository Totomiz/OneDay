package com.zt.android.oneday.activity.testtouch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.zt.android.oneday.R
import com.zt.android.oneday.base.context.BaseActivity
import com.zt.android.oneday.extentions.gone
import com.zt.android.oneday.extentions.visible
import com.zt.android.oneday.widget.touchtest.TouchTestView
import kotlinx.android.synthetic.main.activity_testtouch.*

class TestTouchActivity : BaseActivity(), Handler.Callback {
    val TAG = com.zt.android.oneday.widget.touchtest.TAG_PRE + "_Activity"

    val handler = Handler(Looper.getMainLooper(), this)
    lateinit var view: TouchTestView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = TouchTestView(this).apply {
            val la = ViewGroup.LayoutParams(700, 700)
            layoutParams = la
            setOnClickListener {
                Log.d(TAG, "onClick() called $test")
            }
        }
        setContentView(view)

    }

    override fun onClick(view: View?) {
        super.onClick(view)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "dispatchTouchEvent() called with: ev = $ev")
        val value = super.dispatchTouchEvent(ev)
        Log.d(TAG, "dispatchTouchEvent() ：super.dispatchTouchEvent(ev)=$value")
        return value
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouchEvent() called with: event = $event")
        val value = super.onTouchEvent(event)
        Log.d(TAG, "onTouchEvent() super.onTouchEvent(event) = $value")
//        event?.apply {
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    handler.sendEmptyMessage(1)
//                    Log.d(TAG, "onTouchEvent() called ACTION_DOWN")
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    Log.d(TAG, "onTouchEvent() called ACTION_MOVE")
//                }
//                MotionEvent.ACTION_UP -> {
//                    handler.sendEmptyMessage(2)
//                    Log.d(TAG, "onTouchEvent() called ACTION_UP")
//                }
//            }
//        }
        return false
    }

    override fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            1 -> {
                handler.removeMessages(2)
                view.visible()
            }
            2 -> {
                view.gone()
            }
        }
        return false
    }


}