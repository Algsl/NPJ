package com.zthx.npj.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;
import com.zthx.npj.R;

@SuppressLint("AppCompatCustomView")
public class TabRadioButton extends RadioButton {

    private int mDrawableSize_w;// 宽
    private int mDrawableSize_h;// 高
    private int mDrawableSize;// 高

    public TabRadioButton(Context context) {
        this(context, null, 0);
    }

    public TabRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TabRadioButton);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            Log.i("MyRadioButton", "attr:" + attr);
            switch (attr) {
                case R.styleable.TabRadioButton_drawableSize:
                    mDrawableSize = a.getDimensionPixelSize(R.styleable.TabRadioButton_drawableSize, 50);
                    Log.i("MyRadioButton", "mDrawableSize:" + mDrawableSize);
                    break;
                case R.styleable.TabRadioButton_drawableHighSize:
                    mDrawableSize_h = a.getDimensionPixelSize(R.styleable.TabRadioButton_drawableHighSize, 50);
                    Log.i("MyRadioButton", "mDrawableSize:" + mDrawableSize);
                    break;
                case R.styleable.TabRadioButton_drawableWideSize:
                    mDrawableSize_w = a.getDimensionPixelSize(R.styleable.TabRadioButton_drawableWideSize, 50);
                    Log.i("MyRadioButton", "mDrawableSize:" + mDrawableSize);
                    break;
                case R.styleable.TabRadioButton_drawableTop:
                    drawableTop = a.getDrawable(attr);
                    break;
                case R.styleable.TabRadioButton_drawableBottom:
                    drawableRight = a.getDrawable(attr);
                    break;
                case R.styleable.TabRadioButton_drawableRight:
                    drawableBottom = a.getDrawable(attr);
                    break;
                case R.styleable.TabRadioButton_drawableLeft:
                    drawableLeft = a.getDrawable(attr);
                    break;
                default:
                    break;
            }
        }
        a.recycle();

        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);

    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
                                                        Drawable top, Drawable right, Drawable bottom) {

        if (left != null) {
            left.setBounds(0, 0, mDrawableSize_w, mDrawableSize_h);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableSize_w, mDrawableSize_h);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableSize_w, mDrawableSize_h);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableSize_w, mDrawableSize_h);
        }
        setCompoundDrawables(left, top, right, bottom);
    }

}
