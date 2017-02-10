package com.runtai.materialrefreshlayout;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.runtai.materialrefreshlayoutlibrary.MaterialRefreshLayout;
import com.runtai.materialrefreshlayoutlibrary.MaterialRefreshListener;

public class AutoRefreshActivity extends BaseActivity {

    private MaterialRefreshLayout materialRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        String[] array = new String[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = "啊哈哈哈哈哈，啊哈哈 " + i;
        }

        final ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new android.widget.ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array));
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onfinish() {
                Toast.makeText(AutoRefreshActivity.this, "finish", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AutoRefreshActivity.this, "load more", Toast.LENGTH_LONG).show();
                        materialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 3000);
            }
        });

        materialRefreshLayout.autoRefresh();

        listView.setOnScrollListener(new AbsListView.OnScrollListener(){

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        materialRefreshLayout.autoRefreshLoadMore();
                    }

//                    // 判断是否滚动到顶部
//                    if (view.getFirstVisiblePosition() == 0) {
//                        materialRefreshLayout.autoRefresh();
//                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }
}
