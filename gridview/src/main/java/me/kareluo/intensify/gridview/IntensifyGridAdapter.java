package me.kareluo.intensify.gridview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class IntensifyGridAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private IntensifyGridView mGridView;

    public IntensifyGridAdapter(@NonNull IntensifyGridView intensifyGridView) {
        mGridView = intensifyGridView;
        mGridView.setAdapter(this);
    }

    @Override
    public final int getItemCount() {
        return Math.min(getMaxCount(), getCount() + mGridView.getExtraCount());
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        switch (getItemViewType(position)) {
            case IntensifyGridView.TYPE_ELLIPSIZE:
                onBindEllipsizeViewHolder(holder, position);
                break;
            case IntensifyGridView.TYPE_EXTRA:
                onBindExtraViewHolder(holder, position);
                break;
            default:
                onBindCommonViewHolder(holder, position);
                break;
        }
    }

    protected void onBindExtraViewHolder(VH holder, int position) {
        onBindCommonViewHolder(holder, position);
    }

    protected void onBindEllipsizeViewHolder(VH holder, int position) {
        onBindCommonViewHolder(holder, position);
    }

    protected abstract void onBindCommonViewHolder(VH holder, int position);

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case IntensifyGridView.TYPE_EXTRA:
                return onCreateExtraViewHolder(parent);
            case IntensifyGridView.TYPE_ELLIPSIZE:
                return onCreateEllipsizeViewHolder(parent);
        }
        return onCreateCommonViewHolder(parent, viewType);
    }

    public VH onCreateExtraViewHolder(ViewGroup parent) {
        return onCreateCommonViewHolder(parent, IntensifyGridView.TYPE_EXTRA);
    }

    public VH onCreateEllipsizeViewHolder(ViewGroup parent) {
        return onCreateCommonViewHolder(parent, IntensifyGridView.TYPE_ELLIPSIZE);
    }

    public abstract VH onCreateCommonViewHolder(ViewGroup parent, int viewType);

    /**
     * @return 返回被省略的数量
     */
    public int getEllipsizeCount() {
        return getCount() - getItemCount();
    }

    public abstract int getCount();

    public int getMaxCount() {
        return (int) Math.min(mGridView.getMaxLength(),
                Math.min((long) mGridView.getLayoutManager().getSpanCount()
                        * mGridView.getMaxLines(), Integer.MAX_VALUE));
    }

    /**
     * @return 是否需要显示省略条目
     */
    public boolean shouldEllipsize() {
        return getMaxCount() < getCount();
    }

    /**
     * 是否为省略item
     *
     * @param position item position
     * @return 是否为省略item
     */
    public boolean isEllipsizeItem(int position) {
        switch (mGridView.getEllipsize()) {
            case IntensifyGridView.START:
                return position == 0;
            case IntensifyGridView.END:
                return position + 1 == getItemCount();
            default:
                return false;
        }
    }

    /**
     * 但会item类型，大于0
     *
     * @param position item位置
     * @return type
     */
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public final int getItemViewType(int position) {
        if (shouldEllipsize() && isEllipsizeItem(position)) return IntensifyGridView.TYPE_ELLIPSIZE;
        else if (position >= getCount()) return IntensifyGridView.TYPE_EXTRA;
        else return getItemType(position);
    }
}