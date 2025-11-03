package com.fiap.feedbacksystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.feedbacksystem.model.dto.usuario.UsuarioRequestDTO;
import com.fiap.feedbacksystem.model.enums.TipoUsuario;
import com.fiap.feedbacksystem.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    void criarUsuario_deveRetornar201_eLocation() throws Exception {
        UsuarioRequestDTO request = UsuarioRequestDTO.builder()
                .nome("Maria Souza")
                .email("maria.souza@example.com")
                .tipoUsuario(TipoUsuario.ESTUDANTE)
                .build();

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/usuarios/")))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nome").value("Maria Souza"))
                .andExpect(jsonPath("$.email").value("maria.souza@example.com"));
    }

    @Test
    void criarUsuario_comEmailDuplicado_deveRetornar409() throws Exception {
        UsuarioRequestDTO request = UsuarioRequestDTO.builder()
                .nome("Carlos")
                .email("carlos@example.com")
                .tipoUsuario(TipoUsuario.ADMINISTRADOR)
                .build();

        // cria o primeiro
        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // tenta criar novamente com mesmo email
        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message", containsString("Email já cadastrado")));
    }

    @Test
    void criarUsuario_comPayloadInvalido_deveRetornar400_comErros() throws Exception {
        // nome em branco e email ausente
        String payload = "{ \"nome\": \"\", \"tipoUsuario\": \"ESTUDANTE\" }";

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]", containsString("nome")));
    }
}

