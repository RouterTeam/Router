package com.ifenghui.apilibrary.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifenghui.apilibrary.api.dto.RespModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SplashResult extends RespModel {
    public SplashData indexAds;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class SplashData {
        public int id;
        public String title;
        public String banner;
        public int width;
        public int height;
        public int targetType;
        public int targetValue;
    }
}
