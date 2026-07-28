package com.template.service;


import com.template.model.dao.PassarinhoDAO;
import com.template.model.dto.PassarinhoDTO;
import com.template.util.PassarinhoValidator;

import java.util.List;

public class PassarinhoService {

    private final PassarinhoDAO dao;

    public PassarinhoService() {
        this.dao = new PassarinhoDAO();
    }

    public void cadastrar(String especie, boolean cativeiro, int idade) {
        // 1. Valida os dados (agora chamando o método atualizado do OCP)
        PassarinhoValidator.validarPassarinho(especie);

        // 2. Monta o DTO
        PassarinhoDTO novoPassarinho = new PassarinhoDTO(especie, cativeiro, idade);

        // 3. Salva no banco e checa se deu erro
        if (!dao.cadastrar(novoPassarinho)) {
            throw new RuntimeException("Falha ao cadastrar o passarinho no banco de dados.");
        }
    }

    public void atualizar(int id, String especie, boolean cativeiro, int idade) {
        // 1. Valida os dados
        PassarinhoValidator.validarPassarinho(especie);

        PassarinhoDTO passarinho = new PassarinhoDTO(id, especie, cativeiro, idade);

        if (!dao.atualizar(passarinho)) {
            throw new RuntimeException("Falha ao atualizar o passarinho no banco de dados.");
        }
    }

    public void deletar(int id) {
        if (!dao.deletar(id)) {
            throw new RuntimeException("Falha ao excluir o passarinho no banco de dados.");
        }
    }

    public List<PassarinhoDTO> listarTodos() {
        return dao.listarTodos();
    }
}