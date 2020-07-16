package com.zt.android.oneday.util

import android.view.MotionEvent
import android.view.View

class AutoHideViewUtils(private val duration: Long = 100) {

    fun execHide(view: View): AutoHideViewUtils {
        view.animate().cancel()
        view.animate().translationY(view.height.toFloat()).setDuration(duration).start()
        return this
    }

    fun execShow(view: View): AutoHideViewUtils {
        view.animate().cancel()
        view.animate().translationY(0f).setDuration(duration).start()
        return this
    }

//    var hideActionButtonRunnable = Runnable { autoHideViewUtils.execHide(mActionButtonGroup).execShow(viewAdGroup) }
//
//    fun onTouchEvent(event: MotionEvent?): Boolean {
//        if (event != null) {
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    isTouching = true
//                    if (isAdLoaded) {
//                        viewAdGroup.removeCallbacks(hideActionButtonRunnable)
//                        autoHideViewUtils.execHide(viewAdGroup).execShow(mActionButtonGroup)
//                    }
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    isTouching = true
//                }
//                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
//
//                    //Log.d("GameManager", "onTouchEvent: up");
//                    isTouching = false
//                    if (isAdLoaded) {
//                        viewAdGroup.removeCallbacks(hideActionButtonRunnable)
//                        viewAdGroup.postDelayed(hideActionButtonRunnable, hideDelay)
//                    }
//                }
//            }
//        }
//        return super.onTouchEvent(event)
//    }
}



