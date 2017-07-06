package com.example.hui.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hui.dialog.ImageLoadCouplingUtil;

/**
 * Created by 曾辉 on 2016/9/8.
 * Email : 240336124@qq.com
 * Description :
 */
public class ViewHolder {

    private Context mContext;
    private View mItemView;

    // private Map<Integer,View> mViews;
    private SparseArray mViews;

    public ViewHolder(Context context,int layoutId){
        this.mContext = context;
        mItemView = View.inflate(mContext,layoutId,null);
        mItemView.setTag(this);
        mViews = new SparseArray();
    }

    public static ViewHolder getViewHolder(View convertView, Context context,int layoutId){
        if(convertView != null){
            return (ViewHolder) convertView.getTag();
       }else{
           return new ViewHolder(context,layoutId);
        }
    }

    public View getItemView(){
        return mItemView;
    }

    public <T extends View> T getView(int viewId){
        View view = (View) mViews.get(viewId);
        if(view == null){
            view = mItemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId,CharSequence text){
        TextView itemTv = getView(viewId);
        itemTv.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId,int resourceId){
        ImageView imageIv = getView(viewId);
        imageIv.setImageResource(resourceId);
        return this;
    }

    /**
     * 设置条目点击
     */
    public void setOnItemClick(View.OnClickListener listener){
        mItemView.setOnClickListener(listener);
    }

    public ViewHolder setImageUrl(int viewId,String imageUrl){
        ImageView imageIv = getView(viewId);
        ImageLoadCouplingUtil.getInstance().loadImage(imageUrl,imageIv);
        return this;
    }

    public void setViewGone(int... viewIds) {
        for (int viewId : viewIds) {
            View view = getView(viewId);
            view.setVisibility(View.GONE);
        }
    }
}
