package com.ucentral.microservicio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

	@Autowired
	private EstudianteJpaRespository estudianteJpaRespository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = false)
	public EstudianteDTO registrarEstudiante(EstudianteDTO estudiante) {

		EstudianteEntity estudianteEntity = mapper.map(estudiante, EstudianteEntity.class);

		estudianteEntity = this.estudianteJpaRespository.save(estudianteEntity);

		return mapper.map(estudianteEntity, EstudianteDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<EstudianteDTO> obtenerEstudiantes(Pageable pageable) {

		Page<EstudianteEntity> page = this.estudianteJpaRespository.findAll(pageable);

		return page.map(estudiante -> mapper.map(estudiante, EstudianteDTO.class));
	}

}
