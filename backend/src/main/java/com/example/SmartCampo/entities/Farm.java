package com.example.SmartCampo.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Farm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String location;
    private Double totalArea;

    public Farm() {
    }

    public Farm(Long id, String name, String location, Double totalArea) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.totalArea = totalArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Farm farm = (Farm) o;
        return Objects.equals(id, farm.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
