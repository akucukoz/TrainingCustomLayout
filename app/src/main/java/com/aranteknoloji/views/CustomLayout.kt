package com.aranteknoloji.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginBottom
import com.aranteknoloji.trainingcustomlayout.R
import kotlinx.android.synthetic.main.activity_main.view.*

class CustomLayout(
    context: Context,
    attributeSet: AttributeSet
) : ViewGroup(context, attributeSet) {

//    override fun onFinishInflate() {
//        super.onFinishInflate()
//        myText = findViewById(R.id.my_text)
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        var layoutHeight = 0
        var layoutWidth = 0

        println("childCount = $childCount")
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            val lp = view.layoutParams as MarginLayoutParams
            val viewWidth = view.measuredWidth + lp.leftMargin + lp.rightMargin
            val viewHeight = view.measuredHeight + lp.topMargin + lp.bottomMargin

            val width = viewWidth + paddingLeft + paddingRight
            val height = viewHeight + paddingTop + paddingBottom

            layoutHeight += height
            if (width > layoutWidth) layoutWidth = width
        }

        setMeasuredDimension(View.resolveSize(layoutWidth, widthMeasureSpec), View.resolveSize(layoutHeight, heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var recentBottom = 0

        for (i in 0 until childCount) {
            val view = getChildAt(i)
            val lp = view.layoutParams as MarginLayoutParams
            val left = paddingLeft + lp.leftMargin
            val top = paddingTop + lp.topMargin + recentBottom
            val right = left + view.measuredWidth
            val bottom = top + view.measuredHeight

            recentBottom = bottom + lp.bottomMargin

            view.layout(left, top, right, bottom)
        }
    }

    // in order to generate the Layout Params
    override fun generateLayoutParams(attrs: AttributeSet?): MarginLayoutParams =
        MarginLayoutParams(context, attrs)
}