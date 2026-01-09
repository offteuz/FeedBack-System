package com.fiap.functions.repository;

import com.fiap.functions.dto.FeedbackDTO;
import com.fiap.functions.dto.RelatorioSemanalDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FeedbackRepository {

    private static final String SQL =
            "SELECT id, comentario, nota, tipo_feedback " +
            "FROM feedback " +
            "WHERE tipo_feedback = 'URGENTE' OR nota <= 5";

    public List<FeedbackDTO> buscarFeedbacksCriticos() {

        List<FeedbackDTO> feedbacks = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(
                    System.getenv("DB_URL"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
                );
                PreparedStatement stmt = conn.prepareStatement(SQL);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                feedbacks.add(
                        new FeedbackDTO(
                                rs.getLong("id"),
                                rs.getString("comentario"),
                                rs.getInt("nota"),
                                rs.getString("tipo_feedback")
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar feedbacks críticos", e);
        }

        return feedbacks;
    }

    public RelatorioSemanalDTO buscarFeedbackSemanal() {

        String sql =
                "SELECT id, comentario, nota, tipo_feedback " +
                        "FROM feedback";

        List<FeedbackDTO> feedbacks = new ArrayList<>();
        int totalUrgentes = 0;
        int somaNotas = 0;

        try (
                Connection conn = DriverManager.getConnection(
                        System.getenv("DB_URL"),
                        System.getenv("DB_USER"),
                        System.getenv("DB_PASSWORD")
                );
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

        double media = feedbacks.isEmpty()
                ? 0
                : (double) somaNotas / feedbacks.size();

        Map<String, Integer> feedbacksPorDia = Map.of();
        return new RelatorioSemanalDTO(
                feedbacks.size(),
                totalUrgentes,
                media,
                feedbacksPorDia
                
        );
    }

}
