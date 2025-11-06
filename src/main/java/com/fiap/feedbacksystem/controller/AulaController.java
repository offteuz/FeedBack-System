package com.fiap.feedbacksystem.controller;

import com.fiap.feedbacksystem.model.dto.aula.AulaRequestDTO;
import com.fiap.feedbacksystem.model.dto.aula.AulaResponseDTO;
import com.fiap.feedbacksystem.service.AulaService;
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
@RequestMapping("/api/aulas")
@Validated
@Tag(name = "Criar Aula", description = "Criação de aulas")
public class AulaController {

    private static final Logger logger = LoggerFactory.getLogger(AulaController.class);

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
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