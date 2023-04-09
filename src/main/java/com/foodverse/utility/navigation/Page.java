package com.foodverse.utility.navigation;

import com.foodverse.utility.Widget;

/**
 * The parent class of every page. Pages extending this class get their own id and can be later
 * added to the Router.
 */
public abstract class Page extends Widget implements Identifiable {

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

}
