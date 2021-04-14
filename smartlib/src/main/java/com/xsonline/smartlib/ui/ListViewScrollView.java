package com.xsonline.smartlib.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ListViewScrollView extends ListView {

    public ListViewScrollView(Context context) {
        super(context);
    }

    public ListViewScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListViewScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int ex = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, ex);
    }

}

