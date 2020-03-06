package com.zt.android.oneday.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.zt.android.oneday.R
import com.zt.android.oneday.getResource

/**
 * created by ZT on 2020/1/16.
 */
class DotMarqueImageView : ImageView {


    private val mPaintUnselected = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintSelected = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mNormalDotRadius: Float = 0f
    private var mSelectDotRadius: Float=0f
    private var mCurrentPosition: Int = 0
    private var mCentered: Boolean = false
    private var mGapWidth: Float = 0.toFloat()
    var totalCount=3

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val res = resources

        val dotRidus = dip2px(6f).toFloat()
        val dotRidus_select = dip2px(6f).toFloat()
        val defaultGapWidth = dip2px(5.7f).toFloat()
        val defaultCentered = true
        //Retrieve styles attributes
        //TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LinePageIndicator, defStyle, 0);
        mNormalDotRadius = dotRidus
        mSelectDotRadius = dotRidus_select
        mGapWidth = defaultGapWidth
        setStrokeWidth(mSelectDotRadius, mNormalDotRadius)

        mPaintUnselected.setColor(Color.WHITE)
        mPaintSelected.setColor(Color.parseColor("#0087Db"))

        val background: Drawable? = null
        if (background != null) {
            setBackground(background)
        }

    }

    fun setStrokeWidth(selectLineHeight: Float, normalLineHeight: Float) {
        mPaintSelected.style = Paint.Style.STROKE
        mPaintUnselected.style = Paint.Style.STROKE
        mPaintSelected.strokeWidth = selectLineHeight
        mPaintUnselected.strokeWidth = normalLineHeight
        mPaintUnselected.strokeJoin = Paint.Join.ROUND
        mPaintSelected.strokeJoin = Paint.Join.ROUND
        mPaintUnselected.strokeCap = Paint.Cap.ROUND
        mPaintSelected.strokeCap = Paint.Cap.ROUND
        invalidate()
    }

    fun setCurrent(currentPosition: Int) {
        this.mCurrentPosition = currentPosition
        invalidate()
    }

    private fun dip2px(dipValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.apply {
//            save()
//            drawPoint(width / 2f, height / 2f, dotPaint)
//            restore()
//        }
        val lineWidthAndGap = getMaxRadius() + mGapWidth
        val indicatorWidth = totalCount * lineWidthAndGap - mGapWidth
        val paddingTop = paddingTop.toFloat()
        val paddingLeft = paddingLeft.toFloat()
        val paddingRight = paddingRight.toFloat()

        val verticalOffset = paddingTop + (height.toFloat() - paddingTop - paddingBottom.toFloat()) / 2.0f
        var horizontalOffset = paddingLeft
        if (mCentered) {
            horizontalOffset += (width.toFloat() - paddingLeft - paddingRight) / 2.0f - indicatorWidth / 2.0f
        }

        //Draw stroked circles
        for (i in 0 until totalCount) {
            val dx1 = horizontalOffset + i * lineWidthAndGap
            val dx2 = dx1 + getMaxRadius() / 2
            canvas?.drawPoint(dx2, verticalOffset, if (i == mCurrentPosition) mPaintSelected else mPaintUnselected)
            //            canvas.drawLine(dx1, verticalOffset, dx2, verticalOffset, (i == mCurrentPosition) ? mPaintSelected : mPaintUnselected);
        }
    }



    private fun getMaxRadius(): Float {
        return Math.max(mNormalDotRadius, mSelectDotRadius)
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private fun measureWidth(measureSpec: Int): Int {
        var result: Float
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            //We were told how big to be
            result = specSize.toFloat()
        } else {
            //Calculate the width according the views count
            val count = totalCount

            result = paddingLeft.toFloat() + paddingRight.toFloat() + count * getMaxRadius() + (count - 1) * mGapWidth
            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize.toFloat())
            }
        }
        return Math.ceil(result.toDouble()).toInt()
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private fun measureHeight(measureSpec: Int): Int {
        var result: Float
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            //We were told how big to be
            result = specSize.toFloat()
        } else {
            //Measure the height
            result = getMaxRadius() + paddingTop.toFloat() + paddingBottom.toFloat()
            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize.toFloat())
            }
        }
        return Math.ceil(result.toDouble()).toInt()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
    }

}
