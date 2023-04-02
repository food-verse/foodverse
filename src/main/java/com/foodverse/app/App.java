package com.foodverse.app;

import com.foodverse.pages.ButtonPage;
import com.foodverse.pages.HomePage;
import com.foodverse.pages.TextPage;
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
                .title("Foodiverse")
                .build();
        Shell.init(options);

        // Add and push HomePage, TextPage, ButtonPage to the router
        Router.addPage(new HomePage());
        Router.addPage(new TextPage());
        Router.addPage(new ButtonPage());
        Router.pushPage(Pages.HOME);

        // Render the application
        Shell.render();

    }

}
