package ua.yura.models;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

public class Package {
    private UUID id;
    private UUID idLot;

    @Size(min = 3, max = 22, message = "TTH повинно бути не менше 3 символів і не більше 22 символів")
    private String parcelTrackingNumber;
    @Pattern(regexp = "^(MD|PM|UC)\\d{2}-\\d{1,10}$", message = "Номер повинен починатися з MD, PM або UC, за якими йдуть 2 цифри, тире та від 1 до 10 цифр")
    private String cardNumber;
    private String client;
    private String companyName;
    @Size(min = 3, max = 255, message = "Опис повинен містити не менше 3 символів і не більше 255 символів")
    private String descriptions;
    @Min(value = 0, message = "Ціна доставки не може бути негативною")
    @Digits(integer = 6, fraction = 0, message = "Ціна доставки повинна містити не більше 6 цифр")
    private int deliveryPrice;


    public Package() {
    }

    public Package(UUID id) {
        this.id = id;
    }

    public Package(UUID id, String parcelTrackingNumber, String cardNumber, String client, String companyName, String descriptions, int deliveryPrice) {
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

    public String getParcelTrackingNumber() {
        return parcelTrackingNumber;
    }

    public void setParcelTrackingNumber(String parcelTrackingNumber) {
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

