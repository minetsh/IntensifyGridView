package me.kareluo.intensify.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by felix on 16/7/21.
 */
public class TextItemView extends FrameLayout {

    private TextView mTextView;

    public TextItemView(Context context) {
        this(context, null, 0);
    }

    public TextItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.layout_item, this);
        mTextView = (TextView) findViewById(R.id.tv_text);
    }

    public void update(String text) {
        mTextView.setText(text);
    }
}
