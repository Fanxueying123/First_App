package com.example.myapplication.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private String[] titles={"文史","理工","外语"};
    private View view;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_two, container, false);

        init();
        return view;
    }

    private void init() {
        tabLayout= (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager= (ViewPager) view.findViewById(R.id.view_pager);
        fragments=new ArrayList<>();
        fragments.add(new AAAFragment());
        fragments.add(new BBBFragment());
        fragments.add(new CCCFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("文史");
        tabLayout.getTabAt(1).setText("理工");
        tabLayout.getTabAt(2).setText("外语");
    }

}
