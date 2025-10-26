package com.fiap.feedbacksystem.service;

import com.fiap.feedbacksystem.mapper.AulaMapper;
import com.fiap.feedbacksystem.model.dto.aula.AulaRequestDTO;
import com.fiap.feedbacksystem.model.dto.aula.AulaResponseDTO;
import com.fiap.feedbacksystem.model.entity.Aula;
import com.fiap.feedbacksystem.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private AulaRepository repository;

    public AulaService(AulaRepository repository) {
        this.repository = repository;
    }

    public AulaResponseDTO create(AulaRequestDTO dto) {
        Aula entity = AulaMapper.toEntity(dto);
        Aula saved = repository.save(entity);
        return AulaMapper.toResponseDTO(saved);
    }

/*    public List<AulaResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(AulaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AulaResponseDTO findById(Integer id) {
        Aula entity = repository.findById(id).orElseThrow();
        return AulaMapper.toResponseDTO(entity);
    }*/
}