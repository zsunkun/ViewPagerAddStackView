package com.stackview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by sunkun on 2015/11/24.
 */
public class DisplaySideViewPager extends RedefineViewPager {
    private final String TAG = "DisplaySideViewPager";
    private final float ALPHA_BASE = 0.15f;
    private final int PAGE_LEFT = 0;
    private final int PAGE_RIGHT = 1;
    private final int FIRST_PAGE_LAYOUT_X = 100;

    private float mCurrentPageAlpha;
    DecimalFormat mFormat = new DecimalFormat("#0.0");
    View pageLeft, pageRight;

    public DisplaySideViewPager(Context context) {
        super(context);
    }

    public DisplaySideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getCurrentItem() == PAGE_RIGHT)
            return false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        pageLeft = getChildAt(0);
        pageRight = getChildAt(1);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int measuredWidth = getMeasuredWidth();
        int childWidthSize = measuredWidth - getPaddingLeft() - getPaddingRight() - FIRST_PAGE_LAYOUT_X * 2;
        int childHeightSize = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        int size = getChildCount();
        for (int i = 0; i < size; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp == null || !lp.isDecor) {
                    final int widthSpec = MeasureSpec.makeMeasureSpec(
                            childWidthSize, MeasureSpec.EXACTLY);
                    final int heightSpec = MeasureSpec.makeMeasureSpec(
                            childHeightSize, MeasureSpec.EXACTLY);
                    child.measure(widthSpec, heightSpec);
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int painterPosX = FIRST_PAGE_LAYOUT_X;  //当前绘图光标横坐标位置
        int painterPosY = 0;  //当前绘图光标纵坐标位置

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {

            View childView = getChildAt(i);

            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();

            //执行ChildView的绘制
            childView.layout(painterPosX, painterPosY, painterPosX + width, painterPosY + height);

            //记录当前已经绘制到的横坐标位置
            painterPosX += width;
        }
    }

    @Override
    public void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        if (pageLeft == null || pageRight == null)
            return;
        if (position == 0) {
            setPageAlpha(offset);
        }
    }

    private void setPageAlpha(float alpha) {
        if (Math.abs(mCurrentPageAlpha - alpha) < 0.1)
            return;
        pageLeft.setAlpha(1 - alpha + ALPHA_BASE);
        pageRight.setAlpha(alpha + ALPHA_BASE);
        mCurrentPageAlpha = alpha;
    }

}

