package com.template.validator;

public class EspecieValidador implements Validador<String> {

    private String valor;
    private String mensagemErro;

    public EspecieValidador(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        // 1. Verifica se é nulo ou vazio
        if (valor == null || valor.trim().isEmpty()) {
            this.mensagemErro = "O campo 'Espécie' é obrigatório.";
            return false;
        }

        // 2. Verifica o tamanho mínimo
        if (valor.trim().length() < 3) {
            this.mensagemErro = "O campo 'Espécie' deve ter pelo menos 3 caracteres.";
            return false;
        }

        // 3. Verifica se contém apenas letras, espaços e hifens (ex: Pica-pau, João-de-barro)
        if (!valor.matches("^[a-zA-ZÀ-ÿ\\s\\-]+$")) {
            this.mensagemErro = "O campo 'Espécie' deve conter apenas letras, espaços e hifens.";
            return false;
        }

        return true; // Passou em todas as validações
    }

    @Override
    public String getMensagemErro() {
        return this.mensagemErro;
    }

    @Override
    public String getValor() {
        return valor;
    }
}