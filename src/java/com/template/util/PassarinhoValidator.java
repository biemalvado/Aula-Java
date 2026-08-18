package com.template.util;

import com.template.validator.EspecieValidador;
import com.template.validator.Validador;

import java.util.ArrayList;
import java.util.List;

public class PassarinhoValidator {

    public static void validarPassarinho(String especie, Integer idade) {

        // --- VALIDAÇÃO DA ESPÉCIE ---
        List<Validador<String>> validadoresString = new ArrayList<>();

        // Adiciona o validador único que faz todas as checagens da espécie
        validadoresString.add(new EspecieValidador(especie));

        for (Validador<String> validador : validadoresString) {
            if (!validador.validar(validador.getValor())) {
                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }

        // --- VALIDAÇÃO DA IDADE ---
        List<Validador<Integer>> validadoresInt = new ArrayList<>();

        validadoresInt.add(new Validador<Integer>() {
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