package com.arielt1995.microservicios.app.usuarios.services;

import com.arielt1995.microservicios.app.usuarios.models.entity.Curso;
import com.arielt1995.microservicios.app.usuarios.models.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }
}
