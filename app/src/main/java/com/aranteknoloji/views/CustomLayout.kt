package com.aranteknoloji.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aranteknoloji.trainingcustomlayout.R
import kotlinx.android.synthetic.main.activity_main.view.*

class CustomLayout(
    context: Context,
    attributeSet: AttributeSet
) : ViewGroup(context, attributeSet) {

    private lateinit var myText: TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        myText = findViewById(R.id.my_text)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val lp = myText.layoutParams as MarginLayoutParams
        val viewWidth = myText.measuredWidth + lp.leftMargin + lp.rightMargin
        val viewHeight = myText.measuredHeight + lp.topMargin + lp.bottomMargin

        val width = viewWidth + paddingLeft + paddingRight
        val height = viewHeight + paddingTop + paddingBottom

        setMeasuredDimension(View.resolveSize(width, widthMeasureSpec), View.resolveSize(height, heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val lp = myText.layoutParams as MarginLayoutParams
        val left = paddingLeft + lp.leftMargin
        val top = paddingTop + lp.topMargin
        val right = left + myText.measuredWidth
        val bottom = top + myText.measuredHeight

        myText.layout(left, top, right, bottom)
    }

    // in order to generate the Layout Params
    override fun generateLayoutParams(attrs: AttributeSet?): MarginLayoutParams =
        MarginLayoutParams(context, attrs)
}