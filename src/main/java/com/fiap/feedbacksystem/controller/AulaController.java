package com.fiap.feedbacksystem.controller;

import com.fiap.feedbacksystem.model.dto.aula.AulaRequestDTO;
import com.fiap.feedbacksystem.model.dto.aula.AulaResponseDTO;
import com.fiap.feedbacksystem.service.AulaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;


@RestController
@RequestMapping("/api/aulas")
@Validated
public class AulaController {

    private static final Logger logger = LoggerFactory.getLogger(AulaController.class);

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping(
            path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Criar nova aula (somente administradores)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Aula criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.model.dto.aula.AulaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.exception.ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - usuário não é administrador",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.exception.ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.exception.ErrorResponse.class))),
            @ApiResponse(responseCode = "422", description = "Regras de negócio",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.exception.ErrorResponse.class)))
    })
     public ResponseEntity<AulaResponseDTO> criarAula(@Valid @RequestBody AulaRequestDTO dto) {
        logger.info("POST /api/aulas - payload: {}", dto);
        AulaResponseDTO response = aulaService.create(dto);
        logger.debug("Aula criada - response: {}", response);

        URI location = null;
        try {
            if (response != null && response.getId() != null) {
                location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.getId())
                        .toUri();
            }
        } catch (Exception ex) {
            logger.debug("Não foi possível montar Location para aula criada: {}", ex.getMessage());
        }

        return (location != null)
                ? ResponseEntity.created(location).body(response)
                : ResponseEntity.status(201).body(response);
    }
}