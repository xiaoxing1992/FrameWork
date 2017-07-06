package com.example.hui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曾辉 on 2016/9/8.
 * Email : 240336124@qq.com
 * Description :
 */
public abstract class CommonAdapter<T> extends BaseAdapter{
    protected Context mContext;
    protected List<T> mDatas;
    private int mLayoutId;

    public CommonAdapter(Context context,List<T> datas,int layoutId){
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // 团队   工人  好友
        // 自己定通用ViewHolder
        ViewHolder viewHolder = ViewHolder.getViewHolder(convertView,mContext, mLayoutId);
       // return convertView(position,convertView, viewGroup);
        convertView(viewHolder,mDatas.get(position));
        return viewHolder.getItemView();
    }

    protected abstract void convertView(ViewHolder viewHolder,T item);
}
