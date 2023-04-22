package com.foodverse.utility.input;

public final class InputValidation {

    private static InputValidation inputValidation = new InputValidation();

    private InputValidation() {}

    public static InputValidation getInstance() {
        return inputValidation;
    }

    public boolean isIdValid(String id) {
        return false;
    }

    public boolean isNameValid(String name) {
        return false;
    }

    public boolean isAddressValid(String address) {
        return false;
    }

    public boolean isPhoneValid(String phone) {
        return false;
    }

    public boolean isEmailValid(String email) {
        return false;
    }

    public boolean isPasswordValid(String password) {
        return false;
    }

    public boolean isAnswerValid(String answer) {
        return false;
    }

}
