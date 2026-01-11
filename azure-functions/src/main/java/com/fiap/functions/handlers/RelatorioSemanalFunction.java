package com.fiap.functions.handlers;

import com.fiap.functions.dto.RelatorioSemanalDTO;
import com.fiap.functions.repository.FeedbackRepository;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.util.Optional;
import java.util.logging.Level;

public class RelatorioSemanalFunction {

    private final FeedbackRepository repository = new FeedbackRepository();

    @FunctionName("relatorioSemanal")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS
            )
            HttpRequestMessage<Optional<String>> request,
            ExecutionContext context
    ) {

        context.getLogger().info("Gerando relatório semanal de feedbacks");

        try {
            RelatorioSemanalDTO relatorio = repository.buscarFeedbackSemanal();

            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(relatorio)
                    .build();

        } catch (Exception e) {
            context.getLogger().log(Level.SEVERE, "Erro ao gerar relatório semanal", e);
            context.getLogger().severe("Erro ao executar GET /api/relatorio");
            context.getLogger().severe(e.toString());
            e.printStackTrace();

            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao gerar relatório semanal")
                    .build();
        }
    }
}
