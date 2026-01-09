package com.fiap.functions.handlers;

import com.fiap.functions.service.RelatorioService;
import com.fiap.functions.dto.RelatorioSemanalDTO;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import java.util.Optional;

public class RelatorioFunction {

    @FunctionName("relatorio")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Gerando Relatório Semanal consolidado...");

        try {
            RelatorioService service = new RelatorioService();
            RelatorioSemanalDTO dados = service.gerarRelatorioSemanal(context.getLogger());

            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(dados)
                    .build();

        } catch (Exception e) {
            // Isso retornará o erro exato (ex: Firewall, SSL, User denied) para o seu navegador
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro detalhado: " + e.getMessage())
                    .build();
        }
    }
}
