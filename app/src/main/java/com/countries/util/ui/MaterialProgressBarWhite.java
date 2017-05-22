package com.countries.util.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.countries.R;


public class MaterialProgressBarWhite extends View {

    private CircularProgressDrawable mCircleDrawable;

    public MaterialProgressBarWhite(Context context) {
        this(context, null);
    }

    public MaterialProgressBarWhite(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialProgressBarWhite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //TODO: setup deprecate proof color extraction
        int color = context.getResources().getColor(R.color.colorWhite);

        mCircleDrawable = new CircularProgressDrawable(color, 8);
        mCircleDrawable.setCallback(this);
        if (getVisibility() == VISIBLE) {
            mCircleDrawable.start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCircleDrawable != null) {
            mCircleDrawable.draw(canvas);
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (mCircleDrawable != null) {
            if (visibility == VISIBLE) {
                mCircleDrawable.start();
            } else {
                mCircleDrawable.stop();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mCircleDrawable != null) {
            mCircleDrawable.setBounds(0, 0, w, h);
        }
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return who == mCircleDrawable || super.verifyDrawable(who);
    }
}
