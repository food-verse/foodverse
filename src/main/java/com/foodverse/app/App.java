package com.foodverse.app;

import com.foodverse.pages.ButtonPage;
import com.foodverse.pages.HomePage;
import com.foodverse.pages.OverviewPage;
import com.foodverse.pages.OnboardingPage;
import com.foodverse.pages.SignUpPage;
import com.foodverse.pages.SignInPage;
import com.foodverse.pages.TextPage;
import com.foodverse.pages.SettingsPage;
import com.foodverse.pages.ProfilePage;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.navigation.Shell;
import com.foodverse.utility.navigation.ShellOptions;

public final class App {

    private App() {}

    public static void main(String[] args) {

        // Initialize the application
        ShellOptions options = new ShellOptions.Builder()
                .width(1440)
                .height(1024)
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
        Router.addPage(new SettingsPage());
        Router.addPage(new ProfilePage());

        // Push OverviewPage page to the router
        Router.pushPage(Pages.OVERVIEW);

        // Render the application
        Shell.render();

    }

}
