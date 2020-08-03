package com.ifenghui.apilibrary.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SerialStory {
    public String id;
    public String name;
    public String shortIntro;
    public String intro;
    public String content;
    public int storyCount;
    public int price;
    public int maxAmount;
    public int isBuy;
    public String banner;
    public String icon;

}
