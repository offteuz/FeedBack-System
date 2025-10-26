package com.fiap.feedbacksystem.model.entity;

import com.fiap.feedbacksystem.model.enums.TipoDisciplina;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "aula_sequence", sequenceName = "aula_sequence", allocationSize = 1)
    int id;

    @Enumerated(EnumType.STRING)
    TipoDisciplina tipoDisciplina;

    String descricao;

    @ManyToOne
    @JoinColumn(name = "administrador_id")
    Usuario administrador;
}
