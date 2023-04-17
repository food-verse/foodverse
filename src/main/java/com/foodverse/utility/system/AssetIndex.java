package com.foodverse.utility.system;

import java.util.List;
import java.util.Map;
import com.google.gson.annotations.SerializedName;

public final class AssetIndex {

    @SerializedName("fonts")
    private Map<String, List<String>> fonts;

    public AssetIndex(Map<String, List<String>> fonts) {
        this.fonts = fonts;
    }

    public Map<String, List<String>> getFonts() {
        return fonts;
    }

    public void setFonts(Map<String, List<String>> fonts) {
        this.fonts = fonts;
    }

}
