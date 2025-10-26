package com.fiap.feedbacksystem.model.entity;

import com.fiap.feedbacksystem.model.enums.TipoFeedback;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "feedback_sequence", sequenceName = "feedback_sequence", allocationSize = 1)
    int id;

    int idUsuario;

    int idAula;

    int nota;

    String comentario;

    @Enumerated(EnumType.STRING)
    TipoFeedback tipoFeedback;

    @CreatedDate
    Date dataFeedback;
}
