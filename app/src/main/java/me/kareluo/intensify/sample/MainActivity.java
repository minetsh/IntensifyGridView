package me.kareluo.intensify.sample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.kareluo.intensify.gridview.IntensifyGridAdapter;
import me.kareluo.intensify.gridview.IntensifyGridView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TestAdapter mAdapter;

    private IntensifyGridView mGridView;
    private int[] mResIds = {R.mipmap.a, R.mipmap.b, R.mipmap.d, R.mipmap.e,
            R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.i, R.mipmap.j, R.mipmap.k, R.mipmap.l,
            R.mipmap.m, R.mipmap.n, R.mipmap.o, R.mipmap.p, R.mipmap.q, R.mipmap.r, R.mipmap.s,
            R.mipmap.u, R.mipmap.v, R.mipmap.x, R.mipmap.y, R.mipmap.z, R.mipmap.aa,
            R.mipmap.ab, R.mipmap.ac, R.mipmap.ad, R.mipmap.ae, R.mipmap.af, R.mipmap.ag,
            R.mipmap.ah, R.mipmap.ai, R.mipmap.aj, R.mipmap.ak, R.mipmap.am, R.mipmap.an};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (IntensifyGridView) findViewById(R.id.igv_grid);
        mAdapter = new TestAdapter(mGridView);

        mGridView.setOnItemClickListener(new IntensifyGridView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                Toast.makeText(MainActivity.this, "点击:" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        mGridView.setOnItemLongClickListener(new IntensifyGridView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView.ViewHolder holder) {
                Toast.makeText(MainActivity.this, "长按:" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private class TestAdapter extends IntensifyGridAdapter<TestViewHolder> {

        public TestAdapter(@NonNull IntensifyGridView intensifyGridView) {
            super(intensifyGridView);
        }

        @Override
        protected void onBindCommonViewHolder(TestViewHolder holder, int position) {
            holder.update(mResIds[position]);
            Log.d(TAG, "POSITION=" + position);
        }

        @Override
        protected void onBindEllipsizeViewHolder(TestViewHolder holder, int position) {

        }

        @Override
        protected void onBindExtraViewHolder(TestViewHolder holder, int position) {

        }

        @Override
        public TestViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            imageView.setBackgroundResource(android.R.color.black);
            return new TestViewHolder(imageView);
        }

        @Override
        public TestViewHolder onCreateEllipsizeViewHolder(ViewGroup parent) {
            ImageView imageView = new ImageView(parent.getContext());
            imageView.setBackgroundResource(android.R.color.black);
            return new TestViewHolder(imageView);
        }

        @Override
        public int getCount() {
            return mResIds.length;
        }
    }

    private class TestViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public TestViewHolder(ImageView itemView) {
            super(itemView);
            mImageView = itemView;
        }

        public void update(int resId) {
            mImageView.setImageResource(resId);
        }
    }
}