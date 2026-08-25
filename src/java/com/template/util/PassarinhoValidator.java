package com.template.util;

import com.template.validator.EspecieValidador;
import com.template.validator.Validador;

import java.util.ArrayList;
import java.util.List;

public class PassarinhoValidator {

    // O método principal fica muito mais limpo e fácil de ler
    public static void validarPassarinho(String especie, Integer idade) {
        validarEspecie(especie);
        validarIdade(idade);
    }

    // --- Método extraído para validação da espécie ---
    private static void validarEspecie(String especie) {
        List<Validador<String>> validadoresString = new ArrayList<>();
        validadoresString.add(new EspecieValidador(especie));

        for (Validador<String> validador : validadoresString) {
            if (!validador.validar(validador.getValor())) {
                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }
    }

    // --- Método extraído para validação da idade ---
    private static void validarIdade(Integer idade) {
        List<Validador<Integer>> validadoresInt = new ArrayList<>();

        // Aviso resolvido: Usando apenas <> em vez de <Integer>
        validadoresInt.add(new Validador<>() {
            @Override
            public boolean validar(Integer valor) {
                return valor != null && valor >= 0;
            }

            @Override
            public String getMensagemErro() {
                return "O campo 'Idade (anos)' deve ser um número maior ou igual a zero.";
            }

            @Override
            public Integer getValor() {
                return idade;
            }
        });

        for (Validador<Integer> validador : validadoresInt) {
            if (!validador.validar(validador.getValor())) {
                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }
    }
}