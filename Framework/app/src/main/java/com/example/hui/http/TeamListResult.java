package com.example.hui.http;

import com.example.hui.loading.BaseResult;

import java.util.ArrayList;

public class TeamListResult extends BaseResult {
    public TeamListData data;

    public class TeamListData {
        /** "list": [] **/
        public ArrayList<Team> list;
    }

    public class Team {
        public String is_account_certification;
        public String team_name;
        public String team_worker_nums;
        public String service_fee;
        public String team_industry;
        public String team_skill;
        public String team_location;
        public String service_id;
    }
}
