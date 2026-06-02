package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassarinhoDAO {

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
            System.err.println("Erro ao cadastrar passarinho no banco: " + e.getMessage());
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
            System.err.println("Erro ao buscar passarinhos: " + e.getMessage());
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
            System.err.println("Erro ao atualizar passarinho: " + e.getMessage());
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
            System.err.println("Erro ao deletar passarinho: " + e.getMessage());
            return false;
        }
    }
}