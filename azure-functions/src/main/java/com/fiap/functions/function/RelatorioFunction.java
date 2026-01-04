package com.fiap.functions.function;

import com.fiap.functions.dto.RelatorioDTO;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.util.Optional;

public class RelatorioFunction {

    @FunctionName("relatorio")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS
            )
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context
    ) {

        context.getLogger().info("Gerando relatório geral de feedbacks");

        // Simulação de relatório
        RelatorioDTO relatorio = new RelatorioDTO(
                "GERAL",
                120,
                "2026-01"
        );

        return request
                .createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(relatorio)
                .build();
    }
}
