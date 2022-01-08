package com.arielt1995.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.arielt1995.microservicios.app.usuarios.models.entity.Alumno;
import com.arielt1995.microservicios.app.usuarios.services.AlumnoService;

import javax.validation.Valid;

@RestController
@RequestMapping("alumno")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result,@PathVariable Long id){
		if(result.hasErrors()){
			return this.validar(result);
		}

		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		
		alumnoDb.setNombre(alumno.getNombre());
		
		alumnoDb.setApellido(alumno.getApellido());
		
		alumnoDb.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok().body(service.findByNombreOrApellido(term));
	}
	
}
