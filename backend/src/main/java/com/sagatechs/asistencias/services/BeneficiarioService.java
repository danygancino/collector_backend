package com.sagatechs.asistencias.services;

import com.sagatechs.asistencias.dao.BeneficiarioDao;
import com.sagatechs.asistencias.dao.GrupoFamiliarDao;
import com.sagatechs.asistencias.model.Beneficiario;
import com.sagatechs.asistencias.model.GrupoFamiliar;
import com.sagatechs.asistencias.webServices.modelWeb.BeneficiarioWeb;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class BeneficiarioService {

    private final static Logger LOGGER = Logger.getLogger(BeneficiarioService.class);

    @Inject
    BeneficiarioDao beneficiarioDao;

    public Beneficiario save(Beneficiario beneficiario) {
        return this.beneficiarioDao.save(beneficiario);
    }

    public Beneficiario update(Beneficiario beneficiario) {
        return this.beneficiarioDao.update(beneficiario);
    }

    public List<BeneficiarioWeb> beneficiariosToBeneficiariosWeb(Set<Beneficiario> bens) {
        List<BeneficiarioWeb> r = new ArrayList<>();
        for (Beneficiario ben : bens) {
            r.add(this.beneficiarioToBeneficiarioWeb(ben));
        }
        return r;
    }

    public BeneficiarioWeb beneficiarioToBeneficiarioWeb(Beneficiario ben) {
        if (ben == null) {
            return null;
        }
        BeneficiarioWeb benw = new BeneficiarioWeb();
        benw.setId(ben.getId().toString());
        benw.setGrupoFamiliarId(ben.getGrupoFamiliar().getId().toString());
        benw.setGrupoFamiliarCode(ben.getGrupoFamiliar().getGrupoFamiliarId());
        benw.setUltimaFechaActualizacion(ben.getUltimaFechaActualizacion());
        benw.setNombres(ben.getNombres());
        benw.setNivelParentescoId(ben.getNivelParentesco() != null ? ben.getNivelParentesco().getId() : null);
        benw.setSexoId(ben.getSexo() != null ? ben.getSexo().getId() : null);
        benw.setFechaNacimiento(ben.getFechaNacimiento());
        benw.setEdad(ben.getEdad());
        benw.setGeneroId(ben.getGenero() != null ? ben.getGenero().getId() : null);
        benw.setLgbtiId(ben.getLgbti() != null ? ben.getLgbti().getId() : null);
        benw.setEtniaId(ben.getEtnia() != null ? ben.getEtnia().getId() : null);
        benw.setNacionalidadId(ben.getNacionalidad() != null ? ben.getNacionalidad().getId() : null);
        benw.setDiscapacidadId(ben.getDiscapacidad() != null ? ben.getDiscapacidad().getId() : null);
        benw.setFechaIngresoPais(ben.getFechaIngresoPais());
        benw.setTipoDocumentoId(ben.getTipoDocumento() != null ? ben.getTipoDocumento().getId() : null);
        benw.setNumeroDocumento(ben.getNumeroDocumento());
        benw.setEstatutoMigratorioId(ben.getEstatutoMigratorio() != null ? ben.getEstatutoMigratorio().getId() : null);
        benw.setSituacionMigratoriaId(ben.getSituacionMigratoria() != null ? ben.getSituacionMigratoria().getId() : null);
        benw.setMotivoSalidaPaisId(ben.getMotivoSalidaPais() != null ? ben.getMotivoSalidaPais().getId() : null);
        benw.setCiudadId(ben.getCiudad() != null ? ben.getCiudad().getId() : null);
        benw.setSector(ben.getSector());
        benw.setDireccion(ben.getDireccion());
        benw.setTelefono(ben.getTelefono());
        benw.setNivelEscolaridadId(ben.getNivelEscolaridad() != null ? ben.getNivelEscolaridad().getId() : null);
        benw.setProfesionesIds(ben.getProfesiones().stream().map(profesion -> profesion.getId()).collect(Collectors.toSet()));
        benw.setOficiosIds(ben.getOficios().stream().map(oficio -> oficio.getId()).collect(Collectors.toSet()));
        benw.setObservaciones(ben.getObservaciones());
        return benw;
    }


}
