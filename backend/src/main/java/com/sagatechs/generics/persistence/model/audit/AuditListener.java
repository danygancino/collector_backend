package com.sagatechs.generics.persistence.model.audit;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.security.enterprise.SecurityContext;
import java.time.LocalDateTime;

public class AuditListener {
	@SuppressWarnings("CdiInjectionPointsInspection")
	@Inject
	private SecurityContext securityContext;

	@PrePersist
	public void setFechaCreacion(Auditable auditable) {
		Auditoria auditoria = auditable.getAuditoria();
		if (auditoria == null) {
			auditoria = new Auditoria();
			auditable.setAuditoria(auditoria);
		}

		LocalDateTime datetime=LocalDateTime.now();
		String user = getUser();
		auditoria.setFechaIngreso(datetime);
		auditoria.setUsuarioCreacion(user);
	}

	@PreUpdate
	public void setUpdatedOn(Auditable auditable) {
		Auditoria auditoria = auditable.getAuditoria();

		LocalDateTime datetime=LocalDateTime.now();
		String user = getUser();
		auditoria.setFechaIngreso(datetime);
		auditoria.setUsuarioCreacion(user);
	}

	public String getUser() {
		if (this.securityContext != null && this.securityContext.getCallerPrincipal() != null) {
			return this.securityContext.getCallerPrincipal().getName();
		}

		return "desarrollo";
	}
}