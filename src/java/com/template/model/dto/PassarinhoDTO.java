package com.template.model.dto;

import java.util.Objects;

public class PassarinhoDTO {

    private int id;
    private String especie;
    private boolean cativeiro;
    private int idade;

    public PassarinhoDTO() {
    }

    // Construtor para cadastro (sem ID)
    public PassarinhoDTO(String especie, boolean cativeiro, int idade) {
        this.especie = especie;
        this.cativeiro = cativeiro;
        this.idade = idade;
    }

    // Construtor completo para leitura do banco (com ID)
    public PassarinhoDTO(int id, String especie, boolean cativeiro, int idade) {
        this.id = id;
        this.especie = especie;
        this.cativeiro = cativeiro;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public boolean isCativeiro() {
        return cativeiro;
    }

    public void setCativeiro(boolean cativeiro) {
        this.cativeiro = cativeiro;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        String statusCativeiro = cativeiro ? "Sim" : "Não";
        return String.format("ID: %-4d | Espécie: %-15s | Cativeiro: %-4s | Idade: %d anos",
                id, especie, statusCativeiro, idade);
    }

    // Compara se dois DTOs representam o mesmo registro no banco (pelo ID)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassarinhoDTO that = (PassarinhoDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}