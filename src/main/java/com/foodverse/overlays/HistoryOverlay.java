package com.foodverse.overlays;

import java.awt.Component;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.foodverse.models.Order;
import com.foodverse.models.User;
import com.foodverse.props.OrderCardProps;
import com.foodverse.utility.common.DateUtils;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Divider;
import com.foodverse.widgets.layout.Carousel;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class HistoryOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private User user;

    private LocalDate currBaseDate;

    @Override
    public Component getRef() {

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            Router.openOverlay(new Alert("Error", "Authenticated User is not found"));
            Router.closeOverlay();
        }

        // creating the main panel of the page
        var panel = new Column();

        // creating overlay's heading widget
        var text = new Heading("Orders", HeadingSize.L);
        panel.addWidget(text, new EdgeInsets.Builder()
            .top(16)
            .left(8)
            .build());

        // getting the current date when this program is running to compare it
        // with the date of an order and create the order's carousel heading
        var oneWeekDate = LocalDate.now(ZoneId.systemDefault()).minusWeeks(1);
        var oneMonthDate = LocalDate.now(ZoneId.systemDefault()).minusWeeks(4);
        var endDate = LocalDate.now(ZoneId.systemDefault());

        boolean lastWeekTextPrinted = false;
        boolean lastMonthTextPrinted = false;
        boolean lastYearTextPrinted = false;

        // temporary set to use if one order doesn't get displayed
        Set<Order> tempSet = user.orders();

        // counter of orders displayed in one row
        int count = 0;

        Set<Order> ordersInARow = new HashSet<>();
        LocalDate baseDate;
        // displaying the orders in the overlay. The maximum orders than be displayed in
        // one row of the overlay is 2
        for (Order order : user.orders()) {
            if (((order.date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .isAfter(oneWeekDate)))
                || (order.date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(oneWeekDate))) {
                if (!lastWeekTextPrinted) {
                    panel.addComponent(new Divider());
                    var lastWeekText = new Heading("Last Week", HeadingSize.XS);
                    panel.addWidget(lastWeekText, new EdgeInsets.Builder()
                            .top(13)
                            .left(8)
                            .build(),
                        Align.CENTER_LEFT);
                    lastWeekTextPrinted = true;
                }
                baseDate = oneWeekDate;
            } else if (order.date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .isAfter(oneMonthDate)
                || (order.date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(oneMonthDate))) {
                if (!lastMonthTextPrinted) {
                    panel.addComponent(new Divider());
                    var lastMonthText = new Heading("Last Month", HeadingSize.XS);
                    panel.addWidget(lastMonthText, new EdgeInsets.Builder()
                            .top(13)
                            .left(8)
                            .build(),
                        Align.CENTER_LEFT);
                    lastMonthTextPrinted = true;
                }
                baseDate = oneMonthDate;
            } else {
                if (!lastYearTextPrinted) {
                    panel.addComponent(new Divider());
                    var lastYearText = new Heading("Last Year", HeadingSize.XS);
                    panel.addWidget(lastYearText, new EdgeInsets.Builder()
                            .top(13)
                            .left(8)
                            .build(),
                        Align.CENTER_LEFT);
                    lastYearTextPrinted = true;
                }
                baseDate = LocalDate.now(ZoneId.systemDefault()).minusWeeks(52);
            }

            currBaseDate = baseDate;
            if (count == 2) {
                List<OrderCardProps> orderProps = ordersInARow.stream()
                    .filter(currOrder -> DateUtils.isInRange(order.date(), currBaseDate,
                        endDate))
                    .sorted(Comparator.comparing(Order::date).reversed())
                    .map(OrderCardProps::from)
                    .collect(Collectors.toList());

                // Create a carousel for the available offers
                var offerCarousel = new Carousel(orderProps);

                // Add the carousel for the available offers to the main panel
                panel.addComponent(offerCarousel.getRef());

                // clear the orders from the TempSet that got displayed in the overlay
                for (Order orderDisplayed : ordersInARow) {
                    tempSet.remove(orderDisplayed);
                }

                // clear the orders that were showed in the previous row and reset the counter
                ordersInARow.clear();
                count = 1;
            } else {
                count++;
            }
            ordersInARow.add(order);
        }

        if (!tempSet.isEmpty()) {
            List<OrderCardProps> orderProps = tempSet.stream()
                .filter(currOrder -> DateUtils.isInRange(currOrder.date(), currBaseDate,
                    endDate))
                .sorted(Comparator.comparing(Order::date).reversed())
                .map(OrderCardProps::from)
                .collect(Collectors.toList());
            var offerCarousel = new Carousel(orderProps);
            panel.addComponent(offerCarousel.getRef());
        }


        return new ScrollView(panel.getRef()).getRef();
    }

}
