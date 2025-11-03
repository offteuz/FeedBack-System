package com.fiap.feedbacksystem.mapper;

import com.fiap.feedbacksystem.model.dto.aula.AulaRequestDTO;
import com.fiap.feedbacksystem.model.dto.aula.AulaResponseDTO;
import com.fiap.feedbacksystem.model.entity.Aula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AulaMapper {

    @Mapping(target = "administrador", ignore = true)
    Aula toEntity(AulaRequestDTO dto);

    AulaResponseDTO toResponseDTO(Aula entity);
}
