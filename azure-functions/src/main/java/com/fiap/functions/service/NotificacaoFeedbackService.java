package com.fiap.functions.service;

import com.fiap.functions.repository.DatabaseConnection;
import java.sql.*;
import java.util.logging.Logger;

public class NotificacaoFeedbackService {

    public void processarNotificacoes(Logger logger) throws SQLException {
        // Busca apenas feedbacks críticos (nota baixa ou tipo urgente)
        String sql = """
            SELECT comentario, tipo_feedback 
            FROM feedback 
            WHERE nota <= 3 OR tipo_feedback = 'CRITICO'
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String comentario = rs.getString("comentario");
                // Regra de negócio: Notificação automática para administradores
                enviarEmailAdmin(comentario, logger);
            }
        }
    }

    private void enviarEmailAdmin(String texto, Logger logger) {
        // No vídeo, você explica que aqui integraria com SendGrid ou JavaMail
        logger.warning("📩 NOTIFICAÇÃO ENVIADA AO ADMIN: Feedback crítico recebido: " + texto);
    }
}
