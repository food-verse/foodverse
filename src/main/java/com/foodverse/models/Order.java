package com.foodverse.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import com.google.gson.annotations.SerializedName;

public final class Order {

    @SerializedName("merchant")
    private String merchant;

    @SerializedName("date")
    private Date date;

    @SerializedName("items")
    private Map<String, Integer> items;

    @SerializedName("delivery_tip")
    private float deliveryTip;

    @SerializedName("total")
    private float total;

    @SerializedName("payment")
    private PaymentMethod method;

    @SerializedName("type")
    private OrderType type;

    public Order(String merchant, Date date, Map<String, Integer> items, float deliveryTip,
            float total, PaymentMethod method, OrderType type) {
        this.merchant = merchant;
        this.date = date;
        this.items = items;
        this.deliveryTip = deliveryTip;
        this.total = total;
        this.method = method;
        this.type = type;
    }

    public boolean isRecent() {
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.isAfter(startDate) || localDate.isBefore(endDate);
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public float getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(float deliveryTip) {
        this.deliveryTip = deliveryTip;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

}
