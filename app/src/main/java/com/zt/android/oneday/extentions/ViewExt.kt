package com.zt.android.oneday.extentions

import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

const val TAG="ViewExt"

fun View.gone(){
    this.visibility=View.GONE
}

fun View.visible(){
    this.visibility=View.VISIBLE
}

fun View.invisible(){
    this.visibility=View.INVISIBLE
}

/**
 * @param zoomOut true缩小 false 还原
 * @param ratio   缩小的比例（0-1）
 * @param views   view数组
 */
private fun scaleView(zoomOut: Boolean, ratio: Float, vararg views: View) {
    for (view in views) {
        if (zoomOut) {
            view.scaleX = ratio
            view.scaleY = ratio
        } else {
            view.scaleX = 1f
            view.scaleY = 1f
        }
    }
}

/**
 * 下划线
 */
fun TextView.applyUnderLine(){
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

/**
 * 触碰缩放
 */
fun View.setTouchScale() {
    this.setOnTouchListener(View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                scaleView(true, 0.8f, this)
                return@OnTouchListener true
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_CANCEL -> scaleView(false, 1.0f, this)
            MotionEvent.ACTION_UP -> {
                scaleView(false, 1.0f, this)
                this.performClick()
            }
        }
        Log.d(TAG, "setTouchScale() called with: v = $v, event = $event")
        false
    })
}
