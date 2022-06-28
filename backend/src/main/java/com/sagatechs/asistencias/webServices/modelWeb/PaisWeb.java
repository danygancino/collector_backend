package com.sagatechs.asistencias.webServices.modelWeb;

import java.util.ArrayList;
import java.util.List;

public class PaisWeb extends CatalogWeb {

    public PaisWeb() {
    }

    public PaisWeb(CatalogWeb catalogWeb) {
        this.setId(catalogWeb.getId());
        this.setNombre(catalogWeb.getNombre());
        this.setState(catalogWeb.getState());
    }

    private List<ProvinciaWeb> provincias = new ArrayList<>();


    public List<ProvinciaWeb> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<ProvinciaWeb> provincias) {
        this.provincias = provincias;
    }

    public void addProvincia(ProvinciaWeb provinciasWeb){

        this.provincias.add(provinciasWeb);
    }
}
