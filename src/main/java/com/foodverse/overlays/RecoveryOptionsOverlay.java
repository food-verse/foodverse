package com.foodverse.overlays;

import com.foodverse.models.Credentials;
import com.foodverse.models.User;
import com.foodverse.props.RecoveryOptionsProps;
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
import com.foodverse.views.ConsentPolicyView;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class RecoveryOptionsOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    // Getting a reference to the input validator...
    private final InputValidation validator = InputValidation.getInstance();

    public RecoveryOptionsOverlay(RecoveryOptionsProps props) {
        super(500, 400);

        var configuration = db.getConfiguration();
        var question1 = "";
        var question2 = "";

        if (configuration.isPresent()) {
            question1 = configuration.get().recoveryQuestions().get(0);
            question2 = configuration.get().recoveryQuestions().get(1);
        }

        var panel = new Column();
        var recoveryHeading = new Heading("Add Recovery Options", HeadingSize.L);
        var explanationParagraph = new Paragraph(
            "Sign up now and start enjoying delicious meals.",
            ParagraphSize.S
        );
        var textFirstRecovAnswInput = new TextField();
        var firstRecovAnswInput = new InputForm(question1, textFirstRecovAnswInput);
        var textSecondRecovAnswInput = new TextField();
        var secondRecovAnswInput = new InputForm(question2, textSecondRecovAnswInput);
        var consentPolicyParagraph = new ConsentPolicyView();
        var signUpButton = new RectButton(
            "Sign Up",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                var firstAnswer = textFirstRecovAnswInput.getText();
                var secondAnswer = textSecondRecovAnswInput.getText();
                boolean isValid = validator.isAnswersValid(firstAnswer, secondAnswer);
                if (isValid) {
                    var answers = List.of(firstAnswer, secondAnswer);
                    var userCredentials = new Credentials(props.password(), answers);
                    var createdUser = new User(
                        props.name(),
                        new ArrayList<>(List.of(props.address())),
                        props.phone(),
                        props.email(),
                        userCredentials,
                        new HashSet<>(),
                        new HashMap<>(),
                        new HashSet<>());
                    db.signUp(createdUser);
                    Router.closeOverlay();
                    Router.pushPage(Pages.HOME);
                } else
                    Router.openOverlay(new Alert(
                        UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_TITLE,
                        UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_DESCRIPTION
                    ));
            });

        panel.addWidget(recoveryHeading, Align.CENTER);
        panel.addWidget(explanationParagraph, new EdgeInsets.Builder()
                .symmetric(24, 0)
                .build(),
            Align.CENTER);
        panel.addWidget(firstRecovAnswInput, Align.CENTER);
        panel.addWidget(secondRecovAnswInput, Align.CENTER);
        panel.addWidget(consentPolicyParagraph, Align.CENTER);
        panel.addWidget(signUpButton, Align.CENTER);

        component = panel.getRef();

    }

    @Override
    public Component getRef() {
        return component;
    }

}
