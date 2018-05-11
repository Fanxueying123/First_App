package com.example.myapplication.Fragments;


import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {
    private Button button1,button2;
    private int count=0;

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_three, container, false);
        button1= (Button) view.findViewById(R.id.notification);
        button2= (Button) view.findViewById(R.id.cancel);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Intent intent=new Intent(getActivity(), Main2Activity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity
                        (getContext(),count,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager nm= (NotificationManager) getActivity()
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                Notification n=new NotificationCompat.Builder(getContext())
                        .setContentTitle("标题")
                        .setContentText("内容")
                        //通知时间
                        .setWhen(System.currentTimeMillis())
                        //小图标
                        .setSmallIcon(R.drawable.xinxi)
                        //大图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notify))
                        .setContentIntent(pendingIntent)
                        //点击通知后通知消失
                        .setAutoCancel(true)
                        .setNumber(count)
                        .build();
                nm.notify(1,n);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
                dialog.setTitle("提示：");
                dialog.setMessage("该订单不能被取消。");
                //点击外部不被销毁
                dialog.setCancelable(false);
                //添加按钮
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }

}
