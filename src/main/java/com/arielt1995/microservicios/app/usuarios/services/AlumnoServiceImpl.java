package com.arielt1995.microservicios.app.usuarios.services;

import org.springframework.stereotype.Service;
import com.arielt1995.microservicios.app.usuarios.models.entity.Alumno;
import com.arielt1995.microservicios.app.usuarios.models.repository.AlumnoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }
}
