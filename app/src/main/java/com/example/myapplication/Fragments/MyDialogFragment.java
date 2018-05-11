package com.example.myapplication.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {
    private CheckBox checkbox;
    private Button button;
    private View view;

    public MyDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_dialog, container, false);
        initView();
        init();
        return view;
    }

    private void init() {
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                if (isChecked==true){
                    button.setBackgroundColor(Color.parseColor("#0072E3"));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkbox.setChecked(false);
                            MyDialogFragment.this.dismiss();
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        checkbox= (CheckBox) view.findViewById(R.id.check_box);
        button= (Button) view.findViewById(R.id.dl_btn);
    }

}
