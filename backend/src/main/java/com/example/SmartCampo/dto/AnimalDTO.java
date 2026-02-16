package com.example.SmartCampo.dto;

import com.example.SmartCampo.entities.Animal;

import java.io.Serial;
import java.io.Serializable;

public class AnimalDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String species;
    private String breed;
    private String birthDate;
    private Double weight;

    public AnimalDTO() {
    }

    public AnimalDTO(Long id, String name, String species, String breed, String birthDate, Double weight) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public AnimalDTO(Animal entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.species = entity.getSpecies();
        this.breed = entity.getBreed();
        this.birthDate = entity.getBirthDate();
        this.weight = entity.getWeight();
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
