package com.guidebook.davis.gbchallenge;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UpcomingGuides {
    @SerializedName("total")
    public String total;

    @SerializedName("data")
    public List<Data> data = new ArrayList<>();


    public static class Data {
        @SerializedName("startDate")
        public String startDate;
        @SerializedName("objType")
        public String objType;
        @SerializedName("loginRequired")
        public String loginRequired;
        @SerializedName("endDate")
        public String endDate;
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
        @SerializedName("venue")
        public Venue venue;

        public class Venue{
            @SerializedName("city")
            public String city;
            @SerializedName("state")
            public String state;
        }

        @SerializedName("icon")
        public String icon;
    }
}
