package ua.yura.models;

import java.util.List;
import java.util.UUID;

public class Company {
    private UUID id;
    private String name;
    private int EDRPOUCode;
    private List<Subdivision> subdivisionList;

    public Company() {
    }

    public Company(UUID id, String name, int EDRPOUCode, List<Subdivision> subdivisionList) {
        this.id = id;
        this.name = name;
        this.EDRPOUCode = EDRPOUCode;
        this.subdivisionList = subdivisionList;
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

    public int getEDRPOUCode() {
        return EDRPOUCode;
    }

    public void setEDRPOUCode(int EDRPOUCode) {
        this.EDRPOUCode = EDRPOUCode;
    }

    public List<Subdivision> getSubdivisionList() {
        return subdivisionList;
    }

    public void setSubdivisionList(List<Subdivision> subdivisionList) {
        this.subdivisionList = subdivisionList;
    }
}
