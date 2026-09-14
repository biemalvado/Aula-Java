package com.template.util;

import com.template.validator.CampoObrigatorioValidador;
import com.template.validator.EspecieValidador;
import com.template.validator.IdadeValidador;
import com.template.validator.InterfacePassarinhoValidador;
import com.template.validator.Validador;

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
                List.of(
                        new CampoObrigatorioValidador(
                                "Espécie",
                                especie
                        ),
                        new EspecieValidador(
                                especie
                        )
                );

        executarValidadores(validadores);
    }

    private void validarIdade(Integer idade) {

        List<Validador<Integer>> validadores =
                List.of(
                        new IdadeValidador(
                                idade
                        )
                );

        executarValidadores(validadores);
    }

    private <T> void executarValidadores(
            List<Validador<T>> validadores
    ) {

        for (Validador<T> validador : validadores) {

            if (!validador.validar(
                    validador.getValor()
            )) {

                throw new IllegalArgumentException(
                        validador.getMensagemErro()
                );
            }
        }
    }
}