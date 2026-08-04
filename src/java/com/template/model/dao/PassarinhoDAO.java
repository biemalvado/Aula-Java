package com.template.model.dao;

import com.template.ConexaoBD;
import com.template.model.dto.PassarinhoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PassarinhoDAO {

    // 1. Instanciando o Logger para esta classe
    private static final Logger logger = Logger.getLogger(PassarinhoDAO.class.getName());

    public boolean cadastrar(PassarinhoDTO passarinho) {
        String sql = "INSERT INTO passarinho (especie, cativeiro, idade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, passarinho.getEspecie());
            ps.setBoolean(2, passarinho.isCativeiro());
            ps.setInt(3, passarinho.getIdade());

            ps.execute();
            return true;

        } catch (SQLException e) {
            // 2. Registrando o erro ao invés de apenas falhar silenciosamente
            logger.log(Level.SEVERE, "Erro ao cadastrar o passarinho no banco de dados.", e);
            return false;
        }
    }

    public List<PassarinhoDTO> listarTodos() {
        String sql = "SELECT * FROM passarinho";
        List<PassarinhoDTO> listaPassarinhos = new ArrayList<>();

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PassarinhoDTO p = new PassarinhoDTO();
                p.setId(rs.getInt("id"));
                p.setEspecie(rs.getString("especie"));
                p.setCativeiro(rs.getBoolean("cativeiro"));
                p.setIdade(rs.getInt("idade"));

                listaPassarinhos.add(p);
            }

        } catch (SQLException e) {
            // Registrando o erro
            logger.log(Level.SEVERE, "Erro ao listar os passarinhos do banco de dados.", e);
        }

        return listaPassarinhos;
    }

    public boolean atualizar(PassarinhoDTO passarinho) {
        String sql = "UPDATE passarinho SET especie = ?, cativeiro = ?, idade = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, passarinho.getEspecie());
            ps.setBoolean(2, passarinho.isCativeiro());
            ps.setInt(3, passarinho.getIdade());
            ps.setInt(4, passarinho.getId());

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            // Registrando o erro
            logger.log(Level.SEVERE, "Erro ao atualizar o passarinho de ID: " + passarinho.getId(), e);
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM passarinho WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            // Registrando o erro
            logger.log(Level.SEVERE, "Erro ao deletar o passarinho de ID: " + id, e);
            return false;
        }
    }
}