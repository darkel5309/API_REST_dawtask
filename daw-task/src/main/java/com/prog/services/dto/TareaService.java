package com.prog.services.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.persistence.entities.Tarea;
import com.prog.persistence.entities.enums.Estado;
import com.prog.persistence.repositories.TareaRepository;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;

	public List<Tarea> getTareas() {
		return this.tareaRepository.findAll();
	}

	public Optional<Tarea> getTarea(int idTarea) {
		return this.tareaRepository.findById(idTarea);
	}

	public Tarea crearTarea(Tarea tarea) {
		tarea.setEstado(Estado.PENDIENTE);
		tarea.setFechaCreacion(LocalDate.now());

		return this.tareaRepository.save(tarea);
	}

	public Tarea actualizarTarea(Tarea tarea) {
		return this.tareaRepository.save(tarea);
	}

	public boolean borrarTarea(int idTarea) {
		boolean result = false;

		if (this.tareaRepository.findById(idTarea).isPresent()) {
			this.tareaRepository.deleteById(idTarea);
			result = true;
		}

		return result;
	}

	public List<Tarea> getTareasEmpiezanPor(String titulo) {
		return this.tareaRepository.findByTituloStartingWith(titulo);
	}

	public List<Tarea> getTareasIdMayorQue(int idTarea) {
		return this.tareaRepository.findByIdGreaterThan(idTarea);
	}

	// tareas pendientes, en proceso y completadas
	public List<Tarea> getTareasEstadoEs(Estado estado) {
		return this.tareaRepository.findByEstadoEquals(estado);
	}

	// tareas vencidas
	public List<Tarea> getTareasEstaVencida() {
		return this.tareaRepository.findByFechaVencimientoBefore(LocalDate.now());
	}

	// tareas no vencidas
	public List<Tarea> getTareasNoEstaVencida() {
		return this.tareaRepository.findByFechaVencimientoAfter(LocalDate.now());
	}

	// tareas mediante su titulo con like
	public List<Tarea> getTareasMedianteTitulo(String titulo) {
		return this.tareaRepository.findByTituloLike(titulo);
	}

	// tareas mediante su titulo con %
	public List<Tarea> getTareasMedianteTitulo2(String titulo) {
		return this.tareaRepository.findByTituloContaining(titulo);
	}
}
