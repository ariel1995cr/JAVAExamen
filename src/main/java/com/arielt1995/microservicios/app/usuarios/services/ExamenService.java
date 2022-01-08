package com.arielt1995.microservicios.app.usuarios.services;

import com.arielt1995.microservicios.app.usuarios.models.entity.Alumno;
import com.arielt1995.microservicios.app.usuarios.models.entity.Asignatura;
import com.arielt1995.microservicios.app.usuarios.models.entity.Examen;

import java.util.List;

public interface ExamenService extends CommonService<Examen> {
    public List<Examen> findByNombre(String term);

    public List<Asignatura> findAllAsignaturas();
}
