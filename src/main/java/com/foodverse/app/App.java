package com.foodverse.app;

import javax.swing.SwingUtilities;

import com.foodverse.pages.ButtonPage;
import com.foodverse.pages.HomePage;
import com.foodverse.pages.OverviewPage;
import com.foodverse.pages.OnboardingPage;
import com.foodverse.pages.SignUpPage;
import com.foodverse.pages.SignInPage;
import com.foodverse.pages.TextPage;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.navigation.Shell;
import com.foodverse.utility.navigation.ShellOptions;
import com.foodverse.utility.system.EnvironmentOptions;
import com.foodverse.utility.system.EnvironmentOptions.Mode;

/**
 * The main entry point for the Foodiverse application.
 */
public final class App {

    private App() {}

    public static void main(String[] args) {

        // Launch the application in the event dispatch thread
        SwingUtilities.invokeLater(() -> {

            // Set the environment mode
            EnvironmentOptions.setMode(Mode.DEBUG);

            // Initialize the application
            ShellOptions options = new ShellOptions.Builder()
                    .width(1440)
                    .height(1024)
                    .defaultFont("Inter")
                    .monospacedFont("IBMPlexMono")
                    .title("Foodiverse")
                    .build();
            Shell.init(options);

            // Add pages to the router
            Router.addPage(new OverviewPage());
            Router.addPage(new OnboardingPage());
            Router.addPage(new SignInPage());
            Router.addPage(new SignUpPage());
            Router.addPage(new HomePage());
            Router.addPage(new TextPage());
            Router.addPage(new ButtonPage());

            // Push OverviewPage page to the router
            Router.pushPage(Pages.OVERVIEW);

            // Render the application
            Shell.render();

        });

    }

}
