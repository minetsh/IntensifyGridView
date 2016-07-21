package me.kareluo.intensify.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by felix on 16/7/20.
 */
public class ExtraItemView extends FrameLayout {

    public ExtraItemView(Context context) {
        this(context, null, 0);
    }

    public ExtraItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtraItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.layout_extra, this);

    }
}
