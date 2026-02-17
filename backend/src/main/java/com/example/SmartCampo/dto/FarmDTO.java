package com.example.SmartCampo.dto;

import com.example.SmartCampo.entities.Farm;
import java.io.Serial;
import java.io.Serializable;

public class FarmDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String location;
    private Double totalArea;

    public FarmDTO() {
    }

    public FarmDTO(Farm entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.location = entity.getLocation();
        this.totalArea = entity.getTotalArea();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getTotalArea() { return totalArea; }
    public void setTotalArea(Double totalArea) { this.totalArea = totalArea; }

    // Método útil para converter DTO em entidade
    public Farm toEntity() {
        Farm farm = new Farm();
        farm.setId(this.id);
        farm.setName(this.name);
        farm.setLocation(this.location);
        farm.setTotalArea(this.totalArea);
        return farm;
    }
}