package com.sagatechs.generics.persistence.model.audit;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@SuppressWarnings("JpaDataSourceORMInspection")
@Embeddable
public class Auditoria {

	@Column(name = "fecha_sincronizacion", nullable = false)
	private LocalDateTime fechaIngreso;

	@Column(name = "usuario_sincronizacion", length = 50, nullable = false)
	private String usuarioCreacion;

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
}
