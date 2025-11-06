package com.fiap.feedbacksystem.controller;

import com.fiap.feedbacksystem.model.dto.feedback.FeedbackRequestDTO;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO;
import com.fiap.feedbacksystem.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/feedbacks")
@Validated
@Tag(name = "Criar Feedback", description = "Criação de feedbacks")
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> criarFeedback(@Valid @RequestBody FeedbackRequestDTO dto) {
        logger.info("POST /api/feedbacks - payload: {}", dto);
        FeedbackResponseDTO response = feedbackService.create(dto);
        logger.debug("Feedback criado - response: {}", response);

        URI location = null;
        try {
            if (response != null) {
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
