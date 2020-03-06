package com.zt.android.oneday.widget.conn

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.LinearInterpolator
import com.free.vpn.proxy.shortcut.widget.conn2.IWaveStyle
import com.zt.android.oneday.R
import com.zt.android.utils.log.DebugLog

class DefaultCircleWaveStylt(private val viewGroup: StateNormalView) : IWaveStyle() {
    val TAG = "StateNormalView"
    private val mCirclePaint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mCirclePaint2 = Paint(Paint.ANTI_ALIAS_FLAG)


    private var mMaxRadius = 0f

    private var mMinRadius = 0f
    private var mCircleRadius1 = 0
    private var mCircleRadius2 = 0

    private var mCircleWaveAnimator1: ValueAnimator? = null

    private var mCircleAlphaAnimator1: ValueAnimator? = null

    private var mCircleWaveAnimator2: ValueAnimator? = null

    private var mCircleAlphaAnimator2: ValueAnimator? = null

    private var mCircleAnimator1: AnimatorSet? = null
    private var mCircleAnimator2: AnimatorSet? = null
    private var mCircleAlpha1: Int = 255
    private var mCircleAlpha2: Int = 255
    private var mAnimatorDuration1: Long = 1000L
    private var mAnimatorDuration2: Long = 800L

    private var mScaleAnimSet: AnimatorSet? = null
    private var mScaleAnim: ValueAnimator? = null

    private var vdc: Drawable? = null
    private var vdc2: Drawable? = null

    private val DOUBLE_RATE = floatArrayOf(1.0f, 1.05f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.05f, 1.0f)
    private val ONE_RATE = floatArrayOf(1.0f, 1.05f, 1.0f)
    private val NONE_RATE = floatArrayOf(1.0f, 1f, 1.0f)
    var isMute = false
    var waveRangWith = dp2px(20f)


    fun initConfig() {
        mCirclePaint1.color = Color.parseColor("#A6B7CF")
        mCirclePaint1.style = Paint.Style.FILL
        mCirclePaint1.strokeWidth = 3f

        mCirclePaint2.color = Color.WHITE
        mCirclePaint2.style = Paint.Style.FILL
        mCirclePaint2.strokeWidth = 3f
        waveRangWith = dp2px(20f)
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        vdc = viewGroup.resources.getDrawable(R.drawable.vpn_button_ring)
        vdc2 = viewGroup.resources.getDrawable(R.drawable.vpn_button_ring2)
        vdc?.mutate()
        vdc2?.mutate()
        initConfig()
    }

    override fun draw(canvas: Canvas, view: StateNormalView) {
        super.draw(canvas, view)
        val crx = (view.width) / 2f
        val cry = (view.height) / 2f

//        if (mCircleAnimator1 != null) {
//            mCirclePaint1.alpha = mCircleAlpha1
//            canvas?.drawCircle(crx, cry, mCircleRadius1.toFloat(), mCirclePaint1)
//        }
//        if (mCircleAnimator2 != null) {
//            mCirclePaint2.alpha = mCircleAlpha2
//            canvas?.drawCircle(crx, cry, mCircleRadius2.toFloat(), mCirclePaint2)
//        }

        canvas?.apply {
            vdc?.alpha = mCircleAlpha1
            vdc?.setBounds((crx - mCircleRadius1).toInt(), (cry - mCircleRadius1).toInt(), (crx + mCircleRadius1).toInt(), (cry + mCircleRadius1).toInt())
            vdc?.draw(canvas)

            vdc2?.alpha = mCircleAlpha2
            vdc2?.setBounds((crx - mCircleRadius2).toInt(), (cry - mCircleRadius2).toInt(), (crx + mCircleRadius2).toInt(), (cry + mCircleRadius2).toInt())
            vdc2?.draw(canvas)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int, childView: View) {
        super.onLayout(changed, l, t, r, b, childView)
        if (changed) {
            mMinRadius = childView.width / 2f
            mMaxRadius = childView.width / 2f + waveRangWith
            startHeartAnim(childView, false)
        }
    }

    private fun startHeartAnim(view: View, isSafe: Boolean) {
        var rateInfo: FloatArray
        rateInfo = if (isSafe) {
            ONE_RATE
        } else {
            DOUBLE_RATE
        }
        if (isMute) {
            rateInfo = NONE_RATE
        }
        val delayMillis = (if (isSafe) 1600 else 2000).toLong()
        mScaleAnim = ObjectAnimator.ofFloat(*rateInfo).apply {
            addUpdateListener {
                (it.animatedValue as Float).apply {
                    view.scaleX = this
                    view.scaleY = this
                }
            }
//            repeatMode = ValueAnimator.RESTART
//            repeatCount = ValueAnimator.INFINITE
            duration = (if (isSafe) 150 else 400).toLong()
            interpolator = LinearInterpolator()
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    if (isSafe) {
                        //减去图片外侧阴影像素
                        drawCircleWave()
//                    drawCircleWave2()
                    } else {
                        //减去图片外侧阴影像素
                        drawCircleWave()
                        viewGroup.postDelayed(Runnable {
                            //减去图片外侧阴影像素
                            drawCircleWave2()
                        }, 400)
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    viewGroup.postDelayed(Runnable { mScaleAnim?.start() }, delayMillis)
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
            start()
        }
    }


    private fun postInvalidate() {
        viewGroup.postInvalidate()
    }

    private fun drawCircleWave() {
        mCircleWaveAnimator1?.cancel()
        mCircleAlphaAnimator1?.cancel()
        mCircleAnimator1?.cancel()
        if (mCircleWaveAnimator1 == null || !mCircleWaveAnimator1!!.isRunning()) {
            mCircleWaveAnimator1 = ValueAnimator.ofInt(mMinRadius.toInt(), mMaxRadius.toInt()).apply {
                addUpdateListener(ValueAnimator.AnimatorUpdateListener { valueAnimator ->
                    mCircleRadius1 = (valueAnimator.animatedValue as Int)
                    DebugLog.d(TAG, "drawCircleWave1---${mCircleRadius1}--${mMinRadius.toInt()}--${mMaxRadius.toInt()}")
                    postInvalidate()
                })
                setDuration(mAnimatorDuration1)
            }
            mCircleAlphaAnimator1 = ValueAnimator.ofInt(40, 0).apply {
                addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
                    mCircleAlpha1 = animation.animatedValue as Int
                })
                setDuration(mAnimatorDuration1)
            }

            mCircleAnimator1 = AnimatorSet().apply {
                playTogether(mCircleWaveAnimator1, mCircleAlphaAnimator1)
            }
            mCircleAnimator1!!.start()
        }
    }


    private fun drawCircleWave2() {
        mCircleWaveAnimator2?.cancel()
        mCircleAlphaAnimator2?.cancel()
        mCircleAnimator2?.cancel()
        if (mCircleWaveAnimator2 == null || !mCircleWaveAnimator2!!.isRunning()) {
            mCircleWaveAnimator2 = ValueAnimator.ofInt(mMinRadius.toInt(), mMaxRadius.toInt()).apply {
                addUpdateListener(ValueAnimator.AnimatorUpdateListener { valueAnimator ->
                    mCircleRadius2 = (valueAnimator.animatedValue as Int)
                    DebugLog.d(TAG, "drawCircleWave2---${mCircleRadius2}--${mMinRadius.toInt()}--${mMaxRadius.toInt()}")
                    postInvalidate()
                })
                setDuration(mAnimatorDuration2)
            }
            mCircleAlphaAnimator2 = ValueAnimator.ofInt(100, 0).apply {
                addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
                    mCircleAlpha2 = animation.animatedValue as Int
                })
                setDuration(mAnimatorDuration2)
            }

            mCircleAnimator2 = AnimatorSet().apply {
                playTogether(mCircleWaveAnimator2, mCircleAlphaAnimator2)
            }
            mCircleAnimator2!!.start()
        }
    }


    private fun dp2px(dpValue: Float): Int {
        val scale = viewGroup.context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}