package com.ifenghui.apilibrary.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifenghui.apilibrary.api.dto.RespModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShelfResult extends RespModel {
    public List<ViewRecord> buyStoryRecords;
    public List<SerialStory> serialStoryList;
}
