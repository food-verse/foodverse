package com.foodverse.utility.input;

import javax.lang.model.util.ElementScanner14;

import com.foodverse.models.User;
import com.foodverse.utility.system.Database;

public final class InputValidation {

    //To search if user exists
    private final Database db = Database.getInstance();

    private static final InputValidation inputValidation = new InputValidation();
    private static final int phoneLength = 10;
    private static final int passwordLength = 8;

    private InputValidation() {}

    public static InputValidation getInstance() {
        return inputValidation;
    }

    public boolean isIdValid(String id) {
        return false;
    }

    public boolean isNameValid(String name) {
    	return !name.isEmpty();
    }

    public boolean isAddressValid(String address) {
        return !address.isEmpty();
    }

    public boolean isPhoneValid(String phone) {
        return !phone.isEmpty() && phone.length() == phoneLength && phone.matches("\\d+") ; //phone must contain more than one different digits
    }

    public boolean isEmailValid(String email) {
        return (email.contains("@") && email.contains("."));
    }

    public boolean isPasswordValid(String password) {
        return password.length() >= passwordLength;
    }

    public boolean isAnswersValid(String answer1, String answer2)
    {
        return !answer1.isEmpty() && !answer2.isEmpty();
    }

    /*/
    public boolean isAnswerValid(int index, String answer, String email)
    {
        User user = db.userExists(email);

        if(user == null)
        {
            var answerToCheck = user.credentials().recoveryAnswers().get(index);
            if(answer.equals(answerToCheck))
                return true;
        }
        else
            return false;

    }
    */


}
