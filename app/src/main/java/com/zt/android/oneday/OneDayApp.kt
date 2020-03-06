package com.zt.android.oneday

import android.app.Application
import com.zt.android.utils.log.DebugLog

/**
 * created by ZT on 2019/12/19.
 */
class OneDayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        DebugLog.setLogEnable(true).setShowPlace(true)
    }

}