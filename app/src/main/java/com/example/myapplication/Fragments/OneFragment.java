package com.example.myapplication.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.CallBackValue;
import com.example.myapplication.MainActivity;
import com.example.myapplication.MenuItemDecoration;
import com.example.myapplication.MenuListAdapter;
import com.example.myapplication.MenuScrollListener;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<Map<String,Object>> datalist;
    private Handler handler=new Handler();
    private MenuListAdapter adapter;
    private CallBackValue callBackValue;
    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue=(CallBackValue)context;//绑定
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_one, container, false);
        initView();
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new MenuListAdapter(getContext(),datalist);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }
        },2000);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new MenuScrollListener() {
            @Override
            protected void loadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("menu_thumb", R.mipmap.ic_launcher);
                        map.put("menu_title", "ssss");
                        map.put("menu_info", "ssss");
                        datalist.add(map);
                        adapter.notifyDataSetChanged();
                        recyclerView.smoothScrollToPosition(datalist.size() - 1);
                    }
                },1000);
            }
        });
        //item点击事件
        adapter.setOnItemClickListener(new MenuListAdapter.OnItemClickListener() {
            @Override
            public void onitemclick(View view, int position) {
                Toast.makeText(getContext(),"信息"+position,Toast.LENGTH_SHORT).show();
                ((CallBackValue)getActivity()).sendMessageValue("two");//往main传参
                MainActivity mainActivity= (MainActivity) getActivity();
                FragmentManager fm=mainActivity.getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.main_container,new ThreeFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        recyclerView.addItemDecoration(new MenuItemDecoration(getContext()));
        return view;
    }

    private void initData() {
        datalist=new ArrayList<>();
        int[] image = {R.drawable.gongbaojiding, R.drawable.shuizhuroupian,
                R.drawable.xihucuyu, R.drawable.yuxiangrousi,
                R.drawable.suanlajidantang};
        String[] title = {"宫保鸡丁", "水煮肉片", "西湖醋鱼", "鱼香肉丝", "酸辣鸡蛋汤"};
        String[] content = {
                "宫保鸡丁，是一道闻名中外的特色传统名菜。鲁菜、川菜、贵州菜中都有收录，原料、做法有差别。该菜式的起源与鲁菜中的酱爆鸡丁，和贵州菜的胡辣子鸡丁有关，后被清朝山东巡抚、四川总督丁宝桢改良发扬，形成了一道新菜式——宫保鸡丁，并流传至今，此道菜也被归纳为北京宫廷菜。之后宫保鸡丁也流传到国外。",
                "水煮肉片是一道地方新创名菜，起源于自贡，发扬于西南，属于川菜中著名的家常菜。其起源于上世纪30年代， 自贡名厨范吉安(1887 -1982年)" +
                        "，创新出风味突出的水煮肉片。因肉片未经划油，以水煮熟故名水煮肉片。",
                "西湖醋鱼别名为叔嫂传珍，宋嫂鱼，是浙江杭州饭店的一道传统地方风味名菜。",
                "鱼香肉丝（英文名：Stir-fried Pork Strips in Fish " +
                        "Sauce）是一道特色传统名菜，以鱼香调味而得名，属川菜。相传灵感来自老菜泡椒肉丝，民国年间由四川籍厨师创制而成。",
                "酸辣鸡蛋汤是一款简单的汤类美食，主要原料有猪肉50克、胡萝卜、竹笋各50克等。逢年过节，或寒冬腊月，煮上一锅热腾腾的酸辣鸡蛋汤，暖身提神，回味无穷"};
        for (int i=0;i<image.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("menu_thumb", image[i]);
            map.put("menu_title", title[i]);
            map.put("menu_info", content[i]);
            datalist.add(map);
        }
    }

    private void initView() {
        progressBar= (ProgressBar) view.findViewById(R.id.progress_bar);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.removeAllViews();
                datalist.clear();
                for (int i = 0; i < 10; i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("menu_thumb", R.mipmap.ic_launcher);
                    map.put("menu_title", "aa");
                    map.put("menu_info", "bb");
                    datalist.add(map);
                }
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        },1000);
    }
}
