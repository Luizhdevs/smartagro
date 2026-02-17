package com.example.SmartCampo.entities;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_animal")
public class Animal implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;

    @Enumerated(EnumType.STRING)
    private TipoAnimal tipo;

    private String breed;
    private LocalDate birthDate;
    private Double weight;

    // NOVOS CAMPOS DE SITUAÇÃO
    @Enumerated(EnumType.STRING)
    private SituacaoAnimal situacao;

    private LocalDate dataVenda;
    private LocalDate dataObito;
    private LocalDate dataTransferencia;
    private String observacaoSituacao; // Motivo da venda, causa da morte, etc.

    // Quem comprou (se vendido)
    private String comprador;
    private Double valorVenda;

    // Auto-relacionamento para filiação
    @ManyToOne
    @JoinColumn(name = "mother_id")
    private Animal mother;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Animal father;

    @OneToMany(mappedBy = "mother")
    private List<Animal> offspringAsMother = new ArrayList<>();

    @OneToMany(mappedBy = "father")
    private List<Animal> offspringAsFather = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "current_farm_id")
    private Farm currentFarm;

    @OneToMany(mappedBy = "animal")
    private List<AnimalFarmHistory> farmHistory = new ArrayList<>();

    // Construtor padrão
    public Animal() {
        this.situacao = SituacaoAnimal.ATIVO; // Por padrão, animal nasce ATIVO
    }

    // Construtor com campos principais
    public Animal(Long id, String name, String species, TipoAnimal tipo, String breed, LocalDate birthDate, Double weight) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.tipo = tipo;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.situacao = SituacaoAnimal.ATIVO;
    }

    // GETTERS E SETTERS (incluindo os novos)

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

    // Métodos de negócio para mudar situação
    public void vender(String comprador, Double valor, LocalDate dataVenda) {
        this.situacao = SituacaoAnimal.VENDIDO;
        this.comprador = comprador;
        this.valorVenda = valor;
        this.dataVenda = dataVenda;
        this.currentFarm = null; // Não está mais na fazenda
    }

    public void registrarObito(LocalDate dataObito, String causa) {
        this.situacao = SituacaoAnimal.MORTO;
        this.dataObito = dataObito;
        this.observacaoSituacao = causa;
        this.currentFarm = null;
    }

    public void transferir(Farm novaFazenda, LocalDate dataTransferencia) {
        this.situacao = SituacaoAnimal.TRANSFERIDO;
        this.dataTransferencia = dataTransferencia;
        // O histórico vai registrar a transferência
    }

    public void ativarNovamente() {
        this.situacao = SituacaoAnimal.ATIVO;
    }

    // Getters existentes...
    public Animal getMother() {
        return mother;
    }

    public void setMother(Animal mother) {
        this.mother = mother;
    }

    public Animal getFather() {
        return father;
    }

    public void setFather(Animal father) {
        this.father = father;
    }

    public List<Animal> getOffspringAsMother() {
        return offspringAsMother;
    }

    public List<Animal> getOffspringAsFather() {
        return offspringAsFather;
    }

    public Farm getCurrentFarm() {
        return currentFarm;
    }

    public void setCurrentFarm(Farm currentFarm) {
        this.currentFarm = currentFarm;
    }

    public List<AnimalFarmHistory> getFarmHistory() {
        return farmHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}