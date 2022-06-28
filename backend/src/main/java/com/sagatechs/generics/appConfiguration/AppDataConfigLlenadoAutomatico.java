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

        LOGGER.debug("Terminado llenado automático");
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


        instantiateConfigurationValues("Configuración de correo electrónico. Autenticación SMTP",
                "True si requiere autemticación smtp, false caso contrario",
                AppConfigurationKey.EMAIL_SMTP, "true");
        instantiateConfigurationValues("Configuración de correo electrónico. Requiere tls",
                "True si requiere tls smtp, false caso contrario",
                AppConfigurationKey.EMAIL_TLS, "true");
        instantiateConfigurationValues("Configuración de correo electrónico. Dirección servidor SMTP",
                "Dirección del servidor smtp",
                AppConfigurationKey.EMAIL_SMTP_HOST, "smtp.gmail.com");
        instantiateConfigurationValues("Configuración de correo electrónico. Puerto SMTP",
                "Puerto del servicio  smtp",
                AppConfigurationKey.EMAIL_SMTP_PORT, "465");
        instantiateConfigurationValues("Configuración de correo electrónico. Nombre de usuario",
                "Nombre de usuario de correo electrónico",
                AppConfigurationKey.EMAIL_USERNAME, "care.ecuador.proyectos@gmail.com");
        instantiateConfigurationValues("Configuración de correo electrónico. Contraseña",
                "Contraseña de correo",
                AppConfigurationKey.EMAIL_PASSOWRD, "Sagatechs2019");
        instantiateConfigurationValues("Configuración de correo electrónico. Dirrección de correo electrónico",
                "Dirrección de correo electrónico",
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
        items.add("CÓNYUGE (DEL PUNTO FOCAL)");
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
        items.add("INDÍGENA");
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
        items.add("CÉDULA");
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
        items.add("FALTA DE ACCESO A SERVICIOS BÁSICOS Y/O VULNERACIÓN DE DERECHOS");
        items.add("REFUGIO");
        items.add("MIGRACIÓN POR RAZONES ECONÓMICAS");
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
        items.add("EDUCACIÓN BÁSICA INICIAL (PREPARATORIA)");
        items.add("EDUCACIÓN BÁSICA ELEMENTAL");
        items.add("EDUCACIÓN BÁSICA MEDIA");
        items.add("EDUCACIÓN BÁSICA SUPERIOR");
        items.add("BACHILLERATO GENERAL UNIFICADO");
        items.add("BACHILLERATO TÉCNICO");
        items.add("TECNOLOGÍA");
        items.add("TITULO DE TERCER NIVEL (LICENCIATURA)");
        items.add("TITULO DE CUARTO NIVEL (MAESTRÍA)");
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
        items.add("EDUCACIÓN BÁSICA INICIAL (PREPARATORIA)");
        items.add("EDUCACIÓN BÁSICA ELEMENTAL");
        items.add("EDUCACIÓN BÁSICA MEDIA");
        items.add("EDUCACIÓN BÁSICA SUPERIOR");
        items.add("BACHILLERATO GENERAL UNIFICADO");
        items.add("BACHILLERATO TÉCNICO");
        items.add("TECNOLOGÍA");
        items.add("TITULO DE TERCER NIVEL (LICENCIATURA)");
        items.add("TITULO DE CUARTO NIVEL (MAESTRÍA)");
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
        Provincia sucumbiosProv = this.provinciaDao.findByNombre("Sucumbíos");
        if (sucumbiosProv == null) {
            sucumbiosProv = new Provincia();
            sucumbiosProv.setEstado(State.ACTIVE);
            sucumbiosProv.setNombre("Sucumbíos");
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
        items.add("ALAS DE COLIBRÍ");
        items.add("ALDEAS SOS");
        items.add("ASOCIACIÓN SOCIAL MUNICIPAL");
        items.add("CARE");
        items.add("CÁRITAS");
        items.add("CASA DEL MIGRANTE");
        items.add("CISP");
        items.add("CLUB ROTARIO");
        items.add("CONSEJO NORUEGO");
        items.add("COOPI");
        items.add("DEFENSORÍA DEL PUEBLO");
        items.add("DEFENSORÍA PÚBLICA");
        items.add("DIÁLOGO DIVERSO");
        items.add("FUDELA");
        items.add("FUNDACIÓN EQUIDAD");
        items.add("FUNDACIÓN HACIENDO PANAS");
        items.add("FUNDACIÓN JUBASCA");
        items.add("FUNDACIÓN LUNITA LUNERA");
        items.add("FUNDACIÓN NUESTRA NIÑEZ");
        items.add("FUNDACIÓN NUESTROS JÓVENES");
        items.add("FUNDACIÓN NUEVOS HORIZONTES");
        items.add("FUNDACIÓN RAFALEX");
        items.add("FUNDACIÓN RAYITO DE ESPERANZA");
        items.add("FUNDACIÓN RIO MANTA");
        items.add("FUNDACIÓN SEMBRAR");
        items.add("FUNDACIÓN SENDAS");
        items.add("GAD");
        items.add("HIAS");
        items.add("JUNTA CANTONAL DE PROTECCIÓN DE DERECHOS");
        items.add("MIES");
        items.add("MINISTERIO DE SALUD PÚBLICA");
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
        items.add("ANALISTA DE SISTEMAS INFORMÁTICOS");
        items.add("ANTROPÓLOGO/A");
        items.add("ARQUEÓLOGO/A");
        items.add("ARQUITECTO/A");
        items.add("BIBLIOTECARIO/A");
        items.add("BIÓLOGO/A");
        items.add("BOTÁNICO/A");
        items.add("CONTADOR/A");
        items.add("ECONOMISTA");
        items.add("ELECTRICISTA");
        items.add("ENFERMERO/A");
        items.add("FARMACÓLOGO/A");
        items.add("FILÓSOFO/A");
        items.add("FÍSICO/A");
        items.add("HISTORIADOR/A");
        items.add("INGENIERO/A");
        items.add("LINGÜISTA");
        items.add("MATEMÁTICO/A");
        items.add("MÉDICO CIRUJANO");
        items.add("MÚSICO");
        items.add("ODONTÓLOGO/A");
        items.add("PALEONTÓLOGO/A");
        items.add("PARAMÉDICO");
        items.add("PERIODISTA");
        items.add("POLITÓLOGO/A");
        items.add("PROFESOR/A");
        items.add("PSICOANALISTA");
        items.add("PSICÓLOGO/A");
        items.add("QUÍMICO/A");
        items.add("RADIÓLOGO/A");
        items.add("SECRETARIO/A");
        items.add("SOCIÓLOGO/A");
        items.add("TÉCNICO/A DE SONIDO");
        items.add("TÉCNICO/A EN COMPUTACIÓN");
        items.add("TÉCNICO/A EN TURISMO");
        items.add("TRADUCTOR/A");
        items.add("OTRA PROFESIÓN");

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
        items.add("ALBAÑILERÍA");
        items.add("ALUMINIO Y VIDRIO");
        items.add("ANIMACIÓN");
        items.add("ARTESANÍA");
        items.add("BARREDORES");
        items.add("CAJEROS");
        items.add("CARNICERÍA");
        items.add("CARPINTERÍA");
        items.add("CERRAJERÍA");
        items.add("COCINA");
        items.add("COSTURA");
        items.add("ELECTRICIDAD");
        items.add("ESCRITURA");
        items.add("FRUTERÍA");
        items.add("GANADERÍA");
        items.add("IMPRENTA");
        items.add("JARDINERÍA");
        items.add("LAVANDERÍA");
        items.add("LIMPIEZA");
        items.add("MANEJO/CONDUCCIÓN DE VEICULOS");
        items.add("MECÁNICA");
        items.add("MESEROS/CAMAREROS");
        items.add("OBREROS");
        items.add("OTRAS VENTAS/OTROS COMERCIOS");
        items.add("PANADERÍA");
        items.add("PELUQUERÍA");
        items.add("PESCA");
        items.add("PINTURA DE BROCHA GORDA");
        items.add("PLOMERÍA");
        items.add("POLICÍA");
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
        items.add("CONDICIÓN MEDICA SEVERA/ENF. CRÓNICAS");
        items.add("DISCAPACIDAD");
        items.add("ENF. CATASTRÓFICAS");
        items.add("HOGAR MONOPARENTAL");
        items.add("MUJER EMBARAZADA");
        items.add("MUJER LACTANTE");
        items.add("NECESIDAD DE PROTECCIÓN INTERNACIONAL");
        items.add("NNA NO ACOMPAÑADO/SEPARADO");
        items.add("POBLACION LGBTIQ+");
        items.add("SOBREVIVIENTE DE TORTURA");
        items.add("VIOLENCIA BASADA EN GÉNERO");
        items.add("VIOLENCIA INTRAFAMILIAR");
        items.add("OTRA (TRATA, PERSECUCIÓN, EXPLOTACIÓN)");

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
        items.add("RECIÉN LLEGADOS A PAÍS");
        items.add("SITUACIÓN DE CALLE");
        items.add("DESALOJO");
        items.add("DEUDA ARRIENDO (CUANTOS MESES)");
        items.add("CONDICIONES ESTRUCTURALES DEFICIENTES EN VIVIENDA");
        items.add("ORIENTACIÓN SOBRE DERECHOS DE VIVIENDA Y PROPIEDAD");
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

        Componente proteccion = new Componente("PROTECCIÓN", State.ACTIVE);
        componentes.add(proteccion);
        Componente habitabilidad = new Componente("HABITABILIDAD", State.ACTIVE);
        componentes.add(habitabilidad);
        Componente inclusion = new Componente("INCLUSIÓN SOCIOECONÓMICA", State.ACTIVE);
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
        itemsproteccion.add("ACTIVIDAD DE AUTOGESTIÓN COMUNITARIA");
        itemsproteccion.add("ACTIVIDAD DE PLANIFICACIÓN COMUNITARIA");
        itemsproteccion.add("APOYO PSICOSOCIAL");
        itemsproteccion.add("ASESORÍA LEGAL");
        itemsproteccion.add("CBI MULTIPROPÓSITO");
        itemsproteccion.add("FORMACIÓN / FORTALECIMIENTO COMUNITARIO");
        itemsproteccion.add("FORMACIÓN LIDER/LIDERESA COMUNITARIO/A");
        itemsproteccion.add("KIT DE ALIMENTOS");
        itemsproteccion.add("KIT DE BIOSEGURIDAD");
        itemsproteccion.add("ORIENTACIÓN PARA ACCESO A ASILO");
        itemsproteccion.add("ORIENTACIÓN PARA ACCESO A SERVICIOS MIGRATORIOS");
        itemsproteccion.add("PARTICICIPANTE COMITÉ VECINOS ACTIVADOS");
        itemsproteccion.add("PARTICIPANTE DIAGNÓSTICO PARTICIPATIVO");
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
        itemshabitabilidad.add("GEOREFERENCIACIÓN DE VIVIENDA");
        itemshabitabilidad.add("KIT HABITABILIDAD");
        itemshabitabilidad.add("ORIENTACIÓN SOBRE DERECHOS DE VIVIENDA Y PROPIEDAD");
        itemshabitabilidad.add("RELOCALIZACIÓN A ESPACIO SEGURO");
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
        itemsinclusion.add("CAPACITACIÓN PARA ACCESO A EMPLEO O EMPRENDIMIENTO");
        itemsinclusion.add("CAPITAL SEMILLA PARA EMPRENDIMIENTO");
        itemsinclusion.add("KIT PARA EMPRENDIMIENTO");
        itemsinclusion.add("ORIENTACIÓN PARA ACCESO A EMPLEO O EMPRENDIMIENTO");
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
        itemsotro.add("DERIVACIÓN CASO");
        itemsotro.add("GESTIÓN Y A COMPAÑAMIENTO DE CASO");
        itemsotro.add("INFORMACIÓN SOBRE ACCESO A SERVICIOS Y DERECHOS ");
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
        items.add("ACCESO A ALIMENTACIÓN");
        items.add("ACCESO A EDUCACIÓN");
        items.add("ACCESO A SALUD");
        items.add("ACCESO A VIVIENDA");
        items.add("APOYO PSICOSOCIAL");
        items.add("ASESORÍA LEGAL-DERECHOS LABORALES");
        items.add("ASESORÍA LEGAL-DERECHOS VIVIENDA");
        items.add("ASESORÍA LEGAL-REGULARIZACIÓN MIGRATORIA");
        items.add("ASESORÍA LEGAL-OTRAS ÁREAS");
        items.add("CAPACITACIÓN/TALLER");
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
        items.add("ALAS DE COLIBRÍ");

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
        items.add("ASESORÍA LEGAL");
        items.add("ACCESO A MÉTODOS ANTICONCEPTIVOS /MÉTODOS PREVENTIVOS");
        items.add("CAPACITACIÓN/TALLER EN TEMAS DE PROTECCIÓN");
        items.add("CAPACITACIÓN/TALLER PARA ACCESO A EMPLEO O EMPRENDIMIENTO");
        items.add("CAPITAL SEMILLA PARA EMPRENDIMIENTO");
        items.add("CBI ARRIENDO");
        items.add("CBI MULTIPROPOSITO");
        items.add("EQUIPOS DE PROTECCIÓN PERSONAL (EPP) O KITS DE BIOSEGURIDAD");
        items.add("INFORMACIÓN SOBRE ACCESO A SERVICIOS Y DERECHOS");
        items.add("KIT DE ALIMENTOS");
        items.add("KIT DIGNIDAD");
        items.add("KIT EDUCATIVO");
        items.add("KIT HABITABILIDAD");
        items.add("KIT HIGIENE");
        items.add("ORIENTACIÓN PARA ACCESO A ASILO/REFUGIO");
        items.add("ORIENTACIÓN PARA ACCESO A SERVICIOS MIGRATORIOS");
        items.add("PROVISIÓN DE ALOJAMIENTO TEMPORAL");
        items.add("PROVISIÓN DE PRUEBAS RÁPIDAS (SALUD)");
        items.add("SENSIBILIZACIÓN SOBRE TEMAS DE SALUD Y HÁBITOS SALUDABLES");
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

