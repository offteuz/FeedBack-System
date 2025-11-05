package com.fiap.feedbacksystem.config.swagger;

import com.fiap.feedbacksystem.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Esta é uma meta-anotação que agrupa todas as respostas de erro
 * padrão da API (400, 403, 404, 422) em um único lugar.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {

        @ApiResponse(responseCode = "400", description = "Dados Inválidos",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class))),

        @ApiResponse(responseCode = "403", description = "Acesso Negado (Forbidden)",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class))),

        @ApiResponse(responseCode = "404", description = "Recurso Não Encontrado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class))),

        @ApiResponse(responseCode = "422", description = "Regra de Negócio Violada",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class)))
})
public @interface ApiStandardErrorResponses {
}