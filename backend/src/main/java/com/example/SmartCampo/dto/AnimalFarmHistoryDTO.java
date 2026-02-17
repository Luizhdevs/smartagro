package com.example.SmartCampo.dto;

import com.example.SmartCampo.entities.Animal;
import com.example.SmartCampo.entities.AnimalFarmHistory;
import com.example.SmartCampo.entities.Farm;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AnimalFarmHistoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    // Dados do animal (apenas o necessário)
    private Long animalId;
    private String animalName;

    // Dados da fazenda (apenas o necessário)
    private Long farmId;
    private String farmName;
    private String farmLocation;

    private LocalDate entryDate;
    private LocalDate exitDate;
    private String observation;

    // Campos calculados (opcionais, mas úteis)
    private Long daysSpent;      // dias de permanência
    private String periodFormatted; // período formatado
    private Boolean isCurrent;    // se ainda está na fazenda

    public AnimalFarmHistoryDTO() {
    }

    public AnimalFarmHistoryDTO(AnimalFarmHistory entity) {
        this.id = entity.getId();

        // Dados do animal
        if (entity.getAnimal() != null) {
            this.animalId = entity.getAnimal().getId();
            this.animalName = entity.getAnimal().getName();
        }

        // Dados da fazenda
        if (entity.getFarm() != null) {
            this.farmId = entity.getFarm().getId();
            this.farmName = entity.getFarm().getName();
            this.farmLocation = entity.getFarm().getLocation();
        }

        this.entryDate = entity.getEntryDate();
        this.exitDate = entity.getExitDate();
        this.observation = entity.getObservation();

        // Calcula campos úteis
        calculateDerivedFields();
    }

    private void calculateDerivedFields() {
        if (entryDate != null) {
            // Verifica se é a fazenda atual (exitDate nulo)
            this.isCurrent = (exitDate == null);

            // Calcula dias de permanência
            LocalDate endDate = (exitDate != null) ? exitDate : LocalDate.now();
            this.daysSpent = ChronoUnit.DAYS.between(entryDate, endDate);

            // Formata o período
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String entryFormatted = entryDate.format(formatter);

            if (exitDate != null) {
                String exitFormatted = exitDate.format(formatter);
                this.periodFormatted = entryFormatted + " até " + exitFormatted;
            } else {
                this.periodFormatted = "Desde " + entryFormatted + " (atual)";
            }
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
        calculateDerivedFields();
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
        calculateDerivedFields();
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getDaysSpent() {
        return daysSpent;
    }

    public String getPeriodFormatted() {
        return periodFormatted;
    }

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    // Método para converter DTO em entidade (se precisar criar um novo histórico)
    public AnimalFarmHistory toEntity(Animal animal, Farm farm) {
        AnimalFarmHistory history = new AnimalFarmHistory();
        history.setAnimal(animal);
        history.setFarm(farm);
        history.setEntryDate(this.entryDate);
        history.setExitDate(this.exitDate);
        history.setObservation(this.observation);
        return history;
    }

    // Método para formatar data de entrada
    public String getFormattedEntryDate() {
        if (entryDate != null) {
            return entryDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return null;
    }

    // Método para formatar data de saída
    public String getFormattedExitDate() {
        if (exitDate != null) {
            return exitDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return null;
    }
}