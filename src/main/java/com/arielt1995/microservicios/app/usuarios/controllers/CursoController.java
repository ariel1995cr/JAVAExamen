package com.arielt1995.microservicios.app.usuarios.controllers;

import com.arielt1995.microservicios.app.usuarios.models.entity.Alumno;
import com.arielt1995.microservicios.app.usuarios.models.entity.Curso;
import com.arielt1995.microservicios.app.usuarios.models.entity.Examen;
import com.arielt1995.microservicios.app.usuarios.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("curso")
public class CursoController extends CommonController<Curso, CursoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()){
			return this.validar(result);
		}
		Optional<Curso> o = this.service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso = o.get();

		dbCurso.setNombre(curso.getNombre());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);

		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Curso dbCurso = o.get();

		alumnos.forEach(a -> {
			dbCurso.addAlumno(a);
		});



		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);

		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Curso dbCurso = o.get();

		dbCurso.removeAlumno(alumno);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarAlumnoPorId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}

	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);

		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Curso dbCurso = o.get();

		examenes.forEach(e -> {
			dbCurso.addExamen(e);
		});

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);

		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Curso dbCurso = o.get();

		dbCurso.removeExamen(examen);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}


	
}
