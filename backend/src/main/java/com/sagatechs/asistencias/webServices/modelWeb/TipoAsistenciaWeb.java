package com.sagatechs.asistencias.webServices.modelWeb;

import java.util.ArrayList;
import java.util.List;

public class TipoAsistenciaWeb extends CatalogWeb {

    public TipoAsistenciaWeb() {
    }

    public TipoAsistenciaWeb(CatalogWeb catalogWeb) {
        this.setId(catalogWeb.getId());
        this.setNombre(catalogWeb.getNombre());
        this.setState(catalogWeb.getState());
    }

    private ComponenteWeb componente;

    public ComponenteWeb getComponente() {
        return componente;
    }

    public void setComponente(ComponenteWeb componenteWeb) {
        this.componente = componenteWeb;
    }
}
