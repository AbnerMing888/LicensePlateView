package com.vip.plate

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 *AUTHOR:AbnerMing
 *DATE:2023/3/15
 *INTRODUCE:自定义省份简称
 */
class LicensePlateView : LinearLayout {

    //省份简称数据
    private val mLicensePlateList = arrayListOf(
        "京", "津", "渝", "沪", "冀", "晋", "辽", "吉", "黑", "苏",
        "浙", "皖", "闽", "赣", "鲁", "豫", "鄂", "湘", "粤", "琼",
        "川", "贵", "云", "陕", "甘", "青", "蒙", "桂", "宁", "新",
        "藏", "使", "领", "学", "港", "澳",
    )

    private var mSpacing = 10f//间隔

    private var mMarginLeftRight = 10f//左右边距

    private var mMarginTop = 0f//上边距离

    private var mMarginBottom = 60f//下边距离

    private var mLength = 10//长度

    private var mRectHeight = 88f//每个格子高度

    private var mRectMarginTop = 16f//距离上边的高度

    private var mBackGroundColor = Color.GRAY//背景颜色

    private var mRectBackGround = R.drawable.view_shape_soloid_ffffff_radius_2//格子背景

    private var mRectSelectBackGround = R.drawable.view_shape_stroke_8548d2_radius_2//格子选中背景

    private var mIsShowComplete = true//是否显示完成按钮

    private var mCompleteTextColor = Color.BLACK//完成按钮文字颜色

    private var mCompleteTextSize = 16f//完成文字大小

    private var mCompleteText = "完成"//完成文字内容

    private var mCompleteMarginTop = 20f//完成距离上边

    private var mCompleteMarginBottom = 20f//完成距离上边

    private var mCompleteMarginRight = 20f//完成距离右边

    private var mRectTextSize = 16f//格子的文字大小

    private var mRectTextColor = ContextCompat.getColor(context, R.color.text_333333)//格子文字的默认颜色

    private var mRectSelectTextColor =
        ContextCompat.getColor(context, R.color.text_8548D2)//格子文字的选中颜色


    private var mNumProhibit = true//默认禁止

    private var mNumProhibitColor = ContextCompat.getColor(context, R.color.text_999999)//禁止文字颜色

    private var mTempTextViewList = ArrayList<TextView>()//存储的临时View

    private var mTextClickEffect = false//是否触发点击效果

    constructor(
        context: Context
    ) : super(context) {
        initData(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        context.obtainStyledAttributes(attrs, R.styleable.LicensePlateView)
            .apply {
                //整体的背景颜色
                mBackGroundColor = getColor(R.styleable.LicensePlateView_lp_background, Color.parseColor("#f5f5f5"))
                //每个格子的默认背景
                mRectBackGround =
                    getResourceId(R.styleable.LicensePlateView_lp_rect_background, mRectBackGround)

                //每个格子的选中背景
                mRectSelectBackGround =
                    getResourceId(
                        R.styleable.LicensePlateView_lp_rect_select_background,
                        mRectSelectBackGround
                    )

                //格子的文字大小
                mRectTextSize =
                    getDimension(R.styleable.LicensePlateView_lp_rect_text_size, mRectTextSize)
                //格子的文字颜色
                mRectTextColor =
                    getColor(R.styleable.LicensePlateView_lp_rect_text_color, mRectTextColor)
                //格子的选中文字颜色
                mRectSelectTextColor =
                    getColor(
                        R.styleable.LicensePlateView_lp_rect_select_text_color,
                        mRectSelectTextColor
                    )
                //每个格子的边距
                mSpacing = getDimension(R.styleable.LicensePlateView_lp_rect_spacing, mSpacing)
                //每个格子的高度
                mRectHeight = getDimension(R.styleable.LicensePlateView_lp_rect_height, mRectHeight)
                //格子距离上边的距离
                mRectMarginTop =
                    getDimension(R.styleable.LicensePlateView_lp_rect_margin_top, mRectMarginTop)
                //视图距离左右的距离
                mMarginLeftRight =
                    getDimension(
                        R.styleable.LicensePlateView_lp_margin_left_right,
                        mMarginLeftRight
                    )
                //视图距离上边的距离
                mMarginTop =
                    getDimension(R.styleable.LicensePlateView_lp_margin_top, mMarginTop)
                //视图距离下边的距离
                mMarginBottom =
                    getDimension(R.styleable.LicensePlateView_lp_margin_bottom, mMarginBottom)
                //视图距离左右的距离
                mMarginLeftRight =
                    getDimension(
                        R.styleable.LicensePlateView_lp_margin_left_right,
                        mMarginLeftRight
                    )
                //是否显示完成按钮
                mIsShowComplete = getBoolean(R.styleable.LicensePlateView_lp_is_show_complete, true)
                //完成按钮文字颜色
                mCompleteTextColor =
                    getColor(R.styleable.LicensePlateView_lp_complete_text_color, Color.parseColor("#087EFD"))
                //完成按钮文字大小
                mCompleteTextSize =
                    getDimension(
                        R.styleable.LicensePlateView_lp_complete_text_size,
                        mCompleteTextSize
                    )
                //完成按钮文字内容
                getString(R.styleable.LicensePlateView_lp_complete_text)?.let {
                    mCompleteText = it
                }
                //完成按钮距离上边
                mCompleteMarginTop =
                    getDimension(
                        R.styleable.LicensePlateView_lp_complete_margin_top,
                        mCompleteMarginTop
                    )
                //完成按钮距离下边
                mCompleteMarginBottom =
                    getDimension(
                        R.styleable.LicensePlateView_lp_complete_margin_bottom,
                        mCompleteMarginBottom
                    )
                //完成按钮距离上边
                mCompleteMarginRight =
                    getDimension(
                        R.styleable.LicensePlateView_lp_complete_margin_right,
                        mCompleteMarginRight
                    )
                //是否触发点击效果
                mTextClickEffect =
                    getBoolean(
                        R.styleable.LicensePlateView_lp_text_click_effect,
                        mTextClickEffect
                    )
            }

        initData(context)
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:初始化数据
     */
    private fun initData(context: Context) {
        //设置背景颜色
        setBackgroundColor(mBackGroundColor)
        orientation = VERTICAL//设置纵向
        //设置距离底部
        setPadding(0, mMarginTop.toInt(), 0, mMarginBottom.toInt())

        if (mIsShowComplete) {
            //添加完成的View视图
            val textView = TextView(context)
            textView.apply {
                setOnClickListener {
                    //点击了完成
                    mKeyboardComplete?.invoke()

                }
                gravity = Gravity.RIGHT
                text = mCompleteText
                setTextColor(mCompleteTextColor)
                textSize = px2sp(mCompleteTextSize)
            }

            addView(textView)
            val submitParams = textView.layoutParams as LayoutParams
            submitParams.apply {
                width = LayoutParams.MATCH_PARENT
                topMargin = mCompleteMarginTop.toInt()
                bottomMargin = (mCompleteMarginBottom - mRectMarginTop).toInt()
                rightMargin = mCompleteMarginRight.toInt()
                textView.layoutParams = this
            }

        }

        //每行对应的省份简称
        var layout: LinearLayout? = null
        //遍历省份简称
        mLicensePlateList.forEachIndexed { index, s ->
            if (index % mLength == 0) {
                //重新创建，并添加View
                layout = createLinearLayout()
                layout?.weightSum = 1f
                addView(layout)
                val params = layout?.layoutParams as LayoutParams
                params.apply {
                    topMargin = mRectMarginTop.toInt()
                    height = mRectHeight.toInt()
                    leftMargin = mMarginLeftRight.toInt()
                    rightMargin = mMarginLeftRight.toInt() - mSpacing.toInt()
                    layout?.layoutParams = this
                }
            }

            //创建文字视图
            val textView = TextView(context).apply {
                text = s
                //设置文字的属性
                textSize = px2sp(mRectTextSize)
                //最后五个是否禁止
                if (mNumProhibit && index > (mLicensePlateList.size - 6)) {
                    setTextColor(mNumProhibitColor)
                    mTempTextViewList.add(this)
                } else {
                    setTextColor(mRectTextColor)
                }

                setBackgroundResource(mRectBackGround)
                gravity = Gravity.CENTER
                setOnClickListener {
                    if (mNumProhibit && index > (mLicensePlateList.size - 6)) {
                        return@setOnClickListener
                    }
                    //每个格子的点击事件
                    changeTextViewState(this)
                }
            }

            addRectView(textView, layout, 0.1f)
        }

        //追加最后一个删除按钮View,动态计算宽度
        addEndView(layout)
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:追加最后一个View
     */
    private fun addEndView(layout: LinearLayout?) {
        val endViewLayout = LinearLayout(context)
        endViewLayout.gravity = Gravity.RIGHT
        //删除按钮
        val endView = RelativeLayout(context)
        //添加删除按钮
        val deleteImage = ImageView(context)
        deleteImage.setImageResource(R.drawable.view_ic_key_delete)
        endView.addView(deleteImage)

        val imageParams = deleteImage.layoutParams as RelativeLayout.LayoutParams
        imageParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        deleteImage.layoutParams = imageParams
        endView.setOnClickListener {
            //删除
            mKeyboardDelete?.invoke()
            invalidate()
        }
        endView.setBackgroundResource(mRectBackGround)
        endViewLayout.addView(endView)
        val params = endView.layoutParams as LayoutParams
        params.width = (getScreenWidth() / mLength) * 2 - mMarginLeftRight.toInt()
        params.height = LayoutParams.MATCH_PARENT

        endView.layoutParams = params

        layout?.addView(endViewLayout)
        val endParams = endViewLayout.layoutParams as LayoutParams
        endParams.apply {
            width = (mSpacing * 3).toInt()
            height = LayoutParams.MATCH_PARENT
            weight = 0.4f
            rightMargin = mSpacing.toInt()
            endViewLayout.layoutParams = this
        }


    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:追加视图
     */
    private fun addRectView(view: View, layout: LinearLayout?, w: Float) {
        layout?.addView(view)
        val textParams = view.layoutParams as LayoutParams
        textParams.apply {
            weight = w
            width = 0
            height = LayoutParams.MATCH_PARENT
            //每行的最后一个
            rightMargin = mSpacing.toInt()
            view.layoutParams = this
        }

    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:改变TextView的状态
     */
    private var mOldTextView: TextView? = null
    private fun changeTextViewState(textView: TextView) {
        if (mTextClickEffect) {
            //点击设置成效果
            textView.setSelectTextStyle()
            textView.postDelayed({
                textView.setUnSelectTextStyle()
            }, 200)
        } else {
            //记录上一个
            mOldTextView?.let {
                it.setUnSelectTextStyle()
            }
            textView.setSelectTextStyle()
            mOldTextView = textView
        }
        //每次点击后进行赋值
        mKeyboardContent?.invoke(textView.text.toString())
    }

    private fun TextView.setSelectTextStyle() {
        setBackgroundResource(mRectSelectBackGround)
        setTextColor(mRectSelectTextColor)
    }

    private fun TextView.setUnSelectTextStyle() {
        setBackgroundResource(mRectBackGround)
        setTextColor(mRectTextColor)
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:创建LinearLayout
     */
    private fun createLinearLayout(): LinearLayout {
        val layout = LinearLayout(context)
        layout.orientation = HORIZONTAL
        return layout
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:获取点击的省份简称简称信息
     */
    private var mKeyboardContent: ((content: String) -> Unit?)? = null
    fun keyboardContent(block: (String) -> Unit) {
        mKeyboardContent = block
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:删除省份简称简称信息
     */
    private var mKeyboardDelete: (() -> Unit?)? = null
    fun keyboardDelete(block: () -> Unit) {
        mKeyboardDelete = block
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:键盘完成
     */
    private var mKeyboardComplete: (() -> Unit?)? = null
    fun keyboardComplete(block: () -> Unit) {
        mKeyboardComplete = block
    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:打开禁止
     */
    fun openProhibit(isOpen: Boolean) {
        //禁止解开
        mNumProhibit = isOpen
        mTempTextViewList.forEach {
            if (isOpen) {
                it.setTextColor(mRectTextColor)
            } else {
                it.setTextColor(mNumProhibitColor)
            }
        }
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:获取屏幕的宽
     */
    private fun getScreenWidth(): Int {
        return resources.displayMetrics.widthPixels
    }

    private fun px2sp(pxValue: Float): Float {
        val fontScale = resources.displayMetrics.scaledDensity
        return pxValue / fontScale + 0.5f
    }

}