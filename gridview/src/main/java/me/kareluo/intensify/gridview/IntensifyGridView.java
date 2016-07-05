package me.kareluo.intensify.gridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by felix on 16/7/4.
 */
public class IntensifyGridView extends RecyclerView implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "IntensifyGridView";

    private int mExtraCount = 0;

    private int mEllipsize = NONE;

    private int mHorizontalSpacing = 0;

    private int mVerticalSpacing = 0;

    private int mMaxLength = Integer.MAX_VALUE;
    private int mMaxLines = Integer.MAX_VALUE;

    private GestureDetector mGestureDetector;

    private IntensifyItemDecoration mDecoration;

    private IntensifyGridLayoutManager mLayoutManager;

    protected OnItemClickListener mOnItemClickListener;

    protected OnItemLongClickListener mOnItemLongClickListener;

    public static final int RECTANGLE = 1;
    public static final int SQUARE = 3;

    public static final int NONE = 0;
    public static final int START = 1;
    public static final int END = 3;

    public static final int TYPE_ELLIPSIZE = -2;
    public static final int TYPE_EXTRA = -3;

    public IntensifyGridView(Context context) {
        this(context, null, 0);
    }

    public IntensifyGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IntensifyGridView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs, defStyle);
    }

    protected void initialize(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IntensifyGridView);

        mMaxLength = a.getInt(R.styleable.IntensifyGridView_maxLength, Integer.MAX_VALUE);

        mMaxLines = a.getInt(R.styleable.IntensifyGridView_maxLines, Integer.MAX_VALUE);

        mEllipsize = a.getInt(R.styleable.IntensifyGridView_ellipsize, NONE);

        mExtraCount = a.getInt(R.styleable.IntensifyGridView_extraCount, mExtraCount);

        mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.IntensifyGridView_horizontalSpacing, mHorizontalSpacing);

        mVerticalSpacing = a.getDimensionPixelSize(R.styleable.IntensifyGridView_verticalSpacing, mVerticalSpacing);

        a.recycle();

        mDecoration = new IntensifyItemDecoration(mVerticalSpacing, mHorizontalSpacing);
        addItemDecoration(mDecoration);

        mLayoutManager = new IntensifyGridLayoutManager(context, attrs, defStyle, 0);
        mLayoutManager.setSpacing(mVerticalSpacing, mHorizontalSpacing);

        mDecoration.setOrientation(mLayoutManager.getOrientation());
        setLayoutManager(mLayoutManager);
        mGestureDetector = new GestureDetector(context, new IntensifyGestureListener());
        addOnItemTouchListener(this);
    }

    public void setMaxLines(int maxLines) {
        mMaxLines = maxLines;
    }

    public int getMaxLines() {
        return mMaxLines;
    }

    public void setMaxLength(int maxLength) {
        mMaxLength = maxLength;
    }

    public int getMaxLength() {
        return mMaxLength;
    }

    public int getExtraCount() {
        return mExtraCount;
    }

    public void setLayoutManager(IntensifyGridLayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
    }

    @Override
    public IntensifyGridLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (!(adapter instanceof IntensifyGridAdapter)) {
            throw new IllegalArgumentException("adapter need extends IntensifyGridAdapter.");
        }
        super.setAdapter(adapter);
    }

    public void setAdapter(IntensifyGridAdapter adapter) {
        super.setAdapter(adapter);
    }

    public void setEllipsize(int ellipsize) {
        mEllipsize = ellipsize;
    }

    public int getEllipsize() {
        return mEllipsize;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public void setSpacing(int verticalSpacing, int horizontalSpacing) {
        mDecoration.set(verticalSpacing, horizontalSpacing);
        mLayoutManager.setSpacing(verticalSpacing, horizontalSpacing);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class IntensifyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = findChildViewUnder(e.getX(), e.getY());
            if (view != null && mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getChildViewHolder(view));
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View view = findChildViewUnder(e.getX(), e.getY());
            if (view != null && mOnItemLongClickListener != null) {
                mOnItemLongClickListener.onItemLongClick(getChildViewHolder(view));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewHolder holder);
    }
}