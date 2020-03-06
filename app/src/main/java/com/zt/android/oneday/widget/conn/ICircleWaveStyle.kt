package com.free.vpn.proxy.shortcut.widget.conn2

import android.graphics.Canvas
import android.view.View
import com.zt.android.oneday.widget.conn.StateNormalView

abstract class IWaveStyle {
    open fun draw(canvas: Canvas, view: StateNormalView) {

    }

    open fun onFinishInflate() {
    }

    open fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int, childView: View) {

    }
}

