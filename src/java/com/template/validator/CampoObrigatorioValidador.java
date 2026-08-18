package com.template.validator;

public class CampoObrigatorioValidador implements Validador<String> {
    private String nomeCampo;
    private String valor;

    public CampoObrigatorioValidador(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "O campo '" + nomeCampo + "' é obrigatório e não pode estar vazio.";
    }

    @Override
    public String getValor() {
        return valor;
    }
}