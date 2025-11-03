package com.fiap.feedbacksystem.mapper;

import com.fiap.feedbacksystem.model.dto.feedback.FeedbackRequestDTO;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO;
import com.fiap.feedbacksystem.model.entity.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    Feedback toEntity(FeedbackRequestDTO dto);

    FeedbackResponseDTO toResponseDTO(Feedback entity);
}