package com.foodverse.utility.common;

public final class UIConstants {

    private UIConstants() {}

    // Onboarding
    public static final String ONBOARDING_PROMO_TITLE =
        "<html>Don't feel like cooking?<br>"
            + "No problem*</html>";
    public static final String ONBOARDING_PROMO_MESSAGE =
        "<html>Browse through our vast selection of<br>"
            + "restaurants and pay the way you<br>"
            + "want.</html>";
    public static final String ONBOARDING_PROMO_NOTE = "*We neither, but don’t tell anyone";

    // Info
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

    // Sign Up
    public static final String REGISTRATION_PROMO_TITLE = "Create a new account";
    public static final String REGISTRATION_PROMO_MESSAGE =
        "<html>Sign up now and start enjoying delicious meals from your<br>" +
            "favorite restaurants.</html>";
    public static final String REGISTRATION_PHONE_FIELD_HINT = "<html>Please enter a Greek phone number";
    public static final String REGISTRATION_PASSWORD_FIELD_HINT =
        "<html>Use 8 or more characters with a mix of letters,<br>" +
            "numbers & symbols</html>";

    // Order
    public static final String ORDER_SUCCESSFUL_TITLE = "Order Submitted";
    public static final String ORDER_SUCCESSFUL_MESSAGE =
        "<html>Order placed successfully! Thank you for your<br>" +
            "order. We are now processing it and will notify<br>" +
            "you once it's ready for delivery.</html>";
    public static final String ORDER_DELIVERY_TIP_PROMO =
        "<html>Did you know that tipping your delivery driver is a great way to show your appreciation<br>"
            +
            "for their hard work and dedication? Even a small amount can go a long way in making<br>"
            +
            "their day.</html>";
    public static final String ORDER_COMMENTS_CAPTION =
        "Please use this field to let us know about any specific dietary requirements.";

    // Sign In
    public static final String INVALID_CREDENTIALS_TITLE = "Invalid Credentials";
    public static final String INVALID_CREDENTIALS_DESCRIPTION =
        "<html>Sorry, we were unable to verify your login<br>"
            + "credentials. Please ensure that you have<br>"
            + "entered your email address and password<br>"
            + "correctly. If you are still having trouble, you<br>"
            + "may reset your password or contact our<br>"
            + "support team for further assistance.</html>";

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

    public static final String INVALID_RECOVERY_ANSWERS_INPUT_TITLE =
        "Invalid Recovery Answers Input";
    public static final String INVALID_RECOVERY_ANSWERS_INPUT_DESCRIPTION =
        "<html>There is an error with your input<br>"
            + "Please make sure that you have filled all the fields</html>";

    public static final String USER_NOT_EXIST_TITLE = "User does not exist";
    public static final String USER_NOT_EXIST_DESCRIPTION =
        "<html>This user does not exist<br>"
            + "Maybe you are not signed up<br>"
            + "Please sign up and then try again<br>"
            + "If the problem continues please<br>"
            + "contact our support for further assistance</html>";

    public static final String WRONG_RECOVERY_ANSWERS_TITLE = "Wrong Answers";
    public static final String WRONG_RECOVERY_ANSWERS_DESCRIPTION =
        "<html>You answered wrong. For security purposes<br>"
            + "you will be redirected back to onboarding page.</html>";

    public static final String WRONG_NEW_PASSWORD_FORMAT_TITLE = "Wrong New Password Format";
    public static final String WRONG_NEW_PASSWORD_FORMAT_DESCRIPTION =
        "<html>Please make sure the 2 fields have the same cost<br>"
            + "Also password must be at least 8 characters long</html>";

    public static final String SUCCESSFUL_PASSWORD_CHANGE_TITLE = "Password changed successfully!";
    public static final String SUCCESSFUL_PASSWORD_CHANGE_DESCRIPTION =
        "<html>You successfully changed your password!<br>"
            + "You can continue to enjoy our services now!!!<br>"
            + "You will be redirected to your home page</html>";

    public static final String SIGN_OUT_CONFIRM_MESSAGE = "Are you sure you want to sign out?";
    public static final String EMPTY_STATE_MESSAGE = "Unfortunately, there is nothing to display.";

    public static final String SIGN_IN_TITLE = "Sign In";
    public static final String SIGN_IN_PROMO_MESSAGE =
        "<html>Please sign in to your account to access your order history,<br>"
            + "favorite restaurants, and exclusive deals.</html>";

    public static final String PASSWORD_RECOVERY_TITLE = "Password Recovery";
    public static final String PASSWORD_RECOVERY_DESCRIPTION =
        "<html>It seems that you forgot your password<br>"
            + "Please answer correct to the following questions<br>"
            + "in order to change it.";

}
