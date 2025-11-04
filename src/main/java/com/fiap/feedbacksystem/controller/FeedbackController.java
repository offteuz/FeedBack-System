package com.fiap.feedbacksystem.controller;

import com.fiap.feedbacksystem.model.dto.feedback.FeedbackRequestDTO;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO;
import com.fiap.feedbacksystem.service.FeedbackService;
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
@RequestMapping("/api/feedbacks")
@Validated
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar feedback para uma aula (somente estudantes)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Feedback criado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.exception.ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - somente estudantes",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.exception.ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário ou aula não encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = com.fiap.feedbacksystem.exception.ErrorResponse.class)))
    })
    public ResponseEntity<FeedbackResponseDTO> criarFeedback(@Valid @RequestBody FeedbackRequestDTO dto) {
        logger.info("POST /api/feedbacks - payload: {}", dto);
        FeedbackResponseDTO response = feedbackService.create(dto);
        logger.debug("Feedback criado - response: {}", response);

        URI location = null;
        try {
            if (response != null ) {
                location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.getId())
                        .toUri();
            }
        } catch (Exception ex) {
            logger.debug("Não foi possível montar Location para feedback criado: {}", ex.getMessage());
        }

        return (location != null)
                ? ResponseEntity.created(location).body(response)
                : ResponseEntity.status(201).body(response);
    }
}
