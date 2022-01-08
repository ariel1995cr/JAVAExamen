package com.arielt1995.microservicios.app.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.arielt1995.microservicios.app.usuarios.models.entity.Alumno;

public interface AlumnoService extends CommonService<Alumno> {

    public List<Alumno> findByNombreOrApellido(String term);
}
