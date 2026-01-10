package com.fiap.functions.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificacaoFeedbackFunction {

    @FunctionName("notificacaoFeedback")
    public void run(
            @TimerTrigger(
                    name = "notificacaoTimer",
                    schedule = "0 */1 * * * *"
            )
            String timerInfo,
            final ExecutionContext context
    ) {

        Logger logger = context.getLogger();

        logger.info("🔔 Iniciando verificação de feedbacks críticos");
        logger.info("Horário de execução: " + LocalDateTime.now());

        try {
            // 🔜 Aqui entra a consulta ao banco depois
            // Por enquanto, simulação da regra de negócio

            logger.warning("⚠️ Feedback CRITICA encontrado");
            logger.severe("🚨 Feedback URGENTE encontrado");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao processar notificações de feedback", e);
        }

        logger.info("✅ Finalizando execução da Function de Notificação");
    }
}
