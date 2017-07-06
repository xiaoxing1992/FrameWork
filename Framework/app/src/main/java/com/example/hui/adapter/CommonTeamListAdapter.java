package com.example.hui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hui.http.TeamListResult;
import com.example.hui.ioc.R;

import java.util.ArrayList;

public class CommonTeamListAdapter extends CommonAdapter<TeamListResult.Team> {

    public CommonTeamListAdapter(Context context, ArrayList<TeamListResult.Team> datas) {
        super(context, datas, R.layout.item_team_lv);
    }

    @Override
    protected void convertView(ViewHolder viewHolder, final TeamListResult.Team team) {
        // 文字  本地图片 和 网络
        viewHolder.setText(R.id.tv_title, team.team_skill)
                .setText(R.id.tv_money, team.service_fee).setText(R.id.tv_industry, team.team_industry)
                .setText(R.id.tv_name, team.team_name).setText(R.id.tv_location, team.team_location)
                .setText(R.id.tv_num, team.team_worker_nums);

        if (team.is_account_certification.equals("1")) {
            viewHolder.setImageResource(R.id.iv_is_account_certification, R.mipmap.list_certificationed_icon);
        } else {
            viewHolder.setImageResource(R.id.iv_is_account_certification, R.mipmap.list_uncertification_icon);
        }

        viewHolder.setOnItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,team.service_id,Toast.LENGTH_LONG).show();
            }
        });
    }

	/*@Override
	protected View convertView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_team_lv, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
			viewHolder.tv_industry = (TextView) convertView.findViewById(R.id.tv_industry);
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.iv_is_account_certification = (ImageView) convertView
					.findViewById(R.id.iv_is_account_certification);
			viewHolder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
			viewHolder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		TeamListResult.Team team = mDatas.get(position);
		viewHolder.tv_title.setText(team.team_skill);
		viewHolder.tv_money.setText(team.service_fee);
		viewHolder.tv_industry.setText(team.team_industry);
		viewHolder.tv_name.setText(team.team_name);
		viewHolder.tv_location.setText(team.team_location);
		viewHolder.tv_num.setText(team.team_worker_nums);

		if (team.is_account_certification.equals("1")) {
			viewHolder.iv_is_account_certification.setBackgroundResource(R.mipmap.list_certificationed_icon);
		} else {
			viewHolder.iv_is_account_certification.setBackgroundResource(R.mipmap.list_uncertification_icon);
		}
		return convertView;
	}

	static class ViewHolder {
		public TextView tv_title;
		public TextView tv_money;
		public TextView tv_industry;
		public TextView tv_name;
		public ImageView iv_is_account_certification;
		public TextView tv_num;
		public TextView tv_location;
	}*/
}
