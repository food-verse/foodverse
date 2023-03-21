package com.foodverse.samples;

import java.awt.Component;
import com.foodverse.utility.Button;
import com.foodverse.utility.State;
import com.foodverse.utility.Widget;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class Counter extends Widget {

    private final State<Integer> count = new State<>(0);

    @Override
    public Component getRef() {
        var text = new Paragraph(count.getValue().toString(), ParagraphSize.L);
        return new Button(text, e -> {
            count.setValue(count.getValue() + 1);
            text.setText(count.getValue().toString());
        }).getRef();
    }

}
