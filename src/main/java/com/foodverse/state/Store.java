package com.foodverse.state;

import com.foodverse.models.PaymentMethod;
import com.foodverse.utility.core.State;

public final class Store {

    private Store() {}

    public static final State<PaymentMethod> orderPaymentMethod = new State<>(PaymentMethod.CARD);

    public static final State<Double> orderDeliveryTip = new State<>(0.0);

}
