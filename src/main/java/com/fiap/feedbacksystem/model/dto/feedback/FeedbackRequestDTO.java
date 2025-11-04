package com.fiap.feedbacksystem.model.dto.feedback;

import com.fiap.feedbacksystem.model.enums.TipoFeedback;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequestDTO {

    @NotNull(message = "idUsuario é obrigatório")
    private Integer idUsuario;

    @NotNull(message = "idAula é obrigatório")
    private Integer idAula;

    @NotNull(message = "nota é obrigatória")
    @Min(value = 1, message = "nota mínima é 1")
    @Max(value = 10, message = "nota máxima é 10")
    private Integer nota; // Nota de 1 a 10

    @NotBlank(message = "comentario é obrigatório")
    private String comentario;

    @NotNull(message = "tipoFeedback é obrigatório")
    private TipoFeedback tipoFeedback; // "URGENTE", "ELOGIO" ou "CRITICA"
}
