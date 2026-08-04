package com.template.util;

import com.template.validator.CampoObrigatorioValidador;
import com.template.validator.Validador;

import java.util.ArrayList;
import java.util.List;

public class PassarinhoValidator {

    public static void validarPassarinho(String especie) {

        List<Validador<String>> validadores = new ArrayList<>();


        validadores.add(new CampoObrigatorioValidador("Espécie", especie));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {

                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }
    }
}