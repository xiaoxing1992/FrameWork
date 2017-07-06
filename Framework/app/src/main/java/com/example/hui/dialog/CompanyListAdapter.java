package com.example.hui.dialog;

import android.content.Context;
import android.view.View;

import com.example.hui.adapter.CommonAdapter;
import com.example.hui.adapter.ViewHolder;
import com.example.hui.dialog.mode.CompanyListResult;
import com.example.hui.ioc.R;

import java.util.List;

/**
 * Created by 曾辉 on 2016/9/12.
 * Email : 240336124@qq.com
 * Description :
 */
public class CompanyListAdapter extends CommonAdapter<CompanyListResult.DataBean.CompanyBean>{
    public CompanyListAdapter(Context context, List<CompanyListResult.DataBean.CompanyBean> datas) {
        super(context, datas, R.layout.item_company_lv);
    }

    @Override
    protected void convertView(ViewHolder viewHolder, CompanyListResult.DataBean.CompanyBean item) {
        viewHolder.setText(R.id.designer_number_tv,"设计师："+item.getDesignerNum());
        viewHolder.setText(R.id.worker_number_tv,"设计师："+item.getWorkNum());
        // 发现没有查看数量的字段
        setImage(viewHolder,item.getBanners());
    }

    /**
     * 设置处理图片
     */
    private void setImage(ViewHolder viewHolder, List<CompanyListResult.DataBean.CompanyBean.BannersBean> banners) {
        if(banners == null || banners.size()<=0){//  0 张
            /*View imageLl = viewHolder.getView(R.id.master_ll);
            imageLl.setVisibility(View.GONE);*/
            viewHolder.setViewGone(R.id.master_ll);
            return;
        }

        if(banners.size() == 1){  // 1 张
            viewHolder.setViewGone(R.id.small_iv_1,R.id.small_iv_2);
        }

        if(banners.size() >=1){  // 1 张
            viewHolder.setImageUrl(R.id.large_iv,banners.get(0).getBanner());// 给大图设置图片
        }

        if(banners.size() >= 2){ // 2 张
            viewHolder.setImageUrl(R.id.small_iv_1,banners.get(1).getBanner());// 小图一
        }

        if(banners.size() >= 3){ // 3张
            viewHolder.setImageUrl(R.id.small_iv_2,banners.get(2).getBanner());// 小图二
        }
    }
}
