package com.example.SmartCampo.entities;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_farm")
public class Farm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private Double totalArea;

    // Animais que estão ATUALMENTE nesta fazenda
    @OneToMany(mappedBy = "currentFarm")
    private List<Animal> currentAnimals = new ArrayList<>();

    // Histórico de animais que passaram por aqui
    @OneToMany(mappedBy = "farm")
    private List<AnimalFarmHistory> animalHistory = new ArrayList<>();

    public Farm() {
    }

    public Farm(Long id, String name, String location, Double totalArea) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.totalArea = totalArea;
    }

    // Getters e Setters
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

    public List<Animal> getCurrentAnimals() {
        return currentAnimals;
    }

    public List<AnimalFarmHistory> getAnimalHistory() {
        return animalHistory;
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