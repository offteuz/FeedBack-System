package com.fiap.feedbacksystem.model.dto.usuario;

import com.fiap.feedbacksystem.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO implements Serializable {

    @NotBlank(message = "nome é obrigatório")
    @Size(min = 2, max = 100, message = "nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "email é obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotBlank(message = "senha é obrigatório")
    @Size(min = 5, max = 15, message = "senha deve ter entre 5 e 15 caracteres")
    private String senha;

    @NotNull(message = "tipoUsuario é obrigatório")
    private TipoUsuario tipoUsuario; // ESTUDANTE ou ADMINISTRADOR
}
