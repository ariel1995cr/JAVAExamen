package com.arielt1995.microservicios.app.usuarios.controllers;

import com.arielt1995.microservicios.app.usuarios.models.entity.Examen;
import com.arielt1995.microservicios.app.usuarios.models.entity.Pregunta;
import com.arielt1995.microservicios.app.usuarios.services.ExamenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("examen")
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()){
			return this.validar(result);
		}

		Optional<Examen> o = service.findById(id);

		if(!o.isPresent()){
			return ResponseEntity.notFound().build();
		}

		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());

		examenDb.getPreguntas()
				.stream()
				.filter(pregunta -> !examen.getPreguntas().contains(pregunta))
				.forEach(examenDb::removePregunta);

		examenDb.setPreguntas(examen.getPreguntas());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}

	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
