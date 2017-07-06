package com.example.hui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hui.http.TeamListResult;
import com.example.hui.ioc.R;

import java.util.ArrayList;

public class TeamListAdapter extends BaseAdapter {
	private ArrayList<TeamListResult.Team> teams;
	private Context context;

	public TeamListAdapter(Context context, ArrayList<TeamListResult.Team> teams) {
		this.context = context;
		this.teams = teams;
	}

	@Override
	public int getCount() {
		return teams.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup arg1) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_team_lv, null);
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

		TeamListResult.Team team = teams.get(position);
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
	}
}
