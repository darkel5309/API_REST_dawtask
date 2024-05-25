package com.prog.persistence.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prog.persistence.crud.TareaCrudRepository;
import com.prog.persistence.entities.Tarea;
import com.prog.persistence.entities.enums.Estado;

@Repository
public class TareaRepository {

	@Autowired
	private TareaCrudRepository tareaCrudRepository;

	public List<Tarea> findAll() {
		return (List<Tarea>) this.tareaCrudRepository.findAll();
	}

	public Optional<Tarea> findById(int idTarea) {
		return this.tareaCrudRepository.findById(idTarea);
	}

	public Tarea save(Tarea tarea) {
		return this.tareaCrudRepository.save(tarea);
	}

	public void deleteById(int idTarea) {
		this.tareaCrudRepository.deleteById(idTarea);
	}

	public List<Tarea> findByTituloStartingWith(String titulo) {
		return this.tareaCrudRepository.findByTituloStartingWith(titulo);
	}

	public List<Tarea> findByIdGreaterThan(int id) {
		return this.tareaCrudRepository.findByIdGreaterThan(id);
	}

	// tareas pendientes, en proceso y completadas
	public List<Tarea> findByEstadoEquals(Estado estado) {
		return this.tareaCrudRepository.findByEstadoEquals(estado);
	}

	// tareas vencidas
	public List<Tarea> findByFechaVencimientoBefore(LocalDate fechaVencimiento) {
		return this.tareaCrudRepository.findByFechaVencimientoBefore(fechaVencimiento);
	}

	// tareas no vencidas
	public List<Tarea> findByFechaVencimientoAfter(LocalDate fechaVencimiento) {
		return this.tareaCrudRepository.findByFechaVencimientoAfter(fechaVencimiento);
	}

	// tareas mediante su titulo con like
	public List<Tarea> findByTituloLike(String titulo) {
		return this.tareaCrudRepository.findByTituloLike(titulo);
	}

	// tareas mediante su titulo con %
	public List<Tarea> findByTituloContaining(String titulo) {
		return this.tareaCrudRepository.findByTituloContaining(titulo);
	}
}
