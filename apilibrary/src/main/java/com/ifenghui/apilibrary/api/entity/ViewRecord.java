package com.ifenghui.apilibrary.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewRecord {
    public int id;
    public int storyId;
    public int isRecentUpdate;
    public String createTime;
    public int type;
    public Story story;
}
