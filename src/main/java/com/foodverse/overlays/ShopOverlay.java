package com.foodverse.overlays;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import com.foodverse.models.Shop;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.layout.ScrollView;

public class ShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    public ShopOverlay(String merchant) {
        db.findShopByName(merchant).ifPresentOrElse(shop -> {
            // System.out.println(shop);
        }, () -> {
            // System.out.println("Merchant not found");
        });
        // Alternative
        Optional<Shop> shop = db.findShopByName(merchant);
        if (shop.isPresent()) {
            // System.out.println(shop.get());
        } else {
            // System.out.println("Merchant not found");
        }
    }

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("ShopOverlay", HeadingSize.L);
        var button =
                new PillButton("Close ShopOverlay ->", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                    Router.closeOverlay();
                });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);


        // offers
        var panel1 = new JPanel();

        var offers = new JLabel("Offers");
        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.white);

        panel1.setPreferredSize(new Dimension(1300, 300));
        panel1.setBackground(Color.lightGray);
        panel1.add(offers);

        JScrollPane scroll1 = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll1);



        // this.showItems(panel1);
        // this.showItems(panel2);

        // RectButton "Bug" to appear the OrderOverlay
        var bugButton = new RectButton("Bag", ButtonSize.S, ButtonType.PRIMARY, e -> {
            showSuccessfulOrderMessage(e);
        });

        panel.add(bugButton.getRef());

        // panel.setOpaque(false);

        // panel.add(panel3);


        return new ScrollView(panel).getRef();
    }

    public void showItems(JPanel panel1) {

        for (var i = 0; i < 3; i++) {
            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                // addproducts();

            });
            var panel4 = new JPanel();
            var menuItem = new JLabel("Item");

            panel4.setPreferredSize(new Dimension(1200, 60));
            panel4.add(menuItem);
            panel4.add(productAddButton.getRef());
            panel1.add(panel4);
        }

    }

    private void showSuccessfulOrderMessage(ActionEvent e) {
        JOptionPane.showMessageDialog(getFrame(), "We will deal with this later");
    }

    // public void paint(Graphics g)
    // {
    // super.paint(g);
    // g.setColor(Color.white);
    // g.drawLine(2000, 222, 0, 222);
    // g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    // }

    class RadioButtonListener implements ActionListener {

        private JRadioButton rate1;
        private JRadioButton rate2;
        private JRadioButton rate3;
        private JRadioButton rate4;
        private JRadioButton rate5;

        public RadioButtonListener(JRadioButton rate1, JRadioButton rate2, JRadioButton rate3,
                JRadioButton rate4, JRadioButton rate5) {
            this.rate1 = rate1;
            this.rate2 = rate2;
            this.rate3 = rate3;
            this.rate4 = rate4;
            this.rate5 = rate5;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            double newrate = 0, oldrate = 0;
            Integer numberofrates = 0;

            if (e.getSource() == rate1) {
                numberofrates++;
                System.out.println(rate1.getText());
                calculateNewRate(1, newrate, oldrate, numberofrates);
            } else if (e.getSource() == rate2) {
                System.out.println(rate2.getText());
            } else if (e.getSource() == rate3) {
                System.out.println(rate3.getText());
            } else if (e.getSource() == rate4) {
                System.out.println(rate4.getText());
            } else if (e.getSource() == rate5) {
                System.out.println(rate5.getText());
            }

        }

        private double calculateNewRate(Integer rateValue, double newrate, double oldrate,
                Integer numberofrates) {

            newrate = oldrate + (newrate / numberofrates);

            return newrate;

        }

    }

    // Choose one button for rate

    // public void actionPerformed(ActionEvent e, Integer rateValue, double newrate,
    // double oldrate,
    // Integer numberofrates) {

    // numberofrates++;

    // if (e.getSource().equals("rate1")) {

    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate2")) {
    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate3")) {
    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate4")) {
    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate5")) {

    // }

    // }

    // // Culculate New Rate

    // private double calculateNewRate(Integer rateValue, double newrate, double
    // oldrate, Integer numberofrates) {

    // newrate = oldrate + (newrate / numberofrates);

    // return newrate;

    // }

    // // Add products in the cart

    // private void addproducts() {

    // }
}
