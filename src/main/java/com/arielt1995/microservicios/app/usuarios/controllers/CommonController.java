package com.arielt1995.microservicios.app.usuarios.controllers;

import com.arielt1995.microservicios.app.usuarios.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonController<E, S extends CommonService<E>> {

	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<E> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result){
		if(result.hasErrors()){
			return validar(result);
		}
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}

	
	@DeleteMapping
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(fieldError -> {
			errores.put(fieldError.getField(), "El campo " + fieldError.getField() + " " +fieldError.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errores);
	}
	
}
