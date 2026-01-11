package com.fiap.functions.repository;

import com.fiap.functions.dto.RelatorioDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RelatorioRepository {

    private static final String SQL_FILTRADO = """
                SELECT 
                    f.id AS id_feedback, 
                    f.comentario, 
                    f.nota, 
                    f.tipo_feedback, 
                    u.nome AS usuario,
                    u.email,
                    a.descricao AS aula,
                    a.tipo_disciplina
                FROM feedback f
                JOIN usuario u ON u.id = f.id_usuario
                JOIN aula a ON a.id = f.id_aula
                WHERE f.tipo_feedback = ? """;

    public List<RelatorioDTO> buscarPorTipo(String tipo) {

        List<RelatorioDTO> relatorio = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                System.getenv("DB_URL"),
                System.getenv("DB_USER"),
                System.getenv("DB_PASSWORD")
        );
             PreparedStatement stmt = conn.prepareStatement(SQL_FILTRADO)
        ) {

            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    relatorio.add(
                            new RelatorioDTO(
                                    rs.getLong("id_feedback"),
                                    rs.getString("comentario"),
                                    rs.getInt("nota"),
                                    rs.getString("tipo_feedback"),
                                    rs.getString("usuario"),
                                    rs.getString("email"),
                                    rs.getString("aula"),
                                    rs.getString("tipo_disciplina")
                            )
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar relatório filtrado", e);
        }

        return relatorio;
    }
}
