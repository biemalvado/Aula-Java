package com.template.service;

import com.template.model.dao.InterfacePassarinhoDAO;
import com.template.model.dto.PassarinhoDTO;
import com.template.validator.InterfacePassarinhoValidador;

import java.util.List;

public class PassarinhoService
        implements InterfacePassarinhoService {

    private final InterfacePassarinhoDAO dao;

    private final InterfacePassarinhoValidador
            passarinhoValidador;

    public PassarinhoService(
            InterfacePassarinhoDAO dao,
            InterfacePassarinhoValidador passarinhoValidador
    ) {

        this.dao = dao;

        this.passarinhoValidador =
                passarinhoValidador;
    }

    @Override
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
                        especie.trim(),
                        cativeiro,
                        idade
                );

        if (!dao.cadastrar(
                novoPassarinho
        )) {

            throw new RuntimeException(
                    "Falha ao cadastrar o passarinho no banco de dados."
            );
        }
    }

    @Override
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
                        especie.trim(),
                        cativeiro,
                        idade
                );

        if (!dao.atualizar(
                passarinho
        )) {

            throw new RuntimeException(
                    "Falha ao atualizar o passarinho no banco de dados."
            );
        }
    }

    @Override
    public void deletar(int id) {

        if (!dao.deletar(id)) {

            throw new RuntimeException(
                    "Falha ao excluir o passarinho no banco de dados."
            );
        }
    }

    @Override
    public List<PassarinhoDTO> listarTodos() {

        return dao.listarTodos();
    }
}