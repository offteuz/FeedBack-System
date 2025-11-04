package com.fiap.feedbacksystem.model.dto.aula;

import com.fiap.feedbacksystem.model.entity.Usuario;
import com.fiap.feedbacksystem.model.enums.TipoDisciplina;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaResponseDTO {

    private Integer id;
    private TipoDisciplina tipoDisciplina; // "Matemática", "Portugues" ou "História"
    private String descricao;
    private Usuario administrador;
}
