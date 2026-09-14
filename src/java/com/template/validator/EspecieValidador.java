package com.template.validator;

public class EspecieValidador
        implements Validador<String> {

    private final String valor;
    private String mensagemErro;

    public EspecieValidador(String valor) {

        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {

        if (valor == null || valor.trim().isEmpty()) {

            mensagemErro =
                    "O campo 'Espécie' é obrigatório.";

            return false;
        }

        String especie = valor.trim();

        if (especie.length() < 3) {

            mensagemErro =
                    "O campo 'Espécie' deve ter pelo menos 3 caracteres.";

            return false;
        }

        if (!especie.matches(
                "^[a-zA-ZÀ-ÿ\\s\\-]+$"
        )) {

            mensagemErro =
                    "O campo 'Espécie' deve conter apenas letras, espaços e hifens.";

            return false;
        }

        return true;
    }

    @Override
    public String getMensagemErro() {

        return mensagemErro;
    }

    @Override
    public String getValor() {

        return valor;
    }
}