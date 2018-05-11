package com.example.myapplication.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment {
    private ImageView imageView;
    private DrawerLayout drawerLayout;

    public FourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_four, container, false);
        drawerLayout= (DrawerLayout) view.findViewById(R.id.drawer_layout);
        final NavigationView navigationView= (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_profile);//侧滑默认项
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                drawerLayout.closeDrawers();
                return true;
            }
        });
        imageView= (ImageView) view.findViewById(R.id.tools);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });
        return view;
    }

}
