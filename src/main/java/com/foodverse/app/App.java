package com.foodverse.app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.foodverse.samples.Counter;
import com.foodverse.samples.CounterSwing;
import com.foodverse.utility.AssetManager;
import com.foodverse.utility.Window;

public final class App {

    private App() {}

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Loading font families.
        AssetManager.loadFont("Inter");
        AssetManager.loadFont("IBM Plex Mono");

        // Declarative UI
        Counter counter = new Counter();
        Window window = new Window();
        window.inflate(counter);
        window.setPreferences("Food.verse");
        window.open();

        // Imperative UI
        CounterSwing counterSwing = new CounterSwing();
        JFrame frame = new JFrame();
        frame.add(counterSwing);
        frame.pack();
        frame.setTitle("Food.verse");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
