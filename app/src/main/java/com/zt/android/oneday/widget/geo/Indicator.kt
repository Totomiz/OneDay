package com.zt.android.oneday.widget.geo

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import java.util.*

abstract class Indicator : Drawable(), Animatable {

    private val mUpdateListeners = HashMap<ValueAnimator, ValueAnimator.AnimatorUpdateListener>()

    private var mAnimators: ArrayList<ValueAnimator>? = null
    private var alpha = 255
    var drawBounds = ZERO_BOUNDS_RECT


    private var mHasAnimators: Boolean = false

    var mPaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    var color: Int
        get() = mPaint.color
        set(color) {
            mPaint.color = color
        }

    private val isStarted: Boolean
        get() {
            for (animator in mAnimators!!) {
                return animator.isStarted
            }
            return false
        }

    val width: Int
        get() = drawBounds.width()

    val height: Int
        get() = drawBounds.height()

    override fun setAlpha(alpha: Int) {
        this.alpha = alpha
    }

    override fun getAlpha(): Int {
        return alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun draw(canvas: Canvas) {
        draw(canvas, mPaint)
    }

    abstract fun draw(canvas: Canvas, paint: Paint)

    abstract fun onCreateAnimators(): ArrayList<ValueAnimator>

    override fun start() {
        ensureAnimators()

        if (mAnimators == null) {
            return
        }

        // If the animators has not ended, do nothing.
        if (isStarted) {
            return
        }
        startAnimators()
        invalidateSelf()
    }

    private fun startAnimators() {
        mAnimators?.apply {
            for (i in this.indices) {
                val animator = this[i]

                //when the animator restart , add the updateListener again because they
                // was removed by animator stop .
                val updateListener = mUpdateListeners[animator]
                if (updateListener != null) {
                    animator.addUpdateListener(updateListener)
                }
                animator.start()
            }
        }

    }

    private fun stopAnimators() {
        if (mAnimators != null) {
            for (animator in mAnimators!!) {
                if (animator != null && animator.isStarted) {
                    animator.removeAllUpdateListeners()
                    animator.end()
                }
            }
        }
    }

    private fun ensureAnimators() {
        if (!mHasAnimators) {
            mAnimators = onCreateAnimators()
            mHasAnimators = true
        }
    }

    override fun stop() {
        stopAnimators()
    }

    override fun isRunning(): Boolean {
        mAnimators?.apply {
            for (animator in this) {
                return animator.isRunning
            }
        }

        return false
    }

    /**
     * Your should use this to add AnimatorUpdateListener when
     * create animator , otherwise , animator doesn't work when
     * the animation restart .
     * @param updateListener
     */
    fun addUpdateListener(animator: ValueAnimator, updateListener: ValueAnimator.AnimatorUpdateListener) {
        mUpdateListeners[animator] = updateListener
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        this.drawBounds = bounds
    }

    fun setDrawBounds(left: Int, top: Int, right: Int, bottom: Int) {
        this.drawBounds = Rect(left, top, right, bottom)
    }

    fun postInvalidate() {
        invalidateSelf()
    }


    fun centerX(): Int {
        return drawBounds.centerX()
    }

    fun centerY(): Int {
        return drawBounds.centerY()
    }

    fun exactCenterX(): Float {
        return drawBounds.exactCenterX()
    }

    fun exactCenterY(): Float {
        return drawBounds.exactCenterY()
    }

    companion object {
        private val ZERO_BOUNDS_RECT = Rect()
    }

}
