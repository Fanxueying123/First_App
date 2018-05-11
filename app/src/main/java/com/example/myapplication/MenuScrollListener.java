package com.example.myapplication;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 董寒冰 on 2018/4/20.
 */

public abstract class MenuScrollListener extends RecyclerView.OnScrollListener {
    private int lastvisibaleitem=-1;
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState==RecyclerView.SCROLL_STATE_IDLE && lastvisibaleitem+1==recyclerView.getAdapter().getItemCount())
            loadMore();
    }

    protected abstract void loadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(dy>0){
            LinearLayoutManager linearLayoutManager=(LinearLayoutManager)recyclerView.getLayoutManager();
            lastvisibaleitem=linearLayoutManager.findLastVisibleItemPosition();
        }
    }
}
