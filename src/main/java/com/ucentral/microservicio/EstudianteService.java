package com.ucentral.microservicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstudianteService {

	EstudianteDTO registrarEstudiante(EstudianteDTO estudiante);

	Page<EstudianteDTO> obtenerEstudiantes(Pageable pageable);

}
