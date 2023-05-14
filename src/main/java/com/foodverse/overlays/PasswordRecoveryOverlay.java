package com.foodverse.overlays;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.xml.validation.Validator;

import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.TextField;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class PasswordRecoveryOverlay extends Overlay {

    private final Component component;

    //Recovery questions
    private Map<Integer, String> questions = new HashMap<Integer, String>();

    //This object will help us to produce a random number in order to show a random recovery question
    private Random random = new Random();

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    //Validation for inputs
    private final InputValidation validator = InputValidation.getInstance();


    /*
     * private Row signInHeadingRow = new Row(); private Row emailInputRow = new Row(); private Row
     * passwordInputRow = new Row();
     */

    public PasswordRecoveryOverlay() {
        super(500, 400);

        questions.put(1, "What is the name of your first pet?");
        questions.put(2, "What is your favorite book/movie/TV Show?");

        var randomIndex = random.nextInt(1, 3); //1 inclusive, 3 exclusive
        var randomQuestion = questions.get(randomIndex);

        // Heading
        var panel = new JPanel();
        var passwordRecoveryHeading = new Heading("Password Recovery", HeadingSize.XXL);
        var explanationParagraph = new Paragraph("It seems that you forgot your password. Answer the following question to change it.", ParagraphSize.M);
        var textAnswerInput = new TextField();
        var answer = new InputForm(randomQuestion, "", textAnswerInput);
        var continueButton = new RectButton(
            "Continue",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                    //boolean isValid = checkIfIsEmpty(textAnswerInput);
                }
            
            );

       
        panel.add(passwordRecoveryHeading.getRef());
        panel.add(explanationParagraph.getRef());
        panel.add(answer.getRef());
        panel.add(continueButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    /* 

    private boolean checkValidity(String answer)
    {
        return !answer.isEmpty() && validator.isAnswerValid(answer, answer)
    }

    */

    @Override
    public Component getRef() {
        return component;
    }


}
