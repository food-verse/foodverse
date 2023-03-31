package com.foodverse.pages;

import java.awt.Component;
import com.foodverse.utility.Page;
import com.foodverse.widgets.text.Display;
import com.foodverse.widgets.text.Display.DisplaySize;

public final class HomePage extends Page {

    @Override
    public Component getRef() {
        return new Display("HomePage", DisplaySize.L).getRef();
    }

}
