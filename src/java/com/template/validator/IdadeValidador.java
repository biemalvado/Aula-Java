package com.template.validator;

public class IdadeValidador
        implements Validador<Integer> {

    private final Integer valor;

    public IdadeValidador(Integer valor) {

        this.valor = valor;
    }

    @Override
    public boolean validar(Integer valor) {

        return valor != null
                && valor >= 0
                && valor <= 150;
    }

    @Override
    public String getMensagemErro() {

        return "O campo 'Idade (anos)' deve estar entre 0 e 150.";
    }

    @Override
    public Integer getValor() {

        return valor;
    }
}