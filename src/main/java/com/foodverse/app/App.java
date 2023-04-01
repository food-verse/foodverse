package com.foodverse.app;

import com.foodverse.pages.ButtonPage;
import com.foodverse.pages.HomePage;
import com.foodverse.utility.Pages;
import com.foodverse.utility.Router;
import com.foodverse.utility.Shell;
import com.foodverse.utility.ShellOptions;

public final class App {

    private App() {}

    public static void main(String[] args) {
        // Initialize the application
        ShellOptions options = new ShellOptions.Builder()
                .width(1440)
                .height(1024)
                .build();
        Shell.init(options);

        // Add and push HomePage to the router
        Router.addPage(new HomePage());
        Router.addPage(new ButtonPage());
        Router.pushPage(Pages.HOME);

    }

}
