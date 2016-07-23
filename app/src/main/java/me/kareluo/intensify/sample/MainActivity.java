package me.kareluo.intensify.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.kareluo.intensify.gridview.IntensifyGridAdapter;
import me.kareluo.intensify.gridview.IntensifyGridView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TestAdapter mAdapter;

    private IntensifyGridView mGridView;

    private static final int REQ_SETTING = 1;

    private static final int[] mResIds = {
            R.mipmap.a, R.mipmap.b, R.mipmap.d, R.mipmap.e, R.mipmap.f,
            R.mipmap.g, R.mipmap.h, R.mipmap.i, R.mipmap.j, R.mipmap.k,
            R.mipmap.l, R.mipmap.m, R.mipmap.n, R.mipmap.o, R.mipmap.p,
            R.mipmap.q, R.mipmap.r, R.mipmap.t, R.mipmap.w
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (IntensifyGridView) findViewById(R.id.igv_grid);
        mAdapter = new TestAdapter(mGridView);

        mGridView.setHasFixedSize(true);

        mGridView.setOnItemClickListener(new IntensifyGridView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                Toast.makeText(MainActivity.this, "点击:" + holder.getAdapterPosition(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mGridView.setOnItemLongClickListener(new IntensifyGridView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView.ViewHolder holder) {
                Toast.makeText(MainActivity.this, "长按:" + holder.getAdapterPosition(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                startActivityForResult(new Intent(this, SettingActivity.class), REQ_SETTING);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_SETTING:
                updateSetting();
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateSetting() {
        mGridView.setAutoFix(SettingConfig.AUTO_FIT);
        mGridView.setSpanCount(SettingConfig.SPAN_COUNT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ellipsize:
                startActivity(new Intent(this, EllipsizeDemoActivity.class));
                break;
            case R.id.btn_extra:
                startActivity(new Intent(this, ExtraDemoActivity.class));
                break;
        }
    }

    private class TestAdapter extends IntensifyGridAdapter<DemoViewHolder> {

        public TestAdapter(@NonNull IntensifyGridView intensifyGridView) {
            super(intensifyGridView);
        }

        @Override
        protected void onBindCommonViewHolder(DemoViewHolder holder, int position) {
            holder.update(mResIds[position]);
        }

        @Override
        protected void onBindEllipsizeViewHolder(DemoViewHolder holder, int position) {
            holder.update(position, getCount() - getItemCount());
        }

        @Override
        public DemoViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType) {
            return new DemoViewHolder(new ImageItemView(getBaseContext()));
        }

        @Override
        public DemoViewHolder onCreateEllipsizeViewHolder(ViewGroup parent) {
            return new DemoViewHolder(new ImageItemView(getBaseContext()));
        }

        @Override
        public DemoViewHolder onCreateExtraViewHolder(ViewGroup parent) {
            return new DemoViewHolder(new ImageItemView(getBaseContext()));
        }

        @Override
        public int getCount() {
            return mResIds.length;
        }
    }

    private static class DemoViewHolder extends RecyclerView.ViewHolder {

        private ImageItemView mImageItemView;

        public DemoViewHolder(ImageItemView itemView) {
            super(itemView);
            mImageItemView = itemView;
        }

        public void update(@DrawableRes int resId) {
            mImageItemView.update(resId);
        }

        public void update(@DrawableRes int resId, int count) {
            mImageItemView.update(resId, count);
        }
    }
}