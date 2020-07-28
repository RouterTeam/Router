package com.ifenghui.imageloaderlibrary;

import com.bumptech.glide.load.resource.bitmap.CenterInside;

public enum ScaleTypeMenu {
    Default(1),
    CenterCrop(2),
    CenterInside(3),
    FitCenter(4),
    CircleCrop(5);
    public int scaleFlag;

    ScaleTypeMenu(int scaleFlag) {
        this.scaleFlag = scaleFlag;
    }
}
