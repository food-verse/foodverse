package com.foodverse.models;

import java.io.Serializable;

public final class UserCredentials implements Serializable {

    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String firstRecoveryAnswer;
    private String secondRecoveryAnswer;

    public UserCredentials(String username, String password, String email, String address,
            String phone, String firstRecoveryAnswer, String secondRecoveryAnswer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.firstRecoveryAnswer = firstRecoveryAnswer;
        this.secondRecoveryAnswer = secondRecoveryAnswer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstRecoveryAnswer() {
        return firstRecoveryAnswer;
    }

    public void setFirstRecoveryAnswer(String recoveryAnswer1) {
        this.firstRecoveryAnswer = recoveryAnswer1;
    }

    public String getSecondRecoveryAnswer() {
        return secondRecoveryAnswer;
    }

    public void setSecondRecoveryAnswer(String recoveryAnswer2) {
        this.secondRecoveryAnswer = recoveryAnswer2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s:\n", getClass().getSimpleName()));
        builder.append(String.format("Username: %s\n", username));
        builder.append(String.format("Password: %s\n", password));
        builder.append(String.format("Email: %s\n", email));
        builder.append(String.format("Address: %s\n", address));
        builder.append(String.format("Phone: %s\n", phone));
        builder.append(String.format("Recovery Answer 1: %s\n", firstRecoveryAnswer));
        builder.append(String.format("Recovery Answer 2: %s\n", secondRecoveryAnswer));
        builder.append("----------\n");
        return builder.toString();
    }

}
