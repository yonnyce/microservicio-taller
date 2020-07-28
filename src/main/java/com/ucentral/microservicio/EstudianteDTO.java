package com.ucentral.microservicio;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EstudianteDTO {

	private Integer id;

	@NotNull
	@Size(min = 2, max = 25)
	private String nombres;

	@NotNull
	@Size(min = 2, max = 35)
	private String apellidos;

	@NotNull
	@Size(min = 2, max = 35)
	private String documento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
