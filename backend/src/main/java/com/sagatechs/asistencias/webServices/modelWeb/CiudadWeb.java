package com.sagatechs.asistencias.webServices.modelWeb;

import java.util.ArrayList;
import java.util.List;

public class CiudadWeb extends CatalogWeb{

    public CiudadWeb() {
    }

    public CiudadWeb(CatalogWeb catalogWeb) {
        this.setId(catalogWeb.getId());
        this.setNombre(catalogWeb.getNombre());
        this.setState(catalogWeb.getState());
    }


    private CatalogWeb provincia;

    private List<CatalogWeb> ciudades = new ArrayList<>();


    public List<CatalogWeb> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CatalogWeb> ciudades) {
        this.ciudades = ciudades;
    }

    public void addCiudad(CatalogWeb catalogWeb){
        this.ciudades.add(catalogWeb);
    }


    public CatalogWeb getProvincia() {
        return provincia;
    }

    public void setProvincia(CatalogWeb provincia) {
        this.provincia = provincia;
    }
}
