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

        List<Validador<String>> validadoresString =
                new ArrayList<>();

        validadoresString.add(
                new EspecieValidador(especie)
        );

        for (Validador<String> validador
                : validadoresString) {

            if (!validador.validar(
                    validador.getValor())) {

                throw new IllegalArgumentException(
                        validador.getMensagemErro()
                );
            }
        }
    }

    private void validarIdade(Integer idade) {

        List<Validador<Integer>> validadoresInt =
                new ArrayList<>();

        validadoresInt.add(new Validador<>() {

            @Override
            public boolean validar(Integer valor) {
                return valor != null && valor >= 0;
            }

            @Override
            public String getMensagemErro() {
                return "O campo 'Idade (anos)' "
                        + "deve ser um número maior "
                        + "ou igual a zero.";
            }

            @Override
            public Integer getValor() {
                return idade;
            }
        });

        for (Validador<Integer> validador
                : validadoresInt) {

            if (!validador.validar(
                    validador.getValor())) {

                throw new IllegalArgumentException(
                        validador.getMensagemErro()
                );
            }
        }
    }
}