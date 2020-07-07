package com.ifenghui.apilibrary.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonIndex {
    public int id;
    public String name;
    public String imgPath;
}
