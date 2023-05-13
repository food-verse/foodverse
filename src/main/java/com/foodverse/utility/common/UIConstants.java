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

    // Sign out alert
    public static final String SIGN_OUT_CONFIRM_MESSAGE = "Are you sure you want to sign out?";
    public static final String EMPTY_STATE_MESSAGE = "Unfortunately, there is nothing to display.";

}
