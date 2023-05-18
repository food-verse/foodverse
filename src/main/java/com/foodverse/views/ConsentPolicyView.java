package com.foodverse.views;

import java.awt.Component;

import com.foodverse.utility.common.Endpoints;
import com.foodverse.utility.common.URLHandler;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.ui.Colors;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class ConsentPolicyView extends Widget {

    private final Component component;

    public ConsentPolicyView() {
        // Creating the shared text properties...
        var textSize = ParagraphSize.S;
        var textColor = Colors.gray600;

        // Creating text widgets...
        var firstText = new Paragraph("By signing up you agree to our ", textSize, textColor);
        var termsLink = new Paragraph("<html><u>Terms of Use</u></html>", textSize);
        var secondText = new Paragraph(" and ", textSize, textColor);
        var policiesFirstLink = new Paragraph("<html><u>Privacy</u></html>", textSize);
        var policiesSecondLink = new Paragraph("<html><u>Policy</u></html>", textSize);

        // Getting the endpoints...
        var readme = Endpoints.README.getLink();
        var license = Endpoints.LICENSE.getLink();

        // Adding interactivity to the links...
        termsLink.onPressed(e -> URLHandler.open(readme), readme);
        policiesFirstLink.onPressed(e -> URLHandler.open(license), license);
        policiesSecondLink.onPressed(e -> URLHandler.open(license), license);

        // Creating the row of text...
        var textRow = new Row();
        textRow.addWidget(firstText, Align.FIRST_LINE_START);
        textRow.addWidget(termsLink, Align.FIRST_LINE_START);
        textRow.addWidget(secondText, Align.FIRST_LINE_START);
        textRow.addWidget(policiesFirstLink, Align.FIRST_LINE_START);

        // Creating the column of text...
        var textColumn = new Column();
        textColumn.addWidget(textRow, Align.FIRST_LINE_START);
        textColumn.addWidget(policiesSecondLink, Align.FIRST_LINE_START);

        component = textColumn.getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

}
