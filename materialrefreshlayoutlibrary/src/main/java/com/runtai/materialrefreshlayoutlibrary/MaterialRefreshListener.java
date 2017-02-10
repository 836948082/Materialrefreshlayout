package com.runtai.materialrefreshlayoutlibrary;

public abstract class MaterialRefreshListener {

    public void onfinish() {
    }

    public abstract void onRefresh(MaterialRefreshLayout materialRefreshLayout);

    public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
    }

}
