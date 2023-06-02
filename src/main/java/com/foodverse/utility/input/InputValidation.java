package com.foodverse.utility.input;

import org.apache.commons.validator.routines.EmailValidator;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputValidation {

    private static final InputValidation inputValidation = new InputValidation();
    private static final EmailValidator validator = EmailValidator.getInstance();
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    private InputValidation() {}

    public static InputValidation getInstance() {
        return inputValidation;
    }

    public boolean isNameValid(String name) {
        return !name.isEmpty();
    }

    public boolean isAddressValid(String address) {
        return !address.isEmpty();
    }

    public boolean isPhoneValid(String phone) {
        PhoneNumber parsedPhoneNumber;
        try {
            parsedPhoneNumber = phoneNumberUtil.parse(phone, "GR");
        } catch (NumberParseException e) {
            return false;
        }
        return phoneNumberUtil.isValidNumber(parsedPhoneNumber)
            && phoneNumberUtil.getNumberType(parsedPhoneNumber) == PhoneNumberUtil.PhoneNumberType.MOBILE
            && phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber).equals("GR");
    }

    public boolean isEmailValid(String email) {
        return validator.isValid(email);
    }

    public boolean isPasswordValid(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-_=+§±{}|]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isAnswersValid(String answer1, String answer2) {
        return !answer1.isEmpty() && !answer2.isEmpty();
    }


}
