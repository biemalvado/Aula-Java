package com.template.util;

import com.template.validator.CampoObrigatorioValidador;
import com.template.validator.Validador;

import java.util.ArrayList;
import java.util.List;

public class PassarinhoValidator {

    public static void validarPassarinho(String especie) {
        // Lista de validadores que serão aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("Espécie", especie));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // O validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {
                // Em vez de retornar falso e usar o DialogUtil direto aqui,
                // lançamos a exceção para manter a arquitetura limpa (SRP).
                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }
    }
}