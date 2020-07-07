package com.ifenghui.apilibrary.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifenghui.apilibrary.api.dto.RespModel;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeResult extends RespModel {
    public BannerItemGroup ads;
    public HomeItemGroup recommendGroup;
    public HomeItemGroup lessonIndex;
    public ArrayList<HomeItemGroup> newStoryGroup;
    public ArrayList<HomeItemGroup> classicAndCreateGroup;
    public ArrayList<HomeItemGroup> emotionAndHumourGroup;
    public ArrayList<HomeItemGroup> traditionCultureGroup;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class HomeItemGroup{
        public int id;
        public String name;
        public String content;
        public int targetType;
        public int targetValue;
        public Ad ads;
        public ArrayList<Story> storys;
        public ArrayList<LessonIndex> lessonList;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class BannerItemGroup extends ArrayList<Ad>{

    }
}
