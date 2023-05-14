package com.foodverse.overlays;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.foodverse.models.Credentials;
import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.TextField;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class RecoveryOptionsOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    // Getting a reference to the input validator...
    private final InputValidation validator = InputValidation.getInstance();

    public RecoveryOptionsOverlay(String username, String address, String phone, String email,
                                  String password) {
        super(600, 840);

        var panel = new JPanel();
        var recoveryHeading = new Heading("Add Recovery Options", HeadingSize.XXL);
        var explanationParagraph = new Paragraph("Sign up now and start enjoying delicious meals.", ParagraphSize.S);
        var textFirstRecovAnswInput = new TextField();
        var firstRecovAnswInput = new InputForm("What is the name of your first pet?", "", textFirstRecovAnswInput);
        var textSecondRecovAnswInput = new TextField();
        var secondRecovAnswInput = new InputForm("What is your favorite book/movie/TV Show?", "", textSecondRecovAnswInput);
        var privacyPolicyParagraph = new Paragraph("By signing up you agree to our Terms of Use and Privacy Policy.", ParagraphSize.S);
        var signUpButton = new RectButton(
            "Sign Up",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                boolean isValid = checkQuestions(textFirstRecovAnswInput, textSecondRecovAnswInput);

                if (isValid) {
                    var answers = List.of(textFirstRecovAnswInput.getText(),
                        textSecondRecovAnswInput.getText());
                    var userCredentials = new Credentials(password, answers);
                    var createdUser = new User(username, new ArrayList<>(List.of(address)),
                    phone, email,
                    userCredentials, new ArrayList<>(), new ArrayList<>());
                    db.signUp(createdUser);
                    Router.closeOverlay();
                    Router.pushPage(Pages.HOME);
                    
                } else
                    Router.openOverlay(
                        new Alert(UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_TITLE,
                            UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_DESCRIPTION));

            });


        panel.add(recoveryHeading.getRef());
        panel.add(explanationParagraph.getRef());
        panel.add(firstRecovAnswInput.getRef());
        panel.add(secondRecovAnswInput.getRef());
        panel.add(privacyPolicyParagraph.getRef());
        panel.add(signUpButton.getRef());
        panel.setOpaque(false);
        component = panel;

    }

    private boolean checkQuestions(TextField answer1, TextField answer2) {
        boolean isValid;

        isValid = validator.isAnswersValid(answer1.getText(), answer2.getText());

        return isValid;
    }

    @Override
    public Component getRef() {
        return component;
    }


}
