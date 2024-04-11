package ua.yura.models;

import java.util.UUID;

public class Package {
    private UUID id;
    private long parcelTrackingNumber;
    private int cardNumber;
    private String client;
    private String descriptions;
    private int deliveryPrice;

    public Package(UUID id, long parcelTrackingNumber, int cardNumber, String client, String descriptions, int deliveryPrice) {
        this.id = id;
        this.parcelTrackingNumber = parcelTrackingNumber;
        this.cardNumber = cardNumber;
        this.client = client;
        this.descriptions = descriptions;
        this.deliveryPrice = deliveryPrice;
    }

    public Package() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getParcelTrackingNumber() {
        return parcelTrackingNumber;
    }

    public void setParcelTrackingNumber(long parcelTrackingNumber) {
        this.parcelTrackingNumber = parcelTrackingNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
