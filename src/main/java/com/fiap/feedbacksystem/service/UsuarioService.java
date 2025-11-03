package com.fiap.feedbacksystem.service;

import com.fiap.feedbacksystem.exception.EmailAlreadyExistsException;
import com.fiap.feedbacksystem.exception.ResourceNotFoundException;
import com.fiap.feedbacksystem.mapper.UsuarioMapper;
import com.fiap.feedbacksystem.model.dto.usuario.UsuarioRequestDTO;
import com.fiap.feedbacksystem.model.dto.usuario.UsuarioResponseDTO;
import com.fiap.feedbacksystem.model.entity.Usuario;
import com.fiap.feedbacksystem.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        Objects.requireNonNull(dto, "UsuarioRequestDTO não pode ser nulo");
        logger.info("Criando usuário - email={}", dto.getEmail());

        if (dto.getEmail() != null && repository.existsByEmail(dto.getEmail())) {
            logger.warn("Falha ao criar usuário - email já cadastrado: {}", dto.getEmail());
            throw new EmailAlreadyExistsException("Email já cadastrado: " + dto.getEmail());
        }

        Usuario entity = mapper.toEntity(dto);
        Usuario saved = repository.save(entity);
        UsuarioResponseDTO response = mapper.toResponseDTO(saved);
        logger.debug("Usuário criado com id={}", response.getId());
        return response;
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findAll() {
        logger.debug("Buscando todos os usuários");
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(Integer id) {
        logger.debug("Buscando usuário por id={}", id);
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    // ... possíveis métodos de update e delete podem ser adicionados seguindo mesmas práticas ...
}
