package com.foodverse.utility.core.layout;

import java.awt.GridBagConstraints;

public enum Align {
    CENTER(GridBagConstraints.CENTER),
    PAGE_START(GridBagConstraints.PAGE_START),
    PAGE_END(GridBagConstraints.PAGE_END),
    LINE_START(GridBagConstraints.LINE_START),
    LINE_END(GridBagConstraints.LINE_END),
    FIRST_LINE_START(GridBagConstraints.FIRST_LINE_START),
    FIRST_LINE_END(GridBagConstraints.FIRST_LINE_END),
    LAST_LINE_END(GridBagConstraints.LAST_LINE_END),
    LAST_LINE_START(GridBagConstraints.LAST_LINE_START);

    private final int anchor;

    Align(int anchor) {
        this.anchor = anchor;
    }

    public int getAnchor() {
        return anchor;
    }

}
