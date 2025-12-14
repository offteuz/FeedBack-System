package com.fiap.feedbacksystem.service;

import com.fiap.feedbacksystem.exception.ResourceNotFoundException;
import com.fiap.feedbacksystem.mapper.AulaMapper;
import com.fiap.feedbacksystem.model.dto.aula.AulaRequestDTO;
import com.fiap.feedbacksystem.model.dto.aula.AulaResponseDTO;
import com.fiap.feedbacksystem.model.entity.Aula;
import com.fiap.feedbacksystem.model.entity.Usuario;
import com.fiap.feedbacksystem.repository.AulaRepository;
import com.fiap.feedbacksystem.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AulaService {

    private static final Logger logger = LoggerFactory.getLogger(AulaService.class);

    private final AulaRepository repository;
    private final AulaMapper mapper;
    private final UsuarioRepository usuarioRepository;

    public AulaService(AulaRepository repository, AulaMapper mapper, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public AulaResponseDTO create(AulaRequestDTO dto) {
        logger.info("Criando aula - tipo={} administradorId={}", dto.getTipoDisciplina(), dto.getAdministradorId());

        Aula entity = mapper.toEntity(dto); // administrador ignorado pelo mapper

        // resolve administrador por id e seta a entidade
        Integer adminId = dto.getAdministradorId();
        Usuario administrador = usuarioRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado com id: " + adminId));

        // valida que o usuário é administrador
        if (administrador.getTipoUsuario() == null || administrador.getTipoUsuario() != com.fiap.feedbacksystem.model.enums.TipoUsuario.ADMINISTRADOR) {
            logger.warn("Tentativa de criar aula por usuário não administrador: id={}", adminId);
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.FORBIDDEN,
                    "Apenas administradores podem cadastrar aulas"
            );
        }

        entity.setAdministrador(administrador);

        Aula saved = repository.save(entity);
        AulaResponseDTO response = mapper.toResponseDTO(saved);
        logger.debug("Aula criada com id={}", response.getId());
        return response;
    }
}