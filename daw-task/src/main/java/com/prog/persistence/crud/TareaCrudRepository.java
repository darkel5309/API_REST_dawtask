package com.prog.persistence.crud;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prog.persistence.entities.Tarea;
import com.prog.persistence.entities.enums.Estado;

public interface TareaCrudRepository extends CrudRepository<Tarea, Integer> {

	List<Tarea> findByTituloStartingWith(String titulo);

	List<Tarea> findByIdGreaterThan(int id);

	// tareas pendientes, en proceso y completadas
	List<Tarea> findByEstadoEquals(Estado estado);

	// tareas vencidas
	List<Tarea> findByFechaVencimientoBefore(LocalDate fechaVencimiento);

	// tareas no vencidas
	List<Tarea> findByFechaVencimientoAfter(LocalDate fechaVencimiento);

	// tareas mediante su titulo con like
	List<Tarea> findByTituloLike(String titulo);

	// tareas mediante su titulo con %
	List<Tarea> findByTituloContaining(String titulo);
}
