package com.arielt1995.microservicios.app.usuarios.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.arielt1995.microservicios.app.usuarios.models.entity.Alumno;

import java.util.List;


public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

    @Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
    public List<Alumno> findByNombreOrApellido(String term);
}
