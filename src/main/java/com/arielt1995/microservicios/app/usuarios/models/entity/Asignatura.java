package com.arielt1995.microservicios.app.usuarios.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="asignaturas")
public class Asignatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@JsonIgnoreProperties(value = {"hijos"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Asignatura padre;

	@JsonIgnoreProperties(value = {"padre"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL)
	private List<Asignatura> hijos;

	public Asignatura(){
		this.hijos = new ArrayList<>();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof Asignatura)){
			return false;
		}

		Asignatura a = (Asignatura) obj;

		return this.id != null && this.id.equals(a.getId());
	}
}
