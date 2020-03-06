package com.zt.android.oneday

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.text.format.Formatter
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.net.InetAddress

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val DEFAULT_US = "35.171.223.159"
    private val DEFAULT_SG = "52.221.58.61"
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("com.zt.android.oneday", appContext.packageName)
//        for(i in 0..10000000000L step 100000){
//            Log.d("Teeee", formatSizeBytes(appContext,i))
//            println("Teeee ${ formatSizeBytes(appContext,i)}")
//        }

        Log.d("Teeee",InetAddress.getByName(DEFAULT_US).hostName)
        Log.d("Teeee",InetAddress.getByName(DEFAULT_SG).hostName)
        val ip=Net.getIpAddress(appContext)
        Log.d("Teeee","${ip}--${InetAddress.getByName(ip).hostName}")

        val ip2=Net.getLocalIp()
        Log.d("Teeee","${ip2}--${InetAddress.getByName(ip2).hostName}")
//        listOf<Long>(1000L,1024L,1024*1024L,1024*1024*1024L).forEach {
//
//        }
    }

//    fun formatSizeBytes(context: Context, sizeBytes: Long): String {
//        return Formatter.formatFileSize(context, sizeBytes)
//    }



    fun formatSizeBytes(context: Context, sizeBytes: Long): String {
        var speed=0f
        val result: String
        if (sizeBytes > 1024 * 1024) {
            speed = sizeBytes/(1024 * 1024).toFloat()
            result =context.getString(R.string.speed_size_mb, speed)
        } else {
            speed = sizeBytes/1024f
            result =context.getString(R.string.speed_size_kb, speed)
        }
        return result
    }



}
