package me.kareluo.intensify.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Locale;

import me.kareluo.intensify.gridview.IntensifyGridAdapter;
import me.kareluo.intensify.gridview.IntensifyGridView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TestAdapter mAdapter;

    private IntensifyGridView mGridView;

    private static final int REQ_SETTING = 1;

    private static final int ITEM_COUNT = 100;

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

    private class TestAdapter extends IntensifyGridAdapter<TestViewHolder> {

        public TestAdapter(@NonNull IntensifyGridView intensifyGridView) {
            super(intensifyGridView);
        }

        @Override
        protected void onBindCommonViewHolder(TestViewHolder holder, int position) {
            holder.update(position);
        }

        @Override
        protected void onBindEllipsizeViewHolder(TestViewHolder holder, int position) {
            holder.update(position, getCount() - getItemCount());
        }

        @Override
        public TestViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType) {
            return new TestViewHolder(new TextItemView(getBaseContext()));
        }

        @Override
        public TestViewHolder onCreateEllipsizeViewHolder(ViewGroup parent) {
            return new TestViewHolder(new TextItemView(getBaseContext()));
        }

        @Override
        public TestViewHolder onCreateExtraViewHolder(ViewGroup parent) {
            return new TestViewHolder(new ExtraItemView(getBaseContext()));
        }

        @Override
        public int getCount() {
            return ITEM_COUNT;
        }
    }

    private class TestViewHolder extends RecyclerView.ViewHolder {

        private TextItemView mTextItemView;

        public TestViewHolder(TextItemView itemView) {
            super(itemView);
            mTextItemView = itemView;
        }

        public TestViewHolder(ExtraItemView itemView) {
            super(itemView);
        }

        public void update(int position) {
            mTextItemView.update(String.valueOf(position));
        }

        public void update(int position, int count) {
            mTextItemView.update(String.format(Locale.CHINA, "%d:%d", position, count));
        }
    }
}