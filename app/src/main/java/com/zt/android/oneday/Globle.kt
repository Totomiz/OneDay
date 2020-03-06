package com.zt.android.oneday

import android.app.Application
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.View
import android.widget.Toast
import java.io.File
import java.io.InputStream
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.security.MessageDigest
import java.util.*

lateinit var application: Application

var mainLaunchedCount = 0

val isHotStart: Boolean
    get() {
        return mainLaunchedCount > 1
    }

var launchTime = System.currentTimeMillis()


fun getResource(): Resources {
    return application.resources
}

fun getString(@StringRes id: Int): String {
    return getResource().getString(id)
}

fun getDimension(@DimenRes id: Int): Int {
    return getResource().getDimensionPixelSize(id)
}

fun getDrawable(@DrawableRes id: Int): Drawable {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getResource().getDrawable(id, null)
    } else {
        getResource().getDrawable(id)
    }
}

fun getDrawableByName(name: String): Drawable {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getResource().getDrawable(getDrawableId(name), null)
    } else {
        getResource().getDrawable(getDrawableId(name))
    }
}

fun getBitmapByName(name: String, width: Int, height: Int): Bitmap {
    return getBitmapById(getDrawableId(name), width, height)
}

fun getBitmapById(id: Int, width: Int, height: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.outWidth = width
    options.outHeight = height
    return BitmapFactory.decodeResource(getResource(), id, options)
}

fun getApplicationName(): String {
    return try {
        val packageManager = application.packageManager
        val appInfo = packageManager.getApplicationInfo(getPackageName(), 0)
        packageManager.getApplicationLabel(appInfo).toString()
    } catch (e: Exception) {
        getPackageName()
    }
}

fun getDrawableId(name: String): Int {
    return getResource().getIdentifier(name, "drawable", getPackageName())
}


fun getDrawableUri(@DrawableRes id: Int): Uri {
    return Uri.parse("res://${application.packageName}/$id")
}

fun getResourceId(name: String, type: String): Int {
    return getResource().getIdentifier(name, type, getPackageName())
}

fun getPackageName(): String {
    return application.packageName
}

fun showShortToast(str: String) {
    Toast.makeText(application, str, Toast.LENGTH_SHORT).show()
}

fun showShortToast(@StringRes strId: Int) {
    showShortToast(getString(strId))
}

fun getViewRect(view: View?): RectF {
    val rect = Rect()
    view?.getGlobalVisibleRect(rect)
    return RectF(rect)
}


fun getSystemLanguage(): String {
    return Locale.getDefault().language
}

fun getGenericType(clazz: Class<*>?): Type? {
    if (clazz != null) {
        val genericSuperclass = clazz.genericSuperclass
        if (genericSuperclass is ParameterizedType) {
            val type = genericSuperclass.actualTypeArguments[0]
            return if (type.toString() == "T") {
                getGenericType(clazz.superclass)
            } else {
                type
            }
        }
    }
    return null
}

fun bring2Front(launchClass: Class<*>) {
    val resultIntent = Intent(application, launchClass)
    resultIntent.addCategory(Intent.CATEGORY_LAUNCHER)
    resultIntent.action = Intent.ACTION_MAIN
    resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    application.startActivity(resultIntent)
}

fun <T> getSystemService(name: String): T {
    return application.getSystemService(name) as T
}

fun Calendar.getUtc(): Long {
    return timeInMillis + timeZone.rawOffset
}

fun File.md5(): String {
    return digest("MD5")
}

fun File.getSHA1(): String {
    return digest("SHA-1")
}

fun File.digest(algorithm: String): String {
    return digest(inputStream(), algorithm)
}

fun digest(inputStream: InputStream, algorithm: String): String {
    inputStream.use {
        val md5Instance = MessageDigest.getInstance(algorithm)
        val buffer = ByteArray(1024)
        var size = it.read(buffer)
        while (size != -1) {
            md5Instance.update(buffer, 0, size)
            size = it.read(buffer)
        }
        val digest = md5Instance.digest()
        var result = ""
        digest.forEach {
            var toHexString = Integer.toHexString(it.toInt() and 0xff)
            if (toHexString.length == 1) {
                toHexString = "0$toHexString"
            }
            result += toHexString
        }
        return result
    }

}

fun ByteArray.digest(algorithm: String): String {
    return digest(inputStream(), algorithm)
}

fun ByteArray.md5(): String {
    return digest("MD5")
}

fun ByteArray.getSHA1(): String {
    return digest("SHA-1")
}


inline fun <T : MediaMetadataRetriever?, R> T.use(block: (T) -> R): R {
    var exception: Throwable? = null
    try {
        return block(this)
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when {
            this == null -> {
            }
            exception == null -> release()
            else ->
                try {
                    release()
                } catch (closeException: Throwable) {
                    // cause.addSuppressed(closeException) // ignored here
                }
        }
    }
}

val mainHandler by lazy { Handler(Looper.getMainLooper()) }


fun postOnMain(task: () -> Unit, delay: Long = 0) {
    mainHandler.postDelayed(task, delay)
}

fun ANDROID_ID(): String {
    var android_id = Settings.System.getString(application.contentResolver, Settings.Secure.ANDROID_ID)
    return android_id
}
