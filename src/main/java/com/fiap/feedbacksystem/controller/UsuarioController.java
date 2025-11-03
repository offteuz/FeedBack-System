package com.fiap.feedbacksystem.controller;

import com.fiap.feedbacksystem.model.dto.usuario.UsuarioRequestDTO;
import com.fiap.feedbacksystem.model.dto.usuario.UsuarioResponseDTO;
import com.fiap.feedbacksystem.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioRequestDTO dto) {
        logger.info("POST /api/usuarios - payload: {}", dto);
        try {
            UsuarioResponseDTO response = usuarioService.create(dto);
            logger.debug("Usuario criado - response: {}", response);

            URI location = null;
            try {
                if (response != null) {
                    Object id = response.getId();
                    if (id != null) {
                        location = ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(id)
                                .toUri();
                    }
                }
            } catch (Exception ex) {
                logger.debug("Não foi possível montar Location para usuário criado: {}", ex.getMessage());
            }

            return (location != null)
                    ? ResponseEntity.created(location).body(response)
                    : ResponseEntity.status(201).body(response);

        } catch (IllegalArgumentException ex) {
            logger.warn("Dados inválidos ao criar usuário: {}", ex.getMessage());
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Dados inválidos", ex);
        } catch (Exception ex) {
            logger.error("Erro ao criar usuário: {}", ex.getMessage(), ex);
            throw new ResponseStatusException(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", ex);
        }
    }
}
