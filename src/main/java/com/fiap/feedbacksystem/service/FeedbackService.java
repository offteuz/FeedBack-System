package com.fiap.feedbacksystem.service;

import com.fiap.feedbacksystem.exception.ResourceNotFoundException;
import com.fiap.feedbacksystem.mapper.FeedbackMapper;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackRequestDTO;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO;
import com.fiap.feedbacksystem.model.entity.Feedback;
import com.fiap.feedbacksystem.repository.AulaRepository;
import com.fiap.feedbacksystem.repository.FeedbackRepository;
import com.fiap.feedbacksystem.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    private final FeedbackRepository repository;
    private final FeedbackMapper mapper;
    private final UsuarioRepository usuarioRepository;
    private final AulaRepository aulaRepository;

    public FeedbackService(FeedbackRepository repository, FeedbackMapper mapper, UsuarioRepository usuarioRepository, AulaRepository aulaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
        this.aulaRepository = aulaRepository;
    }

    @Transactional
    public FeedbackResponseDTO create(FeedbackRequestDTO dto) {
        Objects.requireNonNull(dto, "FeedbackRequestDTO não pode ser nulo");
        logger.info("Criando feedback - usuarioId={} aulaId={}", dto.getIdUsuario(), dto.getIdAula());

        // valida existência de usuário e que é do tipo ESTUDANTE
        var usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado: {}", dto.getIdUsuario());
                    return new ResourceNotFoundException("Usuário não encontrado com id: " + dto.getIdUsuario());
                });

        if (usuario.getTipoUsuario() == null || usuario.getTipoUsuario() != com.fiap.feedbacksystem.model.enums.TipoUsuario.ESTUDANTE) {
            logger.warn("Usuário não autorizado a criar feedback (não é estudante): id={}", dto.getIdUsuario());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas estudantes podem enviar feedback");
        }

        if (!aulaRepository.existsById(dto.getIdAula())) {
            logger.warn("Aula não encontrada: {}", dto.getIdAula());
            throw new ResourceNotFoundException("Aula não encontrada com id: " + dto.getIdAula());
        }

        Feedback entity = mapper.toEntity(dto);
        Feedback saved = repository.save(entity);
        FeedbackResponseDTO response = mapper.toResponseDTO(saved);

        logger.debug("Feedback criado com id={}", response.getId());
        return response;
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponseDTO> findAll() {
        logger.debug("Buscando todos feedbacks");
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FeedbackResponseDTO findById(Integer id) {
        logger.debug("Buscando feedback por id={}", id);
        Feedback entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }
}