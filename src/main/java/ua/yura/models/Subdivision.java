package ua.yura.models;

import java.util.UUID;

public class Subdivision {
    private UUID id;
    private String name;
    private int numberSubdivision;
    private String telephone;
    private String locality;
    private String address;

    public Subdivision() {
    }

    public Subdivision(UUID id, String name, int numberSubdivision, String telephone, String locality, String address) {
        this.id = id;
        this.name = name;
        this.numberSubdivision = numberSubdivision;
        this.telephone = telephone;
        this.locality = locality;
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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
