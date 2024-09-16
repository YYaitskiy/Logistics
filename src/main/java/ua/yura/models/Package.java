package ua.yura.models;

import java.util.List;
import java.util.UUID;

public class Package {
    private UUID id;

    private UUID idLot;
    private long parcelTrackingNumber;
    private String cardNumber;
    private String client;
    private String companyName;
    private String descriptions;
    private int deliveryPrice;


    public Package() {
    }

    public Package(UUID id) {
        this.id = id;
    }

    public Package(UUID id, long parcelTrackingNumber, String cardNumber, String client, String companyName, String descriptions, int deliveryPrice) {
        this.id = id;
        this.parcelTrackingNumber = parcelTrackingNumber;
        this.cardNumber = cardNumber;
        this.client = client;
        this.companyName = companyName;
        this.descriptions = descriptions;
        this.deliveryPrice = deliveryPrice;
    }

    public Package(UUID id, String companyName) {
        this.id = id;
        this.companyName = companyName;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UUID getIdLot() {
        return idLot;
    }

    public void setIdLot(UUID idLot) {
        this.idLot = idLot;
    }
}

