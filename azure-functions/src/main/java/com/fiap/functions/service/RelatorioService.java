package com.fiap.functions.service;

import com.fiap.functions.dto.RelatorioSemanalDTO;
import com.fiap.functions.repository.DatabaseConnection;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RelatorioService {

    public RelatorioSemanalDTO gerarRelatorioSemanal(Logger logger) throws SQLException {
        // Query que atende aos requisitos de Agregação do Enunciado
        String sql = """
            SELECT 
                COUNT(*) as total,
                SUM(CASE WHEN tipo_feedback = 'CRITICO' THEN 1 ELSE 0 END) as urgentes,
                AVG(nota) as media
            FROM feedback 
            WHERE data_envio >= DATE_SUB(NOW(), INTERVAL 7 DAY)
        """;

        try (
                Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                return new RelatorioSemanalDTO(
                        rs.getInt("total"),
                        rs.getInt("urgentes"),
                        rs.getDouble("media"),
                        new java.util.HashMap<String, Integer>()
                );
            }
        } catch (SQLException e) {
            logger.severe("Erro no SQL do Relatório: " + e.getMessage());
            throw e;
        }

        return new RelatorioSemanalDTO(
                0,
                0,
                0.0,
                new HashMap<String, Integer>()
        );
    }
}
