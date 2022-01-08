package com.arielt1995.microservicios.app.usuarios.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @JsonIgnoreProperties(value = {"preguntas"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_id")
    private Examen examen;


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Pregunta)){
            return false;
        }

        Pregunta p = (Pregunta) obj;

        return this.id != null && this.id.equals(p.getId());
    }
}
