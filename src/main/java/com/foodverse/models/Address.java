package com.foodverse.models;

public record Address(
    String street,
    String number,
    String floor,
    String doorbell,
    String comments) {

    public Address withStreet(String street) {
        if (this.street.equals(street)) return this;
        return new Address(street, number, floor, doorbell, comments);
    }

    public Address withNumber(String number) {
        if (this.number.equals(number)) return this;
        return new Address(street, number, floor, doorbell, comments);
    }

    public Address withFloor(String floor) {
        if (this.floor.equals(floor)) return this;
        return new Address(street, number, floor, doorbell, comments);
    }

    public Address withDoorbell(String doorbell) {
        if (this.doorbell.equals(doorbell)) return this;
        return new Address(street, number, floor, doorbell, comments);
    }

    public Address withComments(String comments) {
        if (this.comments.equals(comments)) return this;
        return new Address(street, number, floor, doorbell, comments);
    }

}
