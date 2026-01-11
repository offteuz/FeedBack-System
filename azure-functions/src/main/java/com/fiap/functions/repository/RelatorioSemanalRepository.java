package com.fiap.functions.repository;

import com.fiap.functions.dto.FeedbackDTO;
import com.fiap.functions.dto.RelatorioSemanalDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class RelatorioSemanalRepository {

    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");

    public RelatorioSemanalDTO gerarRelatorioSemanal() {

        String sql = """
            SELECT 
                DATE(ultima_modificacao) AS dia, 
                COUNT(*) AS total 
            FROM feedback 
            WHERE criacao >= DATE_SUB(NOW(), INTERVAL 7 DAY) 
            GROUP BY DATE(ultima_modificacao);
        """;

        List<FeedbackDTO> feedbacks = new ArrayList<>();
        int totalUrgentes = 0;
        int somaNotas = 0;

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                int nota = rs.getInt("nota");
                String tipo = rs.getString("tipo_feedback");

                if ("URGENTE".equalsIgnoreCase(tipo)) {
                    totalUrgentes++;
                }

                somaNotas += nota;

                feedbacks.add(
                        new FeedbackDTO(
                                rs.getLong("id"),
                                rs.getString("comentario"),
                                nota,
                                tipo
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório semanal", e);
        }

        int totalFeedbacks = feedbacks.size();
        double mediaNotas = totalFeedbacks == 0
                ? 0
                : (double) somaNotas / totalFeedbacks;

        Map<String, Integer> feedbacksPorDia = new HashMap<>();

        return new RelatorioSemanalDTO(
                0,
                0,
                0.0,
                new HashMap<>()
        );

    }
}
