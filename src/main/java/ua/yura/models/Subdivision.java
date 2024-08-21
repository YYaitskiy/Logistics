package ua.yura.models;

import java.util.UUID;

public class Subdivision {
    private UUID id;
    private String name;
    private int numberSubdivision;
    private String telephone;
    private String region;
    private String city;
    private String address;
    public Subdivision() {
    }

    public Subdivision(UUID id, String name, int numberSubdivision, String telephone, String region, String city, String address) {
        this.id = id;
        this.name = name;
        this.numberSubdivision = numberSubdivision;
        this.telephone = telephone;
        this.region = region;
        this.city = city;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberSubdivision() {
        return numberSubdivision;
    }

    public void setNumberSubdivision(int numberSubdivision) {
        this.numberSubdivision = numberSubdivision;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String locality) {
        this.region = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
