package com.fiap.functions.repository;

import com.fiap.functions.dto.RelatorioSemanalDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeedbackJdbcRepository {

    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");

    public RelatorioSemanalDTO gerarRelatorioSemanal() {

        String sql = """
            SELECT 
                COUNT(*) AS total,
                COALESCE(AVG(avaliacao), 0) AS media
            FROM feedback
            WHERE data_criacao >= CURRENT_DATE - INTERVAL '7 days'
        """;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                long total = rs.getLong("total");
                double media = rs.getDouble("media");

                return new RelatorioSemanalDTO(
                        "Últimos 7 dias",
                        total,
                        media
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório semanal", e);
        }

        return new RelatorioSemanalDTO("Últimos 7 dias", 0, 0);
    }
}
