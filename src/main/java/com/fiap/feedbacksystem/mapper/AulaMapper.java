package com.fiap.feedbacksystem.mapper;

import com.fiap.feedbacksystem.model.dto.aula.AulaRequestDTO;
import com.fiap.feedbacksystem.model.dto.aula.AulaResponseDTO;
import com.fiap.feedbacksystem.model.entity.Aula;

public class AulaMapper {

    public static Aula toEntity(AulaRequestDTO dto) {
        Aula aula = new Aula();
        aula.setTipoDisciplina(dto.getTipoDisciplina());
        aula.setDescricao(dto.getDescricao());
        aula.setAdministrador(dto.getAdministrador());
        return aula;
    }

    public static AulaResponseDTO toResponseDTO(Aula entity) {
        AulaResponseDTO dto = new AulaResponseDTO();
        dto.setId(entity.getId());
        dto.setTipoDisciplina(entity.getTipoDisciplina());
        dto.setDescricao(entity.getDescricao());
        dto.setAdministrador(entity.getAdministrador());
        return dto;
    }
}
