package com.sankuai.meituan.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author 段超
 * @date 2021/1/6 21:46
 * @desc 文件描述
 */
public class XXXLayout extends LinearLayout {

    private static final int DEFAULT_PAINT_FLAGS =
            Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG;

    private final Path mPath = new Path();
    private final Paint mBackgroundPaint = createPaint();

    private int mRadius = DisplayUtils.dp2px(getContext(), 50);


    private int mMaxHeight;

    private int mMoveHeight;

    private final RectF mRectF = new RectF();

    public XXXLayout(Context context) {
        super(context);
    }

    public XXXLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XXXLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private Paint createPaint() {
        Paint paint = new Paint(DEFAULT_PAINT_FLAGS);
//        paint.setColor(0xffffff00);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(0.5f);
        return paint;
    }

    public void setRadius(int radius) {
        mRadius = radius;
        invalidate();
    }

    public void setColor(int color) {
        mBackgroundPaint.setColor(color);
        invalidate();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mPath.reset();
        mPath.moveTo(0, 0);
        int currentRadiusY = calculateCurrentRadiusY();
        if (currentRadiusY >= 0 && currentRadiusY < height) {
            arcTo();
        } else {
            mPath.lineTo(0, height);
        }
        mPath.lineTo(width, height);
        mPath.lineTo(width, 0);
        mPath.close();
        canvas.drawPath(mPath, mBackgroundPaint);
    }


    private void arcTo() {
        int y = calculateCurrentRadiusY();
        mRectF.left = 0;
        mRectF.top = y - mRadius;
        mRectF.right = 2 * mRadius;
        mRectF.bottom = y + mRadius;
        mPath.arcTo(mRectF, 180, calculateAngle());
    }

    public void set(int maxHeight, int moveHeight) {
        int height = getMeasuredHeight();
        if (maxHeight - moveHeight < height) {
            moveHeight = maxHeight - height;
        }
        mMaxHeight = maxHeight;
        mMoveHeight = moveHeight;
        invalidate();
    }


    /**
     * 当前圆心的Y坐标
     */
    private int calculateCurrentRadiusY() {
        return mMaxHeight - mRadius - mMoveHeight;
    }


    private float calculateAngle() {
        //直角边
        double Y = getMeasuredHeight() - calculateCurrentRadiusY() * 1.0;
        return -(float) Math.toDegrees(Math.asin(Y / mRadius));
    }
}
