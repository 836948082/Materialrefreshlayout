package com.runtai.materialrefreshlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.runtai.materialrefreshlayoutlibrary.MaterialRefreshLayout;
import com.runtai.materialrefreshlayoutlibrary.MaterialRefreshListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private MaterialRefreshLayout materialRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefresh();
                        Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_LONG).show();
                    }
                }, 3000);
            }

            @Override
            public void onfinish() {
                Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefreshLoadMore();
                        Toast.makeText(MainActivity.this, "加载完成", Toast.LENGTH_LONG).show();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple:
                startActivity(new Intent(this, SimpleActivity.class));
                break;
            case R.id.wave:
                startActivity(new Intent(this, WaveActivity.class));
                break;
            case R.id.rv:
                startActivity(new Intent(this, LoadMoreActivity.class));
                break;
            case R.id.lv:
                startActivity(new Intent(this, AutoRefreshActivity.class));
                break;
            case R.id.sun:
                startActivity(new Intent(this, SunActivity.class));
                break;
            case R.id.overLay:
                startActivity(new Intent(this, OverLayActivity.class));
                break;
            default:
                break;
        }
    }
}
