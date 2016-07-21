package me.kareluo.intensify.sample;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by felix on 16/7/16.
 */
public class ImageItemView extends FrameLayout {

    private ImageView mImageView;

    private TextView mEllipseView;

    public ImageItemView(Context context) {
        this(context, null, 0);
    }

    public ImageItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.layout_image, this);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mEllipseView = (TextView) findViewById(R.id.tv_count);
    }

    public void update(@DrawableRes int resId) {
        mImageView.setImageResource(resId);
        mEllipseView.setVisibility(GONE);
    }

    public void update(@DrawableRes int resId, int count) {
        mImageView.setImageResource(resId);
        mEllipseView.setText("+" + count);
        mEllipseView.setVisibility(VISIBLE);
    }
}
