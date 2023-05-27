package com.foodverse.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.annotations.SerializedName;

public record Credentials(
    @SerializedName("password") String password,
    @SerializedName("recovery_answers") Map<Integer, String> recoveryAnswers) {

    public Credentials(String password, List<String> recoveryAnswers) {
        this(password, IntStream.range(0, recoveryAnswers.size())
            .boxed()
            .collect(Collectors.toMap(i -> i, recoveryAnswers::get)));
    }

    public Credentials withPassword(String password) {
        if (this.password.equals(password)) return this;
        return new Credentials(password, recoveryAnswers);
    }

}
