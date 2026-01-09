package com.fiap.functions.handlers;

import com.fiap.functions.service.NotificacaoFeedbackService;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificacaoFunction {

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
        logger.info("Iniciando verificação de feedbacks críticos");
        logger.info("Horário: " + LocalDateTime.now());

        try {
            new NotificacaoFeedbackService().processarNotificacoes(logger);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao processar notificações", e);
        }

        logger.info("Execução finalizada");
    }
}
