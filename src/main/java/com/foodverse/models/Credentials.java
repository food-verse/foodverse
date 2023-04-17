package com.foodverse.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.gson.annotations.SerializedName;

public final class Credentials {

    @SerializedName("password")
    private String password;

    @SerializedName("recovery_answers")
    private Map<Integer, String> recoveryAnswers;

    public Credentials(String password, List<String> recoveryAnswers) {
        this.password = password;
        this.recoveryAnswers = IntStream.range(0, recoveryAnswers.size())
                .boxed()
                .collect(Collectors.toMap(i -> i, recoveryAnswers::get));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Integer, String> getRecoveryAnswers() {
        return recoveryAnswers;
    }

    public void setRecoveryAnswers(Map<Integer, String> recoveryAnswers) {
        this.recoveryAnswers = recoveryAnswers;
    }

}
