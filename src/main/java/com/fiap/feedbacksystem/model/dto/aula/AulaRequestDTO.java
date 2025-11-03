package com.fiap.feedbacksystem.model.dto.aula;

import com.fiap.feedbacksystem.model.enums.TipoDisciplina;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaRequestDTO {

    @NotNull(message = "tipoDisciplina é obrigatório")
    private TipoDisciplina tipoDisciplina; // "Matemática", "Portugues" ou "História"

    @NotBlank(message = "descricao é obrigatória")
    private String descricao;

    @NotNull(message = "administradorId é obrigatório")
    private Integer administradorId;
}
