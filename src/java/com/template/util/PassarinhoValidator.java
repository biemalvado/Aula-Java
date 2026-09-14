package com.template.util;

import com.template.validator.EspecieValidador;
import com.template.validator.InterfacePassarinhoValidador;
import com.template.validator.Validador;

import java.util.ArrayList;
import java.util.List;

public class PassarinhoValidator
        implements InterfacePassarinhoValidador {

    @Override
    public void validarPassarinho(
            String especie,
            Integer idade
    ) {

        validarEspecie(especie);
        validarIdade(idade);
    }

    private void validarEspecie(String especie) {

        List<Validador<String>> validadores =
                new ArrayList<>();

        validadores.add(
                new EspecieValidador(especie)
        );

        for (Validador<String> validador : validadores) {

            if (!validador.validar(
                    validador.getValor())) {

                throw new IllegalArgumentException(
                        validador.getMensagemErro()
                );
            }
        }
    }

    private void validarIdade(Integer idade) {

        if (idade == null || idade < 0) {

            throw new IllegalArgumentException(
                    "O campo 'Idade (anos)' deve ser maior ou igual a zero."
            );
        }
    }
}