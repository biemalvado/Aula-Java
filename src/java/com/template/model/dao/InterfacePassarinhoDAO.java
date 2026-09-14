package com.template.model.dao;

import com.template.model.dto.PassarinhoDTO;

import java.util.List;

public interface InterfacePassarinhoDAO {

    boolean cadastrar(
            PassarinhoDTO passarinho
    );

    boolean atualizar(
            PassarinhoDTO passarinho
    );

    boolean deletar(int id);

    List<PassarinhoDTO> listarTodos();
}