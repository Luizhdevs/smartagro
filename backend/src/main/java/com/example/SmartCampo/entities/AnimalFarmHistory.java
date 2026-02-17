package com.example.SmartCampo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_animal_farm_history")
public class AnimalFarmHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    private LocalDate entryDate; // Data de entrada
    private LocalDate exitDate; // Data de saída (pode ser null se ainda está lá)

    private String observation; // Observações sobre o período

    public AnimalFarmHistory() {
    }

    public AnimalFarmHistory(Long id, Animal animal, Farm farm, LocalDate entryDate, LocalDate exitDate, String observation) {
        this.id = id;
        this.animal = animal;
        this.farm = farm;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.observation = observation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnimalFarmHistory that = (AnimalFarmHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}