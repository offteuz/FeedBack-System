package com.fiap.feedbacksystem.model.dto.usuario;

import com.fiap.feedbacksystem.model.enums.TipoUsuario;
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
public class UsuarioResponseDTO {

    private Integer id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario; // "Estudante" ou "Administrador"
}
