package com.arielt1995.microservicios.app.usuarios.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;

    @JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
    @OneToMany(mappedBy = "examen",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Asignatura asignatura;

    public Examen(){
        this.preguntas = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public void setPreguntas(List<Pregunta> preguntas){
        this.preguntas.clear();
        preguntas.forEach(this::addPregunta);
    }

    public void addPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
        pregunta.setExamen(this);
    }

    public void removePregunta(Pregunta pregunta){
        this.preguntas.remove(pregunta);
        pregunta.setExamen(null);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Examen)){
            return false;
        }

        Examen e = (Examen) obj;

        return this.id != null && this.id.equals(e.getId());
    }
}
