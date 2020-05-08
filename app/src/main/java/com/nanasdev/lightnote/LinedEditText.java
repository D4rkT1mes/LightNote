package com.nanasdev.lightnote;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {

    private Rect mRect;
    private Paint mPaint;

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int height = getHeight();
        int line_height = getLineHeight();

        int count = height / line_height;

        if (getLineCount() > count) {
            count = getLineCount();
        }

        Rect r = mRect;
        Paint paint = mPaint;
        int baseline = getLineBounds(0, r) + 10;

        for (int i = 0; i < count; i++) {
            canvas.drawLine(r.left, baseline, r.right, baseline, paint);
            baseline += getLineHeight();
        }

        super.onDraw(canvas);
    }

}
