package com.fiap.feedbacksystem.service;

import com.fiap.feedbacksystem.mapper.FeedbackMapper;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackRequestDTO;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO;
import com.fiap.feedbacksystem.model.entity.Feedback;
import com.fiap.feedbacksystem.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public FeedbackResponseDTO create(FeedbackRequestDTO dto) {
        Feedback entity = FeedbackMapper.toEntity(dto);
        Feedback saved = repository.save(entity);
        return FeedbackMapper.toResponseDTO(saved);
    }

/*    public List<FeedbackResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(FeedbackMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FeedbackResponseDTO findById(Integer id) {
        Feedback entity = repository.findById(id).orElseThrow();
        return FeedbackMapper.toResponseDTO(entity);
    }*/
}