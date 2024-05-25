package com.prog.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prog.persistence.entities.Tarea;
import com.prog.persistence.entities.enums.Estado;
import com.prog.services.dto.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@GetMapping
	public ResponseEntity<List<Tarea>> listAll() {
		return ResponseEntity.ok(this.tareaService.getTareas());
	}

	@GetMapping("/{idTarea}")
	public ResponseEntity<Tarea> findOne(@PathVariable int idTarea) {
		Optional<Tarea> tarea = this.tareaService.getTarea(idTarea);

		if (tarea.isPresent()) {
			return ResponseEntity.ok(tarea.get());
		}

		else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity<Tarea> create(@RequestBody Tarea tarea) {
		return new ResponseEntity<Tarea>(this.tareaService.crearTarea(tarea), HttpStatus.CREATED);
	}

	@PutMapping("/{idTarea}")
	public ResponseEntity<Tarea> update(@PathVariable int idTarea, @RequestBody Tarea tarea) {
		if (idTarea == tarea.getId()) {
			if (this.tareaService.getTarea(idTarea).isPresent()) {
				return ResponseEntity.ok(this.tareaService.actualizarTarea(tarea));
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{idTarea}")
	public ResponseEntity<Tarea> delete(@PathVariable int idTarea) {
		if (this.tareaService.borrarTarea(idTarea)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/empiezan")
	public ResponseEntity<List<Tarea>> empiezan(@RequestParam String titulo) {
		return ResponseEntity.ok(this.tareaService.getTareasEmpiezanPor(titulo));
	}

	@GetMapping("/mayores")
	public ResponseEntity<List<Tarea>> mayores(@RequestParam int idTarea) {
		return ResponseEntity.ok(this.tareaService.getTareasIdMayorQue(idTarea));
	}

	// tareas pendientes, en proceso y completadas
	@GetMapping("/estado")
	public ResponseEntity<List<Tarea>> estado(@RequestParam Estado estado) {
		return ResponseEntity.ok(this.tareaService.getTareasEstadoEs(estado));
	}

	// tareas vencidas
	@GetMapping("/estaVencida")
	public ResponseEntity<List<Tarea>> estaVencida() {
		return ResponseEntity.ok(this.tareaService.getTareasEstaVencida());
	}

	// tareas no vencidas
	@GetMapping("/noEstaVencida")
	public ResponseEntity<List<Tarea>> noEstaVencida() {
		return ResponseEntity.ok(this.tareaService.getTareasNoEstaVencida());
	}

	// tareas mediante su titulo con like
	@GetMapping("/titulo")
	public ResponseEntity<List<Tarea>> contieneTitulo(@RequestParam String titulo) {
		return ResponseEntity.ok(this.tareaService.getTareasMedianteTitulo(titulo));
	}

	// tareas mediante su titulo con %
	@GetMapping("/titulo2")
	public ResponseEntity<List<Tarea>> contieneTitulo2(@RequestParam String titulo) {
		return ResponseEntity.ok(this.tareaService.getTareasMedianteTitulo2(titulo));
	}
}
