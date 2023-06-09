package com.foodverse.overlays;

import java.awt.Component;

import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.TextField;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class PasswordRecoveryOverlay extends Overlay {

    private final Component component;


    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    // Validation for inputs
    private final InputValidation validator = InputValidation.getInstance();


    /*
     * private Row signInHeadingRow = new Row(); private Row emailInputRow = new Row(); private Row
     * passwordInputRow = new Row();
     */

    public PasswordRecoveryOverlay(User user) {
        super(500, 450);

        var configuration = db.getConfiguration();
        var question1 = "";
        var question2 = "";

        if (configuration.isPresent()) {
            question1 = configuration.get().recoveryQuestions().get(0);
            question2 = configuration.get().recoveryQuestions().get(1);
        }

        // Heading
        var panel = new Column();
        var passwordRecoveryHeading =
            new Heading(UIConstants.PASSWORD_RECOVERY_TITLE, HeadingSize.L);
        var explanationParagraph =
            new Paragraph(UIConstants.PASSWORD_RECOVERY_DESCRIPTION, ParagraphSize.M);
        var textFirstAnswerInput = new TextField();
        var answer1 = new InputForm(question1, textFirstAnswerInput);
        var textSecondAnswerInput = new TextField();
        var answer2 = new InputForm(question2, textSecondAnswerInput);
        var continueButton = new RectButton(
            "Continue",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                var givenAnswer1 = textFirstAnswerInput.getText();
                var givenAnswer2 = textSecondAnswerInput.getText();
                boolean isValidAnswer =
                    checkValidityOfRecoveryAnswers(givenAnswer1, givenAnswer2);

                if (isValidAnswer) {
                    // compare with the value that user gave on sign up process
                    var areCorrect = checkIfAnswersAreCorrect(user, givenAnswer1, givenAnswer2);
                    if (areCorrect) {
                        // open the window for password change
                        Router.closeOverlay();
                        Router.openOverlay(new ChangePasswordOverlay(user));
                    } else {
                        // show a message for wrong answer and go back to onboarding
                        Router.closeOverlay();
                        Router.openOverlay(new Alert(UIConstants.WRONG_RECOVERY_ANSWERS_TITLE,
                            UIConstants.WRONG_RECOVERY_ANSWERS_DESCRIPTION));
                        Router.pushPage(Pages.ONBOARDING);
                    }

                } else {
                    Router.openOverlay(
                        new Alert(UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_TITLE,
                            UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_DESCRIPTION));
                }
            }

        );


        panel.addWidget(passwordRecoveryHeading, Align.CENTER);
        panel.addWidget(explanationParagraph, new EdgeInsets.Builder()
                .symmetric(24, 0)
                .build(),
            Align.CENTER);
        panel.addWidget(answer1, Align.CENTER);
        panel.addWidget(answer2, Align.CENTER);
        panel.addWidget(continueButton, Align.CENTER);

        component = panel.getRef();
    }


    private boolean checkValidityOfRecoveryAnswers(String firstAnswer, String secondAnswer) {
        return validator.isAnswersValid(firstAnswer, secondAnswer);
    }


    public boolean checkIfAnswersAreCorrect(User user, String answer1, String answer2) {
        var correctAnswers = user.credentials().recoveryAnswers();
        return correctAnswers.get(0).equals(answer1) && correctAnswers.get(1).equals(answer2);
    }


    @Override
    public Component getRef() {
        return component;
    }


}
