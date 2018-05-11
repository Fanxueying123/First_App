package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Fragments.MyDialogFragment;

public class LoagActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private EditText editText1,editText2;
    private ProgressDialog progressDialog;
    private MyDialogFragment myDialogFragment=new MyDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loag);
        textView= (TextView) findViewById(R.id.zhuce);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment.show(getSupportFragmentManager(),"myDialogFragment");
            }
        });
        editText1= (EditText) findViewById(R.id.user);
        editText2= (EditText) findViewById(R.id.mima);
        button= (Button) findViewById(R.id.loag_ture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(LoagActivity.this);
                progressDialog.setTitle("正在初始化");
                progressDialog.setMessage("百分之比0%");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//样式
                progressDialog.setCancelable(false);
                progressDialog.show();

                Thread thread=new LoagThread();
                thread.start();
            }
        });
    }
    public class LoagThread extends Thread{
        int pgValue=0;
        @Override
        public void run() {
            super.run();
            while (pgValue<101){
                pgValue=pgValue+10;
                Bundle bundle=new Bundle();
                bundle.putInt("pgValue",pgValue);

                Message sendmessage=handler.obtainMessage();
                sendmessage.setData(bundle);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(sendmessage);
            }
        }
        }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.getData().getInt("pgValue")>100){
                progressDialog.dismiss();
                Intent intent=new Intent(LoagActivity.this,MainActivity.class);
                intent.putExtra("user",editText1.getText().toString());
                startActivity(intent);

            }else {
                //显示进度条和进度信息
                progressDialog.setProgress(msg.getData().getInt("pgValue"));
                progressDialog.setMessage(String.valueOf(msg.getData().getInt("pgValue")));
            }
        }
    };
}
