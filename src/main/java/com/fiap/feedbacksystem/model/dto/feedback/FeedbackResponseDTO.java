package com.fiap.feedbacksystem.model.dto.feedback;

import com.fiap.feedbacksystem.model.dto.auditoria.AuditoriaResponseDTO;
import com.fiap.feedbacksystem.model.enums.TipoFeedback;
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
public class FeedbackResponseDTO extends AuditoriaResponseDTO {

    private Integer id;
    private Integer idUsuario;
    private Integer idAula;
    private Integer nota; // Nota de 1 a 10
    private String comentario;
    private TipoFeedback tipoFeedback; // "URGENTE", "ELOGIO" ou "CRITICA"
}
