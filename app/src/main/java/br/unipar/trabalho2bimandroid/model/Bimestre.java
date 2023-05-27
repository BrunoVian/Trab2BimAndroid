package br.unipar.trabalho2bimandroid.model;

public enum Bimestre {

    BIMUM("1° Bimestre"),
    BIMDOIS("2° Bimestre"),
    BIMTRES("3° Bimestre"),
    BIMQUATRO("4° Bimestre");

    private String descricao;

    Bimestre(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
