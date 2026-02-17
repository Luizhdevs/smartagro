package com.example.SmartCampo.entities;

public enum SituacaoAnimal {
    ATIVO("Ativo na fazenda"),
    VENDIDO("Vendido para terceiros"),
    MORTO("Óbito"),
    TRANSFERIDO("Transferido para outra fazenda"),
    DESCARTADO("Descartado da reprodução");

    private String descricao;

    SituacaoAnimal(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}