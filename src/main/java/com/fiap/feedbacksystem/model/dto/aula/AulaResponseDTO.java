package com.fiap.feedbacksystem.model.dto.aula;

import com.fiap.feedbacksystem.model.dto.auditoria.AuditoriaResponseDTO;
import com.fiap.feedbacksystem.model.entity.Usuario;
import com.fiap.feedbacksystem.model.enums.TipoDisciplina;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AulaResponseDTO extends AuditoriaResponseDTO {

    private Integer id;
    private TipoDisciplina tipoDisciplina; // "Matemática", "Portugues" ou "História"
    private String descricao;
    private Usuario administrador;
}
