package com.foodverse.models;

import com.google.gson.annotations.SerializedName;

public final class Credentials {

    @SerializedName("password")
    private String password;

    @SerializedName("recovery_answer_1")
    private String firstRecoveryAnswer;

    @SerializedName("recovery_answer_2")
    private String secondRecoveryAnswer;

    public Credentials(String password, String firstRecoveryAnswer, String secondRecoveryAnswer) {
        this.password = password;
        this.firstRecoveryAnswer = firstRecoveryAnswer;
        this.secondRecoveryAnswer = secondRecoveryAnswer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstRecoveryAnswer() {
        return firstRecoveryAnswer;
    }

    public void setFirstRecoveryAnswer(String firstRecoveryAnswer) {
        this.firstRecoveryAnswer = firstRecoveryAnswer;
    }

    public String getSecondRecoveryAnswer() {
        return secondRecoveryAnswer;
    }

    public void setSecondRecoveryAnswer(String secondRecoveryAnswer) {
        this.secondRecoveryAnswer = secondRecoveryAnswer;
    }


}
