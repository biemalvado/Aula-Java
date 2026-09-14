package com.template.service;

import com.template.model.dao.PassarinhoDAO;
import com.template.model.dto.PassarinhoDTO;
import com.template.validator.InterfacePassarinhoValidador;

import java.util.List;

public class PassarinhoService {

    private final PassarinhoDAO dao;
    private final InterfacePassarinhoValidador passarinhoValidador;

    public PassarinhoService(
            PassarinhoDAO dao,
            InterfacePassarinhoValidador passarinhoValidador
    ) {

        this.dao = dao;
        this.passarinhoValidador = passarinhoValidador;
    }

    public void cadastrar(
            String especie,
            boolean cativeiro,
            int idade
    ) {

        passarinhoValidador.validarPassarinho(
                especie,
                idade
        );

        PassarinhoDTO novoPassarinho =
                new PassarinhoDTO(
                        especie,
                        cativeiro,
                        idade
                );

        if (!dao.cadastrar(novoPassarinho)) {

            throw new RuntimeException(
                    "Falha ao cadastrar o passarinho no banco de dados."
            );
        }
    }

    public void atualizar(
            int id,
            String especie,
            boolean cativeiro,
            int idade
    ) {

        passarinhoValidador.validarPassarinho(
                especie,
                idade
        );

        PassarinhoDTO passarinho =
                new PassarinhoDTO(
                        id,
                        especie,
                        cativeiro,
                        idade
                );

        if (!dao.atualizar(passarinho)) {

            throw new RuntimeException(
                    "Falha ao atualizar o passarinho no banco de dados."
            );
        }
    }

    public void deletar(int id) {

        if (!dao.deletar(id)) {

            throw new RuntimeException(
                    "Falha ao excluir o passarinho no banco de dados."
            );
        }
    }

    public List<PassarinhoDTO> listarTodos() {

        return dao.listarTodos();
    }
}