package com.foodverse.pages;

import java.awt.Component;
import java.util.List;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.foodverse.overlays.SignInOverlay;
import com.foodverse.overlays.SignUpOverlay;
import com.foodverse.utility.common.Endpoints;
import com.foodverse.utility.common.ResourceProvider;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.common.URLHandler;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.media.VectorImage;
import com.foodverse.widgets.text.Display;
import com.foodverse.widgets.text.Display.DisplaySize;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class OnboardingPage extends Page {

    private final JPanel panel;
    private final List<String> onboardingImages;
    private final Column textWidgets;
    private boolean didRender = false;
    private static final Random random = ResourceProvider.getRandom();


    public OnboardingPage() {

        onboardingImages = List.of(
            "corn_meat_quesadillas_sauce_on_board.jpg",
            "mexican_fajita_nachos_sombrero_pan.jpg",
            "tasty_fajita_with_veggies_beef.jpg"
        );

        // Creating text widgets...
        var promoTitleText = new Display(UIConstants.ONBOARDING_PROMO_TITLE, DisplaySize.XS);
        var promoMessageText = new Heading(
            UIConstants.ONBOARDING_PROMO_MESSAGE,
            HeadingSize.S,
            Colors.gray600);
        var promoNoteText = new Heading(
            UIConstants.ONBOARDING_PROMO_NOTE,
            HeadingSize.XS,
            Colors.gray600);

        // Creating image widgets...
        var repositoryUrl = Endpoints.REPOSITORY.getLink();
        var brandImage = new VectorImage(IconAsset.BRAND, e -> {
            URLHandler.open(repositoryUrl);
        }, repositoryUrl);
        var hushFaceImage = new Image("hush-emoji.png", new ImageStyle.Builder()
            .width(32)
            .height(32)
            .build());

        // Creating button widgets...
        // TODO: REVERT
        var infoButton = new PillButton(
            "How it works",
            ButtonSize.M,
            ButtonType.TERTIARY,
            e -> {
                var db = Database.getInstance();
                db.signIn("emilysmith123@gmail.com", "XyZ987!");
                Router.pushPage(Pages.HOME);
            });
        var signInButton = new PillButton(
            "Sign In",
            ButtonSize.M,
            ButtonType.SECONDARY,
            e -> Router.openOverlay(new SignInOverlay()));
        var signUpButton = new PillButton(
            "Sign Up",
            ButtonSize.M,
            ButtonType.PRIMARY,
            e -> Router.openOverlay(new SignUpOverlay()));

        // Creating main panel...
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Row with the brand's logo and the action buttons
        // Creating parent panel for the images...
        var headingRow = new JPanel();
        headingRow.setOpaque(false);
        headingRow.setLayout(new BoxLayout(headingRow, BoxLayout.X_AXIS));

        var buttonRow = new Row();
        buttonRow.addWidget(infoButton, new EdgeInsets.Builder()
                .left(584)
                .right(16)
                .build(),
            Align.BOTTOM_RIGHT);
        buttonRow.addWidget(signInButton, new EdgeInsets.Builder()
                .right(16)
                .build(),
            Align.BOTTOM_RIGHT);
        buttonRow.addWidget(signUpButton, Align.BOTTOM_RIGHT);

        // Add images to the parent panel
        headingRow.add(brandImage.getRef());
        headingRow.add(Box.createHorizontalGlue());
        headingRow.add(buttonRow.getRef());

        // Add the padded heading row to the main panel
        var paddedHeading = new Row();
        paddedHeading.addComponent(headingRow, new EdgeInsets.Builder()
                .symmetric(40, 48)
                .bottom(36)
                .build(),
            Align.CENTER);
        panel.add(paddedHeading.getRef());

        var promoText = new Column();
        promoText.addWidget(promoTitleText, new EdgeInsets.Builder()
                .bottom(16)
                .build(),
            Align.TOP_LEFT);
        promoText.addWidget(promoMessageText, Align.BOTTOM_RIGHT);

        var noteWidget = new Row();
        noteWidget.addWidget(promoNoteText, Align.TOP_LEFT);
        noteWidget.addWidget(hushFaceImage, Align.TOP_RIGHT);

        textWidgets = new Column();
        textWidgets.addWidget(promoText, new EdgeInsets.Builder()
            .top(164)
            .bottom(172)
            .build());
        textWidgets.addWidget(noteWidget, new EdgeInsets.Builder().build());

    }

    @Override
    public Component getRef() {

        // Creating the onboarding image widget
        var onboardingImage = new Image(
            onboardingImages.get(random.nextInt(onboardingImages.size())),
            new ImageStyle.Builder()
                .width(400)
                .height(560)
                .build());

        // Remove the last component (content) if the page has already rendered once
        if (!didRender) {
            didRender = true;
        } else {
            int lastComponentIndex = panel.getComponentCount() - 1;
            panel.remove(lastComponentIndex);
        }

        // Add the text widgets and the onboarding image to the content row
        var content = new Row();
        content.addWidget(textWidgets, new EdgeInsets.Builder()
            .right(432)
            .build());
        content.addWidget(onboardingImage);

        // Add the padded content row to the main panel
        var paddedContent = new Column();
        paddedContent.addWidget(content, new EdgeInsets.Builder()
            .all(48)
            .top(0)
            .build());
        panel.add(paddedContent.getRef());

        return panel;

    }

}
