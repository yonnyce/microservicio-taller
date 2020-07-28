package com.ucentral.microservicio;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteRestController {

	@Autowired
	private EstudianteService estudianteService;

	@PostMapping
	public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody @Valid EstudianteDTO estudiante) {

		estudiante = this.estudianteService.registrarEstudiante(estudiante);

		return ResponseEntity.ok(estudiante);

	}

	@GetMapping
	public ResponseEntity<Page<EstudianteDTO>> consultarEstudiants(@PageableDefault(sort = {
			"id" }, direction = Direction.DESC, size = 10, page = 0) Pageable pageable) { 

		Page<EstudianteDTO> page = this.estudianteService.obtenerEstudiantes(pageable);

		return ResponseEntity.ok(page);

	}

}
