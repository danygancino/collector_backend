package com.sagatechs.asistencias.webServices.modelWeb;

import com.sagatechs.asistencias.model.catalogs.TipoAyuda;

import java.util.ArrayList;
import java.util.List;

public class ComponenteWeb extends CatalogWeb {

    public ComponenteWeb() {
    }

    public ComponenteWeb(CatalogWeb catalogWeb) {
        this.setId(catalogWeb.getId());
        this.setNombre(catalogWeb.getNombre());
        this.setState(catalogWeb.getState());
    }

    private List<TipoAsistenciaWeb> tiposAsistencia = new ArrayList<>();

    public List<TipoAsistenciaWeb> getTiposAsistencia() {
        return tiposAsistencia;
    }

    public void setTiposAsistencia(List<TipoAsistenciaWeb> tiposAsistencia) {
        this.tiposAsistencia = tiposAsistencia;
    }

    public void addTipoAsistencia(TipoAsistenciaWeb tipoAsistenciaWeb){

        this.tiposAsistencia.add(tipoAsistenciaWeb);
    }
}
