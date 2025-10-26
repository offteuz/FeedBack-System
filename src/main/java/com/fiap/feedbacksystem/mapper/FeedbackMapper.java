package com.fiap.feedbacksystem.mapper;

import com.fiap.feedbacksystem.model.dto.feedback.FeedbackRequestDTO;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO;
import com.fiap.feedbacksystem.model.entity.Feedback;

public class FeedbackMapper {

    public static Feedback toEntity(FeedbackRequestDTO dto) {
        Feedback feedback = new Feedback();
        feedback.setIdUsuario(dto.getIdUsuario());
        feedback.setIdAula(dto.getIdAula());
        feedback.setNota(dto.getNota());
        feedback.setComentario(dto.getComentario());
        feedback.setTipoFeedback(dto.getTipoFeedback());
        return feedback;
    }

    public static FeedbackResponseDTO toResponseDTO(Feedback entity) {
        FeedbackResponseDTO dto = new FeedbackResponseDTO();
        dto.setId(entity.getId());
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setIdAula(entity.getIdAula());
        dto.setNota(entity.getNota());
        dto.setComentario(entity.getComentario());
        dto.setTipoFeedback(entity.getTipoFeedback());
        return dto;
    }
}