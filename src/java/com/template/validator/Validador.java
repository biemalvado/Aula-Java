package com.template.validator;

public interface Validador<T> {
    boolean validar(T valorAtual);
    String getMensagemErro();
    T getValor();
}