package com.template;

public class PassarinhoDTO {

    private int id;
    private String especie;
    private boolean cativeiro;
    private int idade;

    public PassarinhoDTO() {
    }

    public PassarinhoDTO(String especie, boolean cativeiro, int idade) {
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
}