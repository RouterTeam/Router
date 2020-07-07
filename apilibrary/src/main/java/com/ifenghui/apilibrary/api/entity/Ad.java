package com.ifenghui.apilibrary.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ad {
    public int id;
    public String title;
    public String banner;
    public int width;
    public int height;
    public int targetType;
    public int targetValue;
}
