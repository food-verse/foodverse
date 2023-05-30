package com.foodverse.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.annotations.SerializedName;

public record Config(
    @SerializedName("recovery_questions") Map<Integer, String> recoveryQuestions
) {

    public Config(List<String> recoveryQuestions) {
        this(IntStream.range(0, recoveryQuestions.size())
            .boxed()
            .collect(Collectors.toMap(i -> i, recoveryQuestions::get)));
    }

}
