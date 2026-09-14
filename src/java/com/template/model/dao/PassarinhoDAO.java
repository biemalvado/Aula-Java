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

public class PassarinhoDAO
        implements InterfacePassarinhoDAO {

    private static final Logger logger =
            Logger.getLogger(
                    PassarinhoDAO.class.getName()
            );

    @Override
    public boolean cadastrar(
            PassarinhoDTO passarinho
    ) {

        String sql =
                "INSERT INTO passarinho " +
                        "(especie, cativeiro, idade) " +
                        "VALUES (?, ?, ?)";

        try (
                Connection conn =
                        ConexaoBD.conectar();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    passarinho.getEspecie()
            );

            ps.setBoolean(
                    2,
                    passarinho.isCativeiro()
            );

            ps.setInt(
                    3,
                    passarinho.getIdade()
            );

            int linhasAfetadas =
                    ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {

            logger.log(
                    Level.SEVERE,
                    "Erro ao cadastrar o passarinho.",
                    e
            );

            return false;
        }
    }

    @Override
    public List<PassarinhoDTO> listarTodos() {

        String sql =
                "SELECT * FROM passarinho ORDER BY id";

        List<PassarinhoDTO> lista =
                new ArrayList<>();

        try (
                Connection conn =
                        ConexaoBD.conectar();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                PassarinhoDTO passarinho =
                        new PassarinhoDTO(
                                rs.getInt("id"),
                                rs.getString("especie"),
                                rs.getBoolean("cativeiro"),
                                rs.getInt("idade")
                        );

                lista.add(passarinho);
            }

        } catch (SQLException e) {

            logger.log(
                    Level.SEVERE,
                    "Erro ao listar os passarinhos.",
                    e
            );
        }

        return lista;
    }

    @Override
    public boolean atualizar(
            PassarinhoDTO passarinho
    ) {

        String sql =
                "UPDATE passarinho " +
                        "SET especie = ?, " +
                        "cativeiro = ?, idade = ? " +
                        "WHERE id = ?";

        try (
                Connection conn =
                        ConexaoBD.conectar();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    passarinho.getEspecie()
            );

            ps.setBoolean(
                    2,
                    passarinho.isCativeiro()
            );

            ps.setInt(
                    3,
                    passarinho.getIdade()
            );

            ps.setInt(
                    4,
                    passarinho.getId()
            );

            int linhasAfetadas =
                    ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {

            logger.log(
                    Level.SEVERE,
                    "Erro ao atualizar o passarinho de ID: "
                            + passarinho.getId(),
                    e
            );

            return false;
        }
    }

    @Override
    public boolean deletar(int id) {

        String sql =
                "DELETE FROM passarinho WHERE id = ?";

        try (
                Connection conn =
                        ConexaoBD.conectar();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            int linhasAfetadas =
                    ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {

            logger.log(
                    Level.SEVERE,
                    "Erro ao deletar o passarinho de ID: "
                            + id,
                    e
            );

            return false;
        }
    }
}