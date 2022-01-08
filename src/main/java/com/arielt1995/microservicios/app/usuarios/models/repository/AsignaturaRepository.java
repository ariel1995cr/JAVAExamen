package com.arielt1995.microservicios.app.usuarios.models.repository;

import com.arielt1995.microservicios.app.usuarios.models.entity.Asignatura;
import com.arielt1995.microservicios.app.usuarios.models.entity.Examen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {

    @Query("select e from Examen e where e.nombre like %?1%")
    public List<Asignatura> findByNombre(String term);
}
