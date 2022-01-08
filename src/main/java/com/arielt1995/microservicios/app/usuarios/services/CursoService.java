package com.arielt1995.microservicios.app.usuarios.services;

import com.arielt1995.microservicios.app.usuarios.models.entity.Curso;

public interface CursoService extends CommonService<Curso>{
    public Curso findCursoByAlumnoId(Long id);
}
