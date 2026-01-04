package com.fiap.functions.function;

import com.fiap.functions.dto.RelatorioSemanalDTO;
import com.fiap.functions.repository.FeedbackJdbcRepository;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RelatorioSemanalFunction {

    private final FeedbackJdbcRepository repository = new FeedbackJdbcRepository();

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
            RelatorioSemanalDTO relatorio = repository.gerarRelatorioSemanal();

            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(relatorio)
                    .build();

        } catch (Exception e) {
            context.getLogger().severe(e.getMessage());

            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao gerar relatório semanal")
                    .build();
        }
    }
}
