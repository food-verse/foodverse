package com.foodverse.utility.system;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public record AssetIndex(
    @SerializedName("fonts") Map<String, List<String>> fonts,
    @SerializedName("icons") List<String> icons,
    @SerializedName("images") List<String> images) {
}
