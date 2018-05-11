package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by 董寒冰 on 2018/4/20.
 */

public class MenuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Map<String,Object>> mdatalist;
    private LayoutInflater layoutInflater;
    private final int ITEM_TYPE=0;
    private final int FOOT_TYPE=1;
    private OnItemClickListener monItemClickListener=null;

    public MenuListAdapter(Context context,List<Map<String,Object>> datalist){
        this.mdatalist=datalist;
        layoutInflater=LayoutInflater.from(context);
    }
    public interface OnItemClickListener{
        void onitemclick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.monItemClickListener=onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview=layoutInflater.from(parent.getContext()).inflate(R.layout.item_view,null);
        View footview=layoutInflater.from(parent.getContext()).inflate(R.layout.foot_view,null);
        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monItemClickListener!=null){
                    monItemClickListener.onitemclick(v,(int)v.getTag());
                }
            }
        });
        if (viewType==ITEM_TYPE){
            return new viewHolder(itemview,ITEM_TYPE);
        }else {
            return new viewHolder(footview,FOOT_TYPE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==mdatalist.size()) {
            return FOOT_TYPE;
        }else {
            return ITEM_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder viewHolder=(viewHolder)holder;
        viewHolder.itemView.setTag(position);
        if (getItemViewType(position) == FOOT_TYPE) {
            viewHolder.tvFootView.setGravity(Gravity.CENTER);
            viewHolder.tvFootView.setText("加载中...");
        } else {
            viewHolder.menu_thumb.setImageResource((int)mdatalist.get(position).get("menu_thumb"));
            viewHolder.menu_title.setText((String)mdatalist.get(position).get("menu_title"));
            viewHolder.menu_info.setText((String)mdatalist.get(position).get("menu_info"));
        }
    }

    @Override
    public int getItemCount() {
        return mdatalist.size()+1;
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView menu_thumb;
        TextView menu_title;
        TextView menu_info;
        TextView tvFootView;

        public viewHolder(View itemView,int viewtype) {
            super(itemView);
            if (viewtype==ITEM_TYPE){
                menu_thumb = (ImageView) itemView.findViewById(R.id.menu_thumb);
                menu_title = (TextView) itemView.findViewById(R.id.menu_title);
                menu_info = (TextView) itemView.findViewById(R.id.menu_info);
            } else if (viewtype == FOOT_TYPE) {
                tvFootView = (TextView) itemView.findViewById(R.id.foot_view);
            }
            }
        }
    }

