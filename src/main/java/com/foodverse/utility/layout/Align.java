package com.foodverse.utility.layout;

import java.awt.GridBagConstraints;

public enum Align {
    TOP_LEFT(GridBagConstraints.FIRST_LINE_START),
    TOP_CENTER(GridBagConstraints.PAGE_START),
    TOP_RIGHT(GridBagConstraints.FIRST_LINE_END),
    CENTER_LEFT(GridBagConstraints.LINE_START),
    CENTER(GridBagConstraints.CENTER),
    CENTER_RIGHT(GridBagConstraints.LINE_END),
    BOTTOM_LEFT(GridBagConstraints.LAST_LINE_START),
    BOTTOM_CENTER(GridBagConstraints.PAGE_END),
    BOTTOM_RIGHT(GridBagConstraints.LAST_LINE_END);


    private final int anchor;

    Align(int anchor) {
        this.anchor = anchor;
    }

    public int getAnchor() {
        return anchor;
    }

}
