package com.example.SmartCampo.dto;

import com.example.SmartCampo.entities.Animal;
import com.example.SmartCampo.entities.SituacaoAnimal;
import com.example.SmartCampo.entities.TipoAnimal;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class AnimalDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String species;
    private TipoAnimal tipo;
    private String breed;
    private LocalDate birthDate;
    private Double weight;

    // NOVOS CAMPOS
    private SituacaoAnimal situacao;
    private LocalDate dataVenda;
    private LocalDate dataObito;
    private LocalDate dataTransferencia;
    private String observacaoSituacao;
    private String comprador;
    private Double valorVenda;

    // Dados da mãe
    private Long motherId;
    private String motherName;

    // Dados do pai
    private Long fatherId;
    private String fatherName;

    // Fazenda atual
    private Long currentFarmId;
    private String currentFarmName;

    // Campos calculados
    private Integer ageInMonths;
    private Integer ageInYears;
    private String ageFormatted;

    public AnimalDTO() {
    }

    public AnimalDTO(Animal entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.species = entity.getSpecies();
        this.tipo = entity.getTipo();
        this.breed = entity.getBreed();
        this.birthDate = entity.getBirthDate();
        this.weight = entity.getWeight();

        // NOVOS CAMPOS
        this.situacao = entity.getSituacao();
        this.dataVenda = entity.getDataVenda();
        this.dataObito = entity.getDataObito();
        this.dataTransferencia = entity.getDataTransferencia();
        this.observacaoSituacao = entity.getObservacaoSituacao();
        this.comprador = entity.getComprador();
        this.valorVenda = entity.getValorVenda();

        // Dados da mãe
        if (entity.getMother() != null) {
            this.motherId = entity.getMother().getId();
            this.motherName = entity.getMother().getName();
        }

        // Dados do pai
        if (entity.getFather() != null) {
            this.fatherId = entity.getFather().getId();
            this.fatherName = entity.getFather().getName();
        }

        // Fazenda atual
        if (entity.getCurrentFarm() != null) {
            this.currentFarmId = entity.getCurrentFarm().getId();
            this.currentFarmName = entity.getCurrentFarm().getName();
        }

        calculateAge();
    }

    private void calculateAge() {
        if (this.birthDate != null) {
            Period period = Period.between(this.birthDate, LocalDate.now());
            this.ageInMonths = period.getYears() * 12 + period.getMonths();
            this.ageInYears = period.getYears();

            if (period.getYears() > 0) {
                this.ageFormatted = period.getYears() + " ano" + (period.getYears() > 1 ? "s" : "");
                if (period.getMonths() > 0) {
                    this.ageFormatted += " e " + period.getMonths() + " mês" + (period.getMonths() > 1 ? "es" : "");
                }
            } else {
                this.ageFormatted = period.getMonths() + " mês" + (period.getMonths() > 1 ? "es" : "");
            }
        }
    }

    // Getters e Setters (incluindo os novos)

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

    public TipoAnimal getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnimal tipo) {
        this.tipo = tipo;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        calculateAge();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    // NOVOS GETTERS E SETTERS
    public SituacaoAnimal getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoAnimal situacao) {
        this.situacao = situacao;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public LocalDate getDataObito() {
        return dataObito;
    }

    public void setDataObito(LocalDate dataObito) {
        this.dataObito = dataObito;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public String getObservacaoSituacao() {
        return observacaoSituacao;
    }

    public void setObservacaoSituacao(String observacaoSituacao) {
        this.observacaoSituacao = observacaoSituacao;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Long getCurrentFarmId() {
        return currentFarmId;
    }

    public void setCurrentFarmId(Long currentFarmId) {
        this.currentFarmId = currentFarmId;
    }

    public String getCurrentFarmName() {
        return currentFarmName;
    }

    public void setCurrentFarmName(String currentFarmName) {
        this.currentFarmName = currentFarmName;
    }

    public Integer getAgeInMonths() {
        return ageInMonths;
    }

    public Integer getAgeInYears() {
        return ageInYears;
    }

    public String getAgeFormatted() {
        return ageFormatted;
    }
}