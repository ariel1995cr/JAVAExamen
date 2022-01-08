package com.arielt1995.microservicios.app.usuarios.services;

import com.arielt1995.microservicios.app.usuarios.models.entity.Asignatura;
import com.arielt1995.microservicios.app.usuarios.models.entity.Examen;
import com.arielt1995.microservicios.app.usuarios.models.repository.AsignaturaRepository;
import com.arielt1995.microservicios.app.usuarios.models.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return repository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAllAsignaturas() {
        return (List<Asignatura>) asignaturaRepository.findAll();
    }
}
