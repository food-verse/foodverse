package com.foodverse.utility.common;

public final class UIConstants {

    private UIConstants() {}

    // Onboarding page
    public static final String ONBOARDING_PROMO_TITLE =
        "<html>Don't feel like cooking?<br>"
            + "No problem*</html>";
    public static final String ONBOARDING_PROMO_MESSAGE =
        "<html>Browse through our vast selection of<br>"
            + "restaurants and pay the way you<br>"
            + "want.</html>";
    public static final String ONBOARDING_PROMO_NOTE = "*We neither, but don’t tell anyone";

    // Info overlay
    public static final String INFO_TITLE =
        "<html>Order delivery online in 3<br>" +
            "simple steps</html>";
    public static final String INFO_STEP_1_TITLE = "Step 1: Enter your address";
    public static final String INFO_STEP_1_DESCRIPTION =
        "<html>Getting started is easy! Simply enter your delivery address<br>" +
            "and feel free to save multiple addresses for added<br>" +
            "convenience.</html>";
    public static final String INFO_STEP_2_TITLE = "Step 2: Select a store";
    public static final String INFO_STEP_2_DESCRIPTION =
        "<html>Browse through our extensive selection of stores across<br>" +
            "Greece and choose the one that suits your cravings best!</html>";
    public static final String INFO_STEP_3_TITLE = "Step 3: Make your order";
    public static final String INFO_STEP_3_DESCRIPTION =
        "<html>Once you've selected your store, it's time to create your<br>" +
            "order. Our easy-to-use interface ensures a hassle-free<br>" +
            "ordering experience - and there are no extra charges to<br>" +
            "worry about!</html>";

    // Invalid credentials alert
    public static final String INVALID_CREDENTIALS_TITLE = "Invalid Credentials";
    public static final String INVALID_CREDENTIALS_DESCRIPTION =
        "<html>Sorry, we were unable to verify your login<br>"
            + "credentials. Please ensure that you have<br>"
            + "entered your email address and password<br>"
            + "correctly. If you are still having trouble, you<br>"
            + "may reset your password or contact our<br>"
            + "support team for further assistance.</html>";

    //Invalid credentials format for sign up
    public static final String INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_TITLE = "Invalid Data Format";
    public static final String INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_DESCRIPTION =
        "<html>There is an error with the given credentials.<br>"
            + "Please make sure that you filled all the<br>"
            + "input fields correctly.<br>"
            + "Phone must be 10 digits long.<br>"
            + "Also email must include @ and .<br>"
            + "Last but not least password must be at least 8 characters long.<br>"
            + "If you continue having problems you can ask<br>"
            + "our support team for further assistance.</html>";

    //Invalid recovery answers
    public static final String INVALID_RECOVERY_ANSWERS_INPUT_TITLE = "Invalid Recovery Answers Input";
    public static final String INVALID_RECOVERY_ANSWERS_INPUT_DESCRIPTION =
        "<html>There is an error with your input<br>"
            + "Please make sure that you have filled all the fields</html>";


    //Message when user does not exist
    public static final String USER_NOT_EXIST_TITLE = "User does not exist";
    public static final String USER_NOT_EXIST_DESCRIPTION =
        "<html>This user does not exist<br>"
            + "Maybe you are not signed up<br>"
            + "Please sign up and then try again<br>"
            + "If the problem continues please<br>"
            + "contact our support for further assistance</html>";


    //Message for wrong recovery answers
    public static final String WRONG_RECOVERY_ANSWERS_TITLE = "Wrong Answers";
    public static final String WRONG_RECOVERY_ANSWERS_DESCRIPTION =
        "<html>You answered wrong. For security purposes<br>"
            + "you will be redirected back to onboarding page.</html>";

    //Message for wrong new password format
    public static final String WRONG_NEW_PASSWORD_FORMAT_TITLE = "Wrong New Password Format";
    public static final String WRONG_NEW_PASSWORD_FORMAT_DESCRIPTION =
        "<html>Please make sure the 2 fields have the same value<br>"
            + "Also password must be at least 8 characters long</html>";


    //Message for correct password change
    public static final String SUCCESSFUL_PASSWORD_CHANGE_TITLE = "Password changed successfully!";
    public static final String SUCCESSFUL_PASSWORD_CHANGE_DESCRIPTION =
        "<html>You successfully changed your password!<br>"
            + "You can continue to enjoy our services now!!!<br>"
            + "You will be redirected to your home page</html>";

    // Sign out alert
    public static final String SIGN_OUT_CONFIRM_MESSAGE = "Are you sure you want to sign out?";
    public static final String EMPTY_STATE_MESSAGE = "Unfortunately, there is nothing to display.";

}
