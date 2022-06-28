package com.sagatechs.asistencias.webServices.modelWeb;

import java.util.ArrayList;
import java.util.List;

public class ProvinciaWeb extends CatalogWeb {

    public ProvinciaWeb() {
    }

    public ProvinciaWeb(CatalogWeb catalogWeb) {
        this.setId(catalogWeb.getId());
        this.setNombre(catalogWeb.getNombre());
        this.setState(catalogWeb.getState());
    }


    private CatalogWeb pais;

    private List<CatalogWeb> ciudades = new ArrayList<>();


    public List<CatalogWeb> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CatalogWeb> ciudades) {
        this.ciudades = ciudades;
    }

    public void addCiudad(CatalogWeb catalogWeb) {
        this.ciudades.add(catalogWeb);
    }

    public CatalogWeb getPais() {
        return pais;
    }

    public void setPais(CatalogWeb pais) {
        this.pais = pais;
    }
}
