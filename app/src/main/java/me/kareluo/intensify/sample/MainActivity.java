package me.kareluo.intensify.sample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.kareluo.intensify.gridview.IntensifyGridAdapter;
import me.kareluo.intensify.gridview.IntensifyGridView;

public class MainActivity extends AppCompatActivity {

    private TestAdapter mAdapter;

    private IntensifyGridView mGridView;
    private int[] mResIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (IntensifyGridView) findViewById(R.id.igv_grid);
        mAdapter = new TestAdapter(mGridView);

    }

    private class TestAdapter extends IntensifyGridAdapter<TestViewHolder> {

        public TestAdapter(@NonNull IntensifyGridView intensifyGridView) {
            super(intensifyGridView);
        }

        @Override
        protected void onBindCommonViewHolder(TestViewHolder holder, int position) {
            holder.update(mResIds[position]);
        }

        @Override
        public TestViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType) {
            return new TestViewHolder(new ImageView(parent.getContext()));
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