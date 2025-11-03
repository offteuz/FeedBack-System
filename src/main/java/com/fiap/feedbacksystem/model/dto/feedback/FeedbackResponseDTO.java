package com.fiap.feedbacksystem.model.dto.feedback;

import com.fiap.feedbacksystem.model.enums.TipoFeedback;
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
public class FeedbackResponseDTO {

    private Integer id;
    private Integer idUsuario;
    private Integer idAula;
    private Integer nota; // Nota de 1 a 10
    private String comentario;
    private TipoFeedback tipoFeedback; // "URGENTE", "ELOGIO" ou "CRITICA"
}
