package com.template.service;

import com.template.model.dto.PassarinhoDTO;

import java.util.List;

public interface InterfacePassarinhoService {

    void cadastrar(
            String especie,
            boolean cativeiro,
            int idade
    );

    void atualizar(
            int id,
            String especie,
            boolean cativeiro,
            int idade
    );

    void deletar(int id);

    List<PassarinhoDTO> listarTodos();
}