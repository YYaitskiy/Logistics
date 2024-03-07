package ua.yura.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lot {
    private UUID id;
    private String status;
    private LocalDate shippingDate;
    private List<Package> packageList;

    public Lot(UUID id, String status, LocalDate shippingDate, List<Package> packageList) {
        this.id = id;
        this.status = status;
        this.shippingDate = shippingDate;
        this.packageList = packageList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public List<Package> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<Package> packageList) {
        this.packageList = packageList;
    }
}
