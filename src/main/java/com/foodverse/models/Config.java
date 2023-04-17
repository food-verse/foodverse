package com.foodverse.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.gson.annotations.SerializedName;

public final class Config {

    @SerializedName("recovery_questions")
    private Map<Integer, String> recoveryQuestions;

    public Config(List<String> recoveryQuestions) {
        this.recoveryQuestions = IntStream.range(0, recoveryQuestions.size())
                .boxed()
                .collect(Collectors.toMap(i -> i, recoveryQuestions::get));
    }

    public Map<Integer, String> getRecoveryQuestions() {
        return recoveryQuestions;
    }

    public void setRecoveryQuestions(Map<Integer, String> recoveryQuestions) {
        this.recoveryQuestions = recoveryQuestions;
    }

}
