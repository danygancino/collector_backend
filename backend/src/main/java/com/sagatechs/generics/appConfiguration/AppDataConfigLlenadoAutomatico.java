package com.sagatechs.generics.appConfiguration;


import com.sagatechs.asistencias.dao.catalogs.*;
import com.sagatechs.asistencias.model.catalogs.*;
import com.sagatechs.generics.persistence.model.State;
import com.sagatechs.generics.security.dao.RoleAssigmentDao;
import com.sagatechs.generics.security.dao.RoleDao;
import com.sagatechs.generics.security.dao.UserDao;
import com.sagatechs.generics.security.model.Role;
import com.sagatechs.generics.security.model.RoleAssigment;
import com.sagatechs.generics.security.model.RoleType;
import com.sagatechs.generics.security.model.User;
import com.sagatechs.generics.security.servicio.UserService;
import com.sagatechs.generics.utils.SecurityUtils;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("WeakerAccess")
@Singleton
@Startup
public class AppDataConfigLlenadoAutomatico {
    private static final Logger LOGGER = Logger.getLogger(AppDataConfigLlenadoAutomatico.class);

    @Inject
    RoleDao roleDao;

    @Inject
    RoleAssigmentDao roleAssigmentDao;

    @Inject
    UserDao userDao;


    @Inject
    SecurityUtils securityUtils;

    @Inject
    AppConfigurationDao appConfigurationDao;

    @Inject
    NivelParentescoDao nivelParentescoDao;
    @Inject
    SexoDao sexoDao;
    @Inject
    GeneroDao generoDao;
    @Inject
    LgbtiDao lgbtiDao;
    @Inject
    NacionalidadDao nacionalidadDao;
    @Inject
    EtniaDao etniaDao;
    @Inject
    DiscapacidadDao discapacidadDao;
    @Inject
    TipoDocumentoDao tipoDocumentoDao;
    @Inject
    EstatutoMigratorioDao estatutoMigratorioDao;
    @Inject
    SituacionMigratoriaDao situacionMigratoriaDao;
    @Inject
    MotivoSalidaPaisDao motivoSalidaPaisDao;
    @Inject
    NivelEscolaridadDao nivelEscolaridadDao;
    @Inject
    PaisDao paisDao;
    @Inject
    ProvinciaDao provinciaDao;
    @Inject
    CiudadDao ciudadDao;
    @Inject
    OrganizacionApoyoDao organizacionApoyoDao;
    @Inject
    ProfesionDao profesionDao;
    @Inject
    OficioDao oficioDao;
    @Inject
    NecesidadesEspecificasProteccionDao necesidadesEspecificasProteccionDao;
    @Inject
    NecesidadesEspecificasHabitabilidadDao necesidadesEspecificasHabitabilidadDao;
    @Inject
    ComponenteDao componenteDao;
    @Inject
    TipoAyudaDao tipoAyudaDao;
    @Inject
    MotivoReferenciaDao motivoReferenciaDao;
    @Inject
    OrganizacionReferenciaDao organizacionReferenciaDao;
    @Inject
    TipoAsistenciaDao tipoAsistenciaDao;
    @Inject
    TipoSeguimientoDao tipoSeguimientoDao;


    private Role roleAdministrador;
    private Role roleOperador;


    @PostConstruct
    private void init() {
        LOGGER.debug("Iniciando llenado automatico");
      /*  this.createAppConfigs();*/
        //this.cargarRoles();
        //this.cargarUsuarios();
        // this.createFileAppConfigs();

        //Descomentar cuando es un nueva implementacion
        /*this.createPaisProvinciaCiudad();
        this.createComponente();
        this.createDiscapacidad();
        this.createEstatutoMigratorio();
        this.createEtnia();
        this.createGenero();
        this.createLgbit();
        this.createMotivoReferencia();
        this.createMotivoSalidaPais();
        this.createNacionalidad();
        this.createNecesidadesEspecificasHabitabilidad();
        this.createNecesidadesEspecificasProteccion();
        this.createNivelEscolaridad();
        this.createNivelesParetesco();
        this.createOficio();
        this.createOrganizacionApoyo();
        this.createOrganizacionReferencia(); // falta
        this.createProfesion();
        this.createSexo();
        this.createSituacionMigratoria();
        this.createTipoAsistencia();//falta corregir esta en asistencia
        this.createTipoAyuda();
        this.createTipoDocumento();
        this.createTipoSeguimiento();// falta
*/

        LOGGER.debug("Terminado llenado autom??tico");
    }



    private void createFileAppConfigs() {
        LOGGER.info("createAppConfigs");


        instantiateConfigurationValues("Directorio de archivos",
                "Directio de archivo",
                AppConfigurationKey.FILE_DIRECTORY, "/opt/fileAttachments/");
    }

    @SuppressWarnings("unused")
    private void createAppConfigs() {
        LOGGER.info("createAppConfigs");


        instantiateConfigurationValues("Configuraci??n de correo electr??nico. Autenticaci??n SMTP",
                "True si requiere autemticaci??n smtp, false caso contrario",
                AppConfigurationKey.EMAIL_SMTP, "true");
        instantiateConfigurationValues("Configuraci??n de correo electr??nico. Requiere tls",
                "True si requiere tls smtp, false caso contrario",
                AppConfigurationKey.EMAIL_TLS, "true");
        instantiateConfigurationValues("Configuraci??n de correo electr??nico. Direcci??n servidor SMTP",
                "Direcci??n del servidor smtp",
                AppConfigurationKey.EMAIL_SMTP_HOST, "smtp.gmail.com");
        instantiateConfigurationValues("Configuraci??n de correo electr??nico. Puerto SMTP",
                "Puerto del servicio  smtp",
                AppConfigurationKey.EMAIL_SMTP_PORT, "465");
        instantiateConfigurationValues("Configuraci??n de correo electr??nico. Nombre de usuario",
                "Nombre de usuario de correo electr??nico",
                AppConfigurationKey.EMAIL_USERNAME, "care.ecuador.proyectos@gmail.com");
        instantiateConfigurationValues("Configuraci??n de correo electr??nico. Contrase??a",
                "Contrase??a de correo",
                AppConfigurationKey.EMAIL_PASSOWRD, "Sagatechs2019");
        instantiateConfigurationValues("Configuraci??n de correo electr??nico. Dirrecci??n de correo electr??nico",
                "Dirrecci??n de correo electr??nico",
                AppConfigurationKey.EMAIL_ADDRES, "care.ecuador.proyectos@gmail.com");
    }

    @SuppressWarnings("unused")
    private void cargarUsuarios() {


        byte[] pass = this.securityUtils.hashPasswordByte("1234", UserService.salt);

       /* User administrador = new User();
        administrador.setUsername("administrador");
        administrador.setName("name administrador");
        administrador.setPassword(pass);
        administrador.setState(State.ACTIVE);
        administrador.setEmail("administrador@yopmail.com");
        administrador.addRole(roleAdministrador);
        this.instantiateUser(administrador);*/

        User operador = new User();
        operador.setUsername("operador");
        operador.setName("name operador");
        operador.setPassword(pass);
        operador.setState(State.ACTIVE);
        operador.setEmail("operador@yopmail.com");
        operador.addRole(this.roleOperador);
        this.instantiateUser(operador);

    }

    @SuppressWarnings("unused")
    private void cargarRoles() {
        roleAdministrador = new Role(RoleType.ADMINISTRADOR, State.ACTIVE);
        this.instantiateRole(roleAdministrador);
        roleOperador = new Role(RoleType.OPERADOR, State.ACTIVE);
        this.instantiateRole(roleOperador);

    }


    @SuppressWarnings("UnusedReturnValue")
    private Role instantiateRole(Role role) {
        Role roleTmp = this.roleDao.findByRoleType(role.getRoleType());
        if (roleTmp == null) {
            return this.roleDao.save(role);
        } else {
            return role;
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    private User instantiateUser(User user) {
        User userTmp = this.userDao.findByUserNameWithRole(user.getUsername());
        if (userTmp == null) {
            user = this.userDao.save(user);
            for (RoleAssigment roleAssigmentD : user.getRoleAssigments()) {
                //this.roleAssigmentDao.save(roleAssigmentD);
            }
            return user;
        } else {
            return user;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void instantiateConfigurationValues(String nombre, String descripcion, AppConfigurationKey key,
                                                String valor) {
        AppConfiguration conf = this.appConfigurationDao.findByKey(key);
        if (conf == null) {
            AppConfiguration appConfigAppURL = new AppConfiguration(nombre, descripcion, key, valor);
            appConfigurationDao.save(appConfigAppURL);
        }
    }


    private void createNivelesParetesco() {
        List<String> items = new ArrayList<>();
        items.add("PUNTO FOCAL FAMILIAR");
        items.add("PADRE (DEL PUNTO FOCAL)");
        items.add("MADRE (DEL PUNTO FOCAL)");
        items.add("C??NYUGE (DEL PUNTO FOCAL)");
        items.add("PAREJA (DEL PUNTO FOCAL)");
        items.add("HIJO (DEL PUNTO FOCAL)");
        items.add("HIJA (DEL PUNTO FOCAL)");
        for (String item : items) {
            NivelParentesco itemO;
            NivelParentesco io = this.nivelParentescoDao.findByNombre(item);
            if (io == null) {
                itemO = new NivelParentesco();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.nivelParentescoDao.save(itemO);
            }
        }
    }



    private void createSexo() {
        List<String> items = new ArrayList<>();
        items.add("HOMBRE");
        items.add("MUJER");
        items.add("OTRO");
        for (String item : items) {
            Sexo itemO;
            Sexo io = this.sexoDao.findByNombre(item);
            if (io == null) {
                itemO = new Sexo();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.sexoDao.save(itemO);
            }
        }
    }


    private void createGenero() {
        List<String> items = new ArrayList<>();
        items.add("MASCULINO");
        items.add("FEMENINO");
        items.add("OTRO");
        items.add("NO BINARIO");
        items.add("PREFIERE NO RESPONDER");
        items.add("NO DEFINIDO");
        for (String item : items) {
            Genero itemO;
            Genero io = this.generoDao.findByNombre(item);
            if (io == null) {
                itemO = new Genero();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.generoDao.save(itemO);
            }
        }
    }


    private void createLgbit() {
        List<String> items = new ArrayList<>();
        items.add("SI");
        items.add("NO");
        items.add("NO DEFINIDO");
        for (String item : items) {
            Lgbti itemO;
            Lgbti io = this.lgbtiDao.findByNombre(item);
            if (io == null) {
                itemO = new Lgbti();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.lgbtiDao.save(itemO);
            }
        }
    }


    private void createNacionalidad() {
        List<String> items = new ArrayList<>();
        items.add("ECUATORIANA");
        items.add("VENEZOLANA");
        items.add("COLOMBIANA");
        items.add("PERUANA");
        items.add("OTRA");
        items.add("DOBLE NACIONALIDAD");
        items.add("NO DEFINIDO");
        for (String item : items) {
            Nacionalidad itemO;
            Nacionalidad io = this.nacionalidadDao.findByNombre(item);
            if (io == null) {
                itemO = new Nacionalidad();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.nacionalidadDao.save(itemO);
            }
        }
    }



    private void createEtnia() {
        List<String> items = new ArrayList<>();
        items.add("BLANCO");
        items.add("MESTIZO");
        items.add("AFRODESCENDIENTE");
        items.add("MULATO");
        items.add("IND??GENA");
        items.add("MONTUBIO");
        items.add("OTRO ");
        items.add("NO DEFINIDO");

        for (String item : items) {
            Etnia itemO;
            Etnia io = this.etniaDao.findByNombre(item);
            if (io == null) {
                itemO = new Etnia();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.etniaDao.save(itemO);
            }
        }
    }


    private void createDiscapacidad() {
        List<String> items = new ArrayList<>();
        items.add("SI");
        items.add("NO");
        items.add("NO DEFINIDO");
        for (String item : items) {
            Discapacidad itemO;
            Discapacidad io = this.discapacidadDao.findByNombre(item);
            if (io == null) {
                itemO = new Discapacidad();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.discapacidadDao.save(itemO);
            }
        }
    }


    private void createTipoDocumento() {
        List<String> items = new ArrayList<>();
        items.add("C??DULA");
        items.add("PASAPORTE");
        items.add("TARJETA ANDINA");
        items.add("VISA REFUGIO");
        items.add("VISA VERHU");
        items.add("VISA TRABAJO");
        items.add("VISA UNASUR");
        items.add("VISA AMPARO");
        items.add("OTRO");
        items.add("SIN DOCUMENTO");
        for (String item : items) {
            TipoDocumento itemO;
            TipoDocumento io = this.tipoDocumentoDao.findByNombre(item);
            if (io == null) {
                itemO = new TipoDocumento();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.tipoDocumentoDao.save(itemO);
            }
        }
    }


    private void createEstatutoMigratorio() {
        List<String> items = new ArrayList<>();
        items.add("PERSONAS REFUGIADAS");
        items.add("SOLICITANTES DE ASILO");
        items.add("COMUNIDADES DE ACOGIDA");
        items.add("PERSONAS FUERA DE LA CATEGORIA DE ASILO ");
        items.add("NO DEFINIDO");
        for (String item : items) {
            EstatutoMigratorio itemO;
            EstatutoMigratorio io = this.estatutoMigratorioDao.findByNombre(item);
            if (io == null) {
                itemO = new EstatutoMigratorio();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.estatutoMigratorioDao.save(itemO);
            }
        }
    }



    private void createSituacionMigratoria() {
        List<String> items = new ArrayList<>();
        items.add("REGULAR");
        items.add("IRREGULAR");
        items.add("NO APLICA");
        items.add("NO DEFINIDO");
        for (String item : items) {
            SituacionMigratoria itemO;
            SituacionMigratoria io = this.situacionMigratoriaDao.findByNombre(item);
            if (io == null) {
                itemO = new SituacionMigratoria();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.situacionMigratoriaDao.save(itemO);
            }
        }
    }


    private void createMotivoSalidaPais() {
        List<String> items = new ArrayList<>();
        items.add("FALTA DE ACCESO A SERVICIOS B??SICOS Y/O VULNERACI??N DE DERECHOS");
        items.add("REFUGIO");
        items.add("MIGRACI??N POR RAZONES ECON??MICAS");
        items.add("OTRO");
        items.add("NO DEFINIDO");
        for (String item : items) {
            MotivoSalidaPais itemO;
            MotivoSalidaPais io = this.motivoSalidaPaisDao.findByNombre(item);
            if (io == null) {
                itemO = new MotivoSalidaPais();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.motivoSalidaPaisDao.save(itemO);
            }
        }
    }


    private void createNivelEscolaridad() {
        List<String> items = new ArrayList<>();
        items.add("EDUCACI??N B??SICA INICIAL (PREPARATORIA)");
        items.add("EDUCACI??N B??SICA ELEMENTAL");
        items.add("EDUCACI??N B??SICA MEDIA");
        items.add("EDUCACI??N B??SICA SUPERIOR");
        items.add("BACHILLERATO GENERAL UNIFICADO");
        items.add("BACHILLERATO T??CNICO");
        items.add("TECNOLOG??A");
        items.add("TITULO DE TERCER NIVEL (LICENCIATURA)");
        items.add("TITULO DE CUARTO NIVEL (MAESTR??A)");
        items.add("TITULO DE QUINTO NIVEL (DOCTORADO)");
        items.add("NO DEFINIDO");

        for (String item : items) {
            NivelEscolaridad itemO;
            NivelEscolaridad io = this.nivelEscolaridadDao.findByNombre(item);
            if (io == null) {
                itemO = new NivelEscolaridad();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.nivelEscolaridadDao.save(itemO);
            }
        }
    }


    private void createPaisProvinciaCiudad() {
        List<String> items = new ArrayList<>();
        items.add("EDUCACI??N B??SICA INICIAL (PREPARATORIA)");
        items.add("EDUCACI??N B??SICA ELEMENTAL");
        items.add("EDUCACI??N B??SICA MEDIA");
        items.add("EDUCACI??N B??SICA SUPERIOR");
        items.add("BACHILLERATO GENERAL UNIFICADO");
        items.add("BACHILLERATO T??CNICO");
        items.add("TECNOLOG??A");
        items.add("TITULO DE TERCER NIVEL (LICENCIATURA)");
        items.add("TITULO DE CUARTO NIVEL (MAESTR??A)");
        items.add("TITULO DE QUINTO NIVEL (DOCTORADO)");
        items.add("NO DEFINIDO");


        Pais ecuador = this.paisDao.findByNombre("Ecuador");
        if (ecuador == null) {
            ecuador = new Pais();
            ecuador.setEstado(State.ACTIVE);
            ecuador.setNombre("Ecuador");
            this.paisDao.save(ecuador);
        }

        Provincia manabiProv = this.provinciaDao.findByNombre("Manabi");
        if (manabiProv == null) {
            manabiProv = new Provincia();
            manabiProv.setEstado(State.ACTIVE);
            manabiProv.setNombre("Manabi");
            manabiProv.setPais(ecuador);
            ecuador.addProvincia(manabiProv);
            this.provinciaDao.save(manabiProv);
        }
        Ciudad mantaCiudad = this.ciudadDao.findByNombre("Manta");
        if (mantaCiudad == null) {
            mantaCiudad = new Ciudad();
            mantaCiudad.setEstado(State.ACTIVE);
            mantaCiudad.setNombre("Manta");
            mantaCiudad.setProvincia(manabiProv);
            manabiProv.addCiudad(mantaCiudad);
            this.ciudadDao.save(mantaCiudad);
        }
        Ciudad portoviejoCiudad = this.ciudadDao.findByNombre("Portoviejo");
        if (portoviejoCiudad == null) {
            portoviejoCiudad = new Ciudad();
            portoviejoCiudad.setEstado(State.ACTIVE);
            portoviejoCiudad.setNombre("Portoviejo");
            portoviejoCiudad.setProvincia(manabiProv);
            manabiProv.addCiudad(portoviejoCiudad);
            this.ciudadDao.save(portoviejoCiudad);
        }
        Ciudad montecristiCiudad = this.ciudadDao.findByNombre("Montecristi");
        if (montecristiCiudad == null) {
            montecristiCiudad = new Ciudad();
            montecristiCiudad.setEstado(State.ACTIVE);
            montecristiCiudad.setNombre("Montecristi");
            montecristiCiudad.setProvincia(manabiProv);
            manabiProv.addCiudad(montecristiCiudad);
            this.ciudadDao.save(montecristiCiudad);
        }

        Provincia elOroProv = this.provinciaDao.findByNombre("El Oro");
        if (elOroProv == null) {
            elOroProv = new Provincia();
            elOroProv.setEstado(State.ACTIVE);
            elOroProv.setNombre("El Oro");
            elOroProv.setPais(ecuador);
            ecuador.addProvincia(elOroProv);
            this.provinciaDao.save(elOroProv);
        }
        Ciudad machalaCiudad = this.ciudadDao.findByNombre("Machala");
        if (machalaCiudad == null) {
            machalaCiudad = new Ciudad();
            machalaCiudad.setEstado(State.ACTIVE);
            machalaCiudad.setNombre("Machala");
            machalaCiudad.setProvincia(elOroProv);
            elOroProv.addCiudad(machalaCiudad);
            this.ciudadDao.save(machalaCiudad);
        }
        Ciudad elOroCiudad = this.ciudadDao.findByNombre("El Oro");
        if (elOroCiudad == null) {
            elOroCiudad = new Ciudad();
            elOroCiudad.setEstado(State.ACTIVE);
            elOroCiudad.setNombre("El Oro");
            elOroCiudad.setProvincia(elOroProv);
            elOroProv.addCiudad(elOroCiudad);
            this.ciudadDao.save(elOroCiudad);
        }
        Ciudad huaquillasCiudad = this.ciudadDao.findByNombre("Huaquillas");
        if (huaquillasCiudad == null) {
            huaquillasCiudad = new Ciudad();
            huaquillasCiudad.setEstado(State.ACTIVE);
            huaquillasCiudad.setNombre("Huaquillas");
            huaquillasCiudad.setProvincia(elOroProv);
            elOroProv.addCiudad(huaquillasCiudad);
            this.ciudadDao.save(huaquillasCiudad);
        }

        Provincia pichinchaProv = this.provinciaDao.findByNombre("Pichincha");
        if (pichinchaProv == null) {
            pichinchaProv = new Provincia();
            pichinchaProv.setEstado(State.ACTIVE);
            pichinchaProv.setNombre("Pichincha");
            pichinchaProv.setPais(ecuador);
            ecuador.addProvincia(pichinchaProv);
            this.provinciaDao.save(pichinchaProv);
        }
        Ciudad quitoCiudad = this.ciudadDao.findByNombre("Quito");
        if (quitoCiudad == null) {
            quitoCiudad = new Ciudad();
            quitoCiudad.setEstado(State.ACTIVE);
            quitoCiudad.setNombre("Quito");
            quitoCiudad.setProvincia(pichinchaProv);
            pichinchaProv.addCiudad(quitoCiudad);
            this.ciudadDao.save(quitoCiudad);
        }

        Provincia santoDomingoProv = this.provinciaDao.findByNombre("Santo Domingo");
        if (santoDomingoProv == null) {
            santoDomingoProv = new Provincia();
            santoDomingoProv.setEstado(State.ACTIVE);
            santoDomingoProv.setNombre("Santo Domingo");
            santoDomingoProv.setPais(ecuador);
            ecuador.addProvincia(santoDomingoProv);
            this.provinciaDao.save(santoDomingoProv);
        }
        Ciudad santoDomingoCiudad = this.ciudadDao.findByNombre("Santo Domingo");
        if (santoDomingoCiudad == null) {
            santoDomingoCiudad = new Ciudad();
            santoDomingoCiudad.setEstado(State.ACTIVE);
            santoDomingoCiudad.setNombre("Santo Domingo");
            santoDomingoCiudad.setProvincia(santoDomingoProv);
            santoDomingoProv.addCiudad(santoDomingoCiudad);
            this.ciudadDao.save(santoDomingoCiudad);
        }
        Provincia sucumbiosProv = this.provinciaDao.findByNombre("Sucumb??os");
        if (sucumbiosProv == null) {
            sucumbiosProv = new Provincia();
            sucumbiosProv.setEstado(State.ACTIVE);
            sucumbiosProv.setNombre("Sucumb??os");
            sucumbiosProv.setPais(ecuador);
            ecuador.addProvincia(sucumbiosProv);
            this.provinciaDao.save(sucumbiosProv);
        }
        Ciudad lagoAgrioCiudad = this.ciudadDao.findByNombre("Lago Agrio");
        if (lagoAgrioCiudad == null) {
            lagoAgrioCiudad = new Ciudad();
            lagoAgrioCiudad.setEstado(State.ACTIVE);
            lagoAgrioCiudad.setNombre("Lago Agrio");
            lagoAgrioCiudad.setProvincia(sucumbiosProv);
            sucumbiosProv.addCiudad(lagoAgrioCiudad);
            this.ciudadDao.save(lagoAgrioCiudad);
        }
        Provincia azuayProv = this.provinciaDao.findByNombre("Azuay");
        if (azuayProv == null) {
            azuayProv = new Provincia();
            azuayProv.setEstado(State.ACTIVE);
            azuayProv.setNombre("Azuay");
            azuayProv.setPais(ecuador);
            ecuador.addProvincia(azuayProv);
            this.provinciaDao.save(azuayProv);
        }
        Ciudad cuencaCiudad = this.ciudadDao.findByNombre("Cuenca");
        if (cuencaCiudad == null) {
            cuencaCiudad = new Ciudad();
            cuencaCiudad.setEstado(State.ACTIVE);
            cuencaCiudad.setNombre("Cuenca");
            cuencaCiudad.setProvincia(azuayProv);
            azuayProv.addCiudad(cuencaCiudad);
            this.ciudadDao.save(cuencaCiudad);
        }


    }



    private void createOrganizacionApoyo() {
        List<String> items = new ArrayList<>();
        items.add("ACNUR");
        items.add("ADRA");
        items.add("ALAS DE COLIBR??");
        items.add("ALDEAS SOS");
        items.add("ASOCIACI??N SOCIAL MUNICIPAL");
        items.add("CARE");
        items.add("C??RITAS");
        items.add("CASA DEL MIGRANTE");
        items.add("CISP");
        items.add("CLUB ROTARIO");
        items.add("CONSEJO NORUEGO");
        items.add("COOPI");
        items.add("DEFENSOR??A DEL PUEBLO");
        items.add("DEFENSOR??A P??BLICA");
        items.add("DI??LOGO DIVERSO");
        items.add("FUDELA");
        items.add("FUNDACI??N EQUIDAD");
        items.add("FUNDACI??N HACIENDO PANAS");
        items.add("FUNDACI??N JUBASCA");
        items.add("FUNDACI??N LUNITA LUNERA");
        items.add("FUNDACI??N NUESTRA NI??EZ");
        items.add("FUNDACI??N NUESTROS J??VENES");
        items.add("FUNDACI??N NUEVOS HORIZONTES");
        items.add("FUNDACI??N RAFALEX");
        items.add("FUNDACI??N RAYITO DE ESPERANZA");
        items.add("FUNDACI??N RIO MANTA");
        items.add("FUNDACI??N SEMBRAR");
        items.add("FUNDACI??N SENDAS");
        items.add("GAD");
        items.add("HIAS");
        items.add("JUNTA CANTONAL DE PROTECCI??N DE DERECHOS");
        items.add("MIES");
        items.add("MINISTERIO DE SALUD P??BLICA");
        items.add("OIM");
        items.add("OTRA (ESPECIFIQUE EN LAS OBSERV.)");
        items.add("PASTORAL SOCIAL");
        items.add("PLAN INTERNACIONAL");
        items.add("PMA");
        items.add("PROYECTO INTEGRADOS");
        items.add("SERVICIO JESUITA");
        items.add("UNICEF");
        items.add("WOCCU");
        items.add("WORLD VISION");
        items.add("OTRA (ESPECIFIQUE EN LAS OBSERV.)");

        for (String item : items) {
            OrganizacionApoyo itemO;
            OrganizacionApoyo io = this.organizacionApoyoDao.findByNombre(item);
            if (io == null) {
                itemO = new OrganizacionApoyo();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.organizacionApoyoDao.save(itemO);
            }
        }
    }



    private void createProfesion() {
        List<String> items = new ArrayList<>();
        items.add("ABOGADO/A");
        items.add("ADMINISTRADOR/A DE EMPRESAS");
        items.add("ANALISTA DE SISTEMAS INFORM??TICOS");
        items.add("ANTROP??LOGO/A");
        items.add("ARQUE??LOGO/A");
        items.add("ARQUITECTO/A");
        items.add("BIBLIOTECARIO/A");
        items.add("BI??LOGO/A");
        items.add("BOT??NICO/A");
        items.add("CONTADOR/A");
        items.add("ECONOMISTA");
        items.add("ELECTRICISTA");
        items.add("ENFERMERO/A");
        items.add("FARMAC??LOGO/A");
        items.add("FIL??SOFO/A");
        items.add("F??SICO/A");
        items.add("HISTORIADOR/A");
        items.add("INGENIERO/A");
        items.add("LING??ISTA");
        items.add("MATEM??TICO/A");
        items.add("M??DICO CIRUJANO");
        items.add("M??SICO");
        items.add("ODONT??LOGO/A");
        items.add("PALEONT??LOGO/A");
        items.add("PARAM??DICO");
        items.add("PERIODISTA");
        items.add("POLIT??LOGO/A");
        items.add("PROFESOR/A");
        items.add("PSICOANALISTA");
        items.add("PSIC??LOGO/A");
        items.add("QU??MICO/A");
        items.add("RADI??LOGO/A");
        items.add("SECRETARIO/A");
        items.add("SOCI??LOGO/A");
        items.add("T??CNICO/A DE SONIDO");
        items.add("T??CNICO/A EN COMPUTACI??N");
        items.add("T??CNICO/A EN TURISMO");
        items.add("TRADUCTOR/A");
        items.add("OTRA PROFESI??N");

        for (String item : items) {
            Profesion itemO;
            Profesion io = this.profesionDao.findByNombre(item);
            if (io == null) {
                itemO = new Profesion();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.profesionDao.save(itemO);
            }
        }
    }


    private void createOficio() {
        List<String> items = new ArrayList<>();
        items.add("AGRICULTURA");
        items.add("ALBA??ILER??A");
        items.add("ALUMINIO Y VIDRIO");
        items.add("ANIMACI??N");
        items.add("ARTESAN??A");
        items.add("BARREDORES");
        items.add("CAJEROS");
        items.add("CARNICER??A");
        items.add("CARPINTER??A");
        items.add("CERRAJER??A");
        items.add("COCINA");
        items.add("COSTURA");
        items.add("ELECTRICIDAD");
        items.add("ESCRITURA");
        items.add("FRUTER??A");
        items.add("GANADER??A");
        items.add("IMPRENTA");
        items.add("JARDINER??A");
        items.add("LAVANDER??A");
        items.add("LIMPIEZA");
        items.add("MANEJO/CONDUCCI??N DE VEICULOS");
        items.add("MEC??NICA");
        items.add("MESEROS/CAMAREROS");
        items.add("OBREROS");
        items.add("OTRAS VENTAS/OTROS COMERCIOS");
        items.add("PANADER??A");
        items.add("PELUQUER??A");
        items.add("PESCA");
        items.add("PINTURA DE BROCHA GORDA");
        items.add("PLOMER??A");
        items.add("POLIC??A");
        items.add("SERVICIOS DE ENTREGAS");
        items.add("SOLDADURA");
        items.add("TORNO");
        items.add("VIGILANCIA/SEGURIDAD");
        items.add("OTRO OFICIO");

        for (String item : items) {
            Oficio itemO;
            Oficio io = this.oficioDao.findByNombre(item);
            if (io == null) {
                itemO = new Oficio();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.oficioDao.save(itemO);
            }
        }
    }


    private void createNecesidadesEspecificasProteccion() {
        List<String> items = new ArrayList<>();
        items.add("ADULTO/A MAYOR");
        items.add("CONDICI??N MEDICA SEVERA/ENF. CR??NICAS");
        items.add("DISCAPACIDAD");
        items.add("ENF. CATASTR??FICAS");
        items.add("HOGAR MONOPARENTAL");
        items.add("MUJER EMBARAZADA");
        items.add("MUJER LACTANTE");
        items.add("NECESIDAD DE PROTECCI??N INTERNACIONAL");
        items.add("NNA NO ACOMPA??ADO/SEPARADO");
        items.add("POBLACION LGBTIQ+");
        items.add("SOBREVIVIENTE DE TORTURA");
        items.add("VIOLENCIA BASADA EN G??NERO");
        items.add("VIOLENCIA INTRAFAMILIAR");
        items.add("OTRA (TRATA, PERSECUCI??N, EXPLOTACI??N)");

        for (String item : items) {
            NecesidadEspecificaProteccion itemO;
            NecesidadEspecificaProteccion io = this.necesidadesEspecificasProteccionDao.findByNombre(item);
            if (io == null) {
                itemO = new NecesidadEspecificaProteccion();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.necesidadesEspecificasProteccionDao.save(itemO);
            }
        }
    }


    private void createNecesidadesEspecificasHabitabilidad() {
        List<String> items = new ArrayList<>();
        items.add("EN ALBERGUE");
        items.add("HACINAMIENTO");
        items.add("RECI??N LLEGADOS A PA??S");
        items.add("SITUACI??N DE CALLE");
        items.add("DESALOJO");
        items.add("DEUDA ARRIENDO (CUANTOS MESES)");
        items.add("CONDICIONES ESTRUCTURALES DEFICIENTES EN VIVIENDA");
        items.add("ORIENTACI??N SOBRE DERECHOS DE VIVIENDA Y PROPIEDAD");
        items.add("OTRA NECESIDAD (ESPECIFIQUE)");

        for (String item : items) {
            NecesidadEspecificaHabitabilidad itemO;
            NecesidadEspecificaHabitabilidad io = this.necesidadesEspecificasHabitabilidadDao.findByNombre(item);
            if (io == null) {
                itemO = new NecesidadEspecificaHabitabilidad();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.necesidadesEspecificasHabitabilidadDao.save(itemO);
            }
        }
    }


    private void createComponente() {
        List<Componente> componentes = new ArrayList<>();
        List<Componente> componentesToCreate = new ArrayList<>();
        List<Componente> componentesToUpdate = new ArrayList<>();

        Componente proteccion = new Componente("PROTECCI??N", State.ACTIVE);
        componentes.add(proteccion);
        Componente habitabilidad = new Componente("HABITABILIDAD", State.ACTIVE);
        componentes.add(habitabilidad);
        Componente inclusion = new Componente("INCLUSI??N SOCIOECON??MICA", State.ACTIVE);
        componentes.add(inclusion);
        Componente otro = new Componente("OTRO", State.ACTIVE);
        componentes.add(otro);

        for (Componente componente : componentes) {
            Componente c1 = this.componenteDao.findByNombre(componente.getNombre());
            if (c1 == null) {
                componentesToCreate.add(componente);
            } else {
                componentesToUpdate.add(c1);
            }
        }


        List<String> itemsproteccion = new ArrayList<>();
        itemsproteccion.add("ACTIVIDAD DE AUTOGESTI??N COMUNITARIA");
        itemsproteccion.add("ACTIVIDAD DE PLANIFICACI??N COMUNITARIA");
        itemsproteccion.add("APOYO PSICOSOCIAL");
        itemsproteccion.add("ASESOR??A LEGAL");
        itemsproteccion.add("CBI MULTIPROP??SITO");
        itemsproteccion.add("FORMACI??N / FORTALECIMIENTO COMUNITARIO");
        itemsproteccion.add("FORMACI??N LIDER/LIDERESA COMUNITARIO/A");
        itemsproteccion.add("KIT DE ALIMENTOS");
        itemsproteccion.add("KIT DE BIOSEGURIDAD");
        itemsproteccion.add("ORIENTACI??N PARA ACCESO A ASILO");
        itemsproteccion.add("ORIENTACI??N PARA ACCESO A SERVICIOS MIGRATORIOS");
        itemsproteccion.add("PARTICICIPANTE COMIT?? VECINOS ACTIVADOS");
        itemsproteccion.add("PARTICIPANTE DIAGN??STICO PARTICIPATIVO");
        itemsproteccion.add("PARTIPANTE JORNADA DE SALUD");


        List<TipoAsistencia> tiposToCreate = new ArrayList<>();
        for (String item : itemsproteccion) {
             TipoAsistencia io = this.tipoAsistenciaDao.findByNombre(item);
            if (io == null) {
                io = new TipoAsistencia();
                io.setEstado(State.ACTIVE);
                io.setNombre(item);
                proteccion.addTipoAsistencia(io);
                tiposToCreate.add(io);
            }
        }

        List<String> itemshabitabilidad = new ArrayList<>();
        itemshabitabilidad.add("ACONDICIONAMIENTO VIVIENDA MULTIFAMILIAR");
        itemshabitabilidad.add("CBI ARRIENDO");
        itemshabitabilidad.add("GEOREFERENCIACI??N DE VIVIENDA");
        itemshabitabilidad.add("KIT HABITABILIDAD");
        itemshabitabilidad.add("ORIENTACI??N SOBRE DERECHOS DE VIVIENDA Y PROPIEDAD");
        itemshabitabilidad.add("RELOCALIZACI??N A ESPACIO SEGURO");
        itemshabitabilidad.add("SALIDA ALBERGUE");

        for (String item : itemshabitabilidad) {
           TipoAsistencia io = this.tipoAsistenciaDao.findByNombre(item);
            if (io == null) {
                io = new TipoAsistencia();
                io.setEstado(State.ACTIVE);
                io.setNombre(item);
                habitabilidad.addTipoAsistencia(io);
                tiposToCreate.add(io);
            }
        }

        List<String> itemsinclusion = new ArrayList<>();
        itemsinclusion.add("CAPACITACI??N PARA ACCESO A EMPLEO O EMPRENDIMIENTO");
        itemsinclusion.add("CAPITAL SEMILLA PARA EMPRENDIMIENTO");
        itemsinclusion.add("KIT PARA EMPRENDIMIENTO");
        itemsinclusion.add("ORIENTACI??N PARA ACCESO A EMPLEO O EMPRENDIMIENTO");
        itemsinclusion.add("TALLER PARA ACCESO A EMPLEO O EMPRENDIMIENTO");

        for (String item : itemsinclusion) {
            TipoAsistencia io = this.tipoAsistenciaDao.findByNombre(item);
            if (io == null) {
                io = new TipoAsistencia();
                io.setEstado(State.ACTIVE);
                io.setNombre(item);
                inclusion.addTipoAsistencia(io);
                tiposToCreate.add(io);
            }
        }

        List<String> itemsotro = new ArrayList<>();
        itemsotro.add("DERIVACI??N CASO");
        itemsotro.add("GESTI??N Y A COMPA??AMIENTO DE CASO");
        itemsotro.add("INFORMACI??N SOBRE ACCESO A SERVICIOS Y DERECHOS ");
        itemsotro.add("OTRO TIPO DE ASISTENCIA");


        for (String item : itemsotro) {
            TipoAsistencia io = this.tipoAsistenciaDao.findByNombre(item);
            if (io == null) {
                io = new TipoAsistencia();
                io.setEstado(State.ACTIVE);
                io.setNombre(item);
                otro.addTipoAsistencia(io);
                tiposToCreate.add(io);
            }
        }

        for (Componente componente : componentesToCreate) {
            this.componenteDao.save(componente);
        }
        for (Componente componente : componentesToUpdate) {
            this.componenteDao.update(componente);
        }

        for (TipoAsistencia tipoAsistencia : tiposToCreate) {
            this.tipoAsistenciaDao.save(tipoAsistencia);

        }

    }


    private void createMotivoReferencia() {
        List<String> items = new ArrayList<>();
        items.add("ACCESO A ALIMENTACI??N");
        items.add("ACCESO A EDUCACI??N");
        items.add("ACCESO A SALUD");
        items.add("ACCESO A VIVIENDA");
        items.add("APOYO PSICOSOCIAL");
        items.add("ASESOR??A LEGAL-DERECHOS LABORALES");
        items.add("ASESOR??A LEGAL-DERECHOS VIVIENDA");
        items.add("ASESOR??A LEGAL-REGULARIZACI??N MIGRATORIA");
        items.add("ASESOR??A LEGAL-OTRAS ??REAS");
        items.add("CAPACITACI??N/TALLER");
        items.add("OTRO ");
        items.add("NO DEFINIDO");

        for (String item : items) {
            MotivoReferencia itemO;
            MotivoReferencia io = this.motivoReferenciaDao.findByNombre(item);
            if (io == null) {
                itemO = new MotivoReferencia();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.motivoReferenciaDao.save(itemO);
            }
        }
    }



    private void createOrganizacionReferencia() {
        List<String> items = new ArrayList<>();
        items.add("ACNUR");
        items.add("ADRA");
        items.add("ALAS DE COLIBR??");

        for (String item : items) {
            OrganizacionReferencia itemO;
            OrganizacionReferencia io = this.organizacionReferenciaDao.findByNombre(item);
            if (io == null) {
                itemO = new OrganizacionReferencia();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.organizacionReferenciaDao.save(itemO);
            }
        }
    }






    private void createTipoSeguimiento() {
        List<String> items = new ArrayList<>();
        items.add("Tipo 1");
        items.add("Tipo 2");
        items.add("Tipo 3");

        for (String item : items) {
            TipoSeguimiento itemO;
            TipoSeguimiento io = this.tipoSeguimientoDao.findByNombre(item);
            if (io == null) {
                itemO = new TipoSeguimiento();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.tipoSeguimientoDao.save(itemO);
            }
        }
    }


    private void createTipoAyuda() {

        List<String> items = new ArrayList<>();
        items.add("ASESOR??A LEGAL");
        items.add("ACCESO A M??TODOS ANTICONCEPTIVOS /M??TODOS PREVENTIVOS");
        items.add("CAPACITACI??N/TALLER EN TEMAS DE PROTECCI??N");
        items.add("CAPACITACI??N/TALLER PARA ACCESO A EMPLEO O EMPRENDIMIENTO");
        items.add("CAPITAL SEMILLA PARA EMPRENDIMIENTO");
        items.add("CBI ARRIENDO");
        items.add("CBI MULTIPROPOSITO");
        items.add("EQUIPOS DE PROTECCI??N PERSONAL (EPP) O KITS DE BIOSEGURIDAD");
        items.add("INFORMACI??N SOBRE ACCESO A SERVICIOS Y DERECHOS");
        items.add("KIT DE ALIMENTOS");
        items.add("KIT DIGNIDAD");
        items.add("KIT EDUCATIVO");
        items.add("KIT HABITABILIDAD");
        items.add("KIT HIGIENE");
        items.add("ORIENTACI??N PARA ACCESO A ASILO/REFUGIO");
        items.add("ORIENTACI??N PARA ACCESO A SERVICIOS MIGRATORIOS");
        items.add("PROVISI??N DE ALOJAMIENTO TEMPORAL");
        items.add("PROVISI??N DE PRUEBAS R??PIDAS (SALUD)");
        items.add("SENSIBILIZACI??N SOBRE TEMAS DE SALUD Y H??BITOS SALUDABLES");
        items.add("TARJETA DE ALIMENTOS");
        items.add("OTRO TIPO DE ASISTENCIA (ESPECIFIQUE EN LAS OBSERV.)");



        for (String item : items) {
            TipoAyuda itemO;
            TipoAyuda io = this.tipoAyudaDao.findByNombre(item);
            if (io == null) {
                itemO = new TipoAyuda();
                itemO.setEstado(State.ACTIVE);
                itemO.setNombre(item);
                this.tipoAyudaDao.save(itemO);
            }
        }
    }
}

