package me.kareluo.intensify.sample;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.kareluo.intensify.gridview.IntensifyGridAdapter;
import me.kareluo.intensify.gridview.IntensifyGridView;

/**
 * Created by felix on 16/7/21.
 */
public class ExtraDemoActivity extends AppCompatActivity implements IntensifyGridView.OnItemClickListener {

    private List<Integer> mResIdList = new ArrayList<>();

    private DemoAdapter mAdapter;

    private static final int[] mResIds = {R.mipmap.a, R.mipmap.b,
            R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h,
            R.mipmap.i, R.mipmap.j, R.mipmap.k, R.mipmap.l};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_extra);
        mResIdList.add(mResIds[0]);
        IntensifyGridView intensifyGridView = (IntensifyGridView) findViewById(R.id.igv_grid);
        mAdapter = new DemoAdapter(intensifyGridView);
        intensifyGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder holder) {
        switch (holder.getItemViewType()) {
            case IntensifyGridView.TYPE_EXTRA:
                mResIdList.add(mResIds[mResIdList.size()]);
                mAdapter.notifyDataSetChanged();
                break;
            default:
                Toast.makeText(this, "" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class DemoAdapter extends IntensifyGridAdapter<DemoViewHolder> {

        public DemoAdapter(@NonNull IntensifyGridView intensifyGridView) {
            super(intensifyGridView);
        }

        @Override
        protected void onBindCommonViewHolder(DemoViewHolder holder, int position) {
            holder.update(mResIdList.get(position));
        }

        @Override
        protected void onBindEllipsizeViewHolder(DemoViewHolder holder, int position) {
            holder.update(mResIdList.get(position), getEllipsizeCount());
        }

        @Override
        public DemoViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType) {
            return new DemoViewHolder(new ImageItemView(getBaseContext()));
        }

        @Override
        public DemoViewHolder onCreateExtraViewHolder(ViewGroup parent) {
            return new DemoViewHolder(new ExtraItemView(getBaseContext()));
        }

        @Override
        public int getCount() {
            return mResIdList.size();
        }
    }

    private static class DemoViewHolder extends RecyclerView.ViewHolder {

        private ImageItemView mImageItemView;

        public DemoViewHolder(ImageItemView itemView) {
            super(itemView);
            mImageItemView = itemView;
        }

        public DemoViewHolder(ExtraItemView itemView) {
            super(itemView);
        }

        public void update(@DrawableRes int resId) {
            mImageItemView.update(resId);
        }

        public void update(@DrawableRes int resId, int count) {
            mImageItemView.update(resId, count);
        }
    }
}
