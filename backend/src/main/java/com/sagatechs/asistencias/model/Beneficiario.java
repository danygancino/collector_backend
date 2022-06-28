package com.sagatechs.asistencias.model;

import com.sagatechs.asistencias.model.catalogs.*;
import com.sagatechs.generics.persistence.model.BaseEntity;
import com.sagatechs.generics.persistence.model.audit.AuditListener;
import com.sagatechs.generics.persistence.model.audit.Auditable;
import com.sagatechs.generics.persistence.model.audit.Auditoria;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "beneficiarios", schema = "asistencias")
@EntityListeners(AuditListener.class)
public class Beneficiario extends BaseEntity<UUID> implements Auditable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "ultima_fecha_actualizacion", nullable = false)
    public LocalDateTime ultimaFechaActualizacion;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nivel_parentesco_id", foreignKey = @ForeignKey(name = "fk_beneficiario_nivel_parentesco"))
    private NivelParentesco nivelParentesco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sexo_id", foreignKey = @ForeignKey(name = "fk_beneficiario_sexo"))
    private Sexo sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Transient
    private Integer edad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genero_id", foreignKey = @ForeignKey(name = "fk_beneficiario_genero"))
    private Genero genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lgbti_id", foreignKey = @ForeignKey(name = "fk_beneficiario_lgbti"))
    private Lgbti lgbti;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "etnia_id", foreignKey = @ForeignKey(name = "fk_beneficiario_etnia"))
    private Etnia etnia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nacionalidad_id", foreignKey = @ForeignKey(name = "fk_beneficiario_nacionalidad"))
    private Nacionalidad nacionalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discapacidad_id", foreignKey = @ForeignKey(name = "fk_beneficiario_discapacidad"))
    private Discapacidad discapacidad;

    @Column(name = "fecha_ingreso_pais")
    private LocalDate fechaIngresoPais;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_documento_id", foreignKey = @ForeignKey(name = "fk_beneficiario_tipo_documento"))
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estatuto_migratorio_id", foreignKey = @ForeignKey(name = "fk_beneficiario_estatuto_migratorio"))
    private EstatutoMigratorio estatutoMigratorio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "situacion_migratoria_id", foreignKey = @ForeignKey(name = "fk_beneficiario_situacion_migratoria"))
    private SituacionMigratoria situacionMigratoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "motivo_salida_pais_id", foreignKey = @ForeignKey(name = "fk_beneficiario_motivo_salida"))
    private MotivoSalidaPais motivoSalidaPais;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudad_id", foreignKey = @ForeignKey(name = "fk_beneficiario_ciudad"))
    private Ciudad ciudad;

    @Column(name = "sector")
    private String sector;

    @Column(name = "direccion", columnDefinition = "text")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nivel_escolaridad_id", foreignKey = @ForeignKey(name = "fk_beneficiario_nivel_escolaridad"))
    private NivelEscolaridad nivelEscolaridad;

    @ManyToMany
    @JoinTable(name = "beneficiarios_profesiones",
            joinColumns = @JoinColumn(name = "beneficiario_id",foreignKey = @ForeignKey(name = "fk_beneficiario_profesion")),
            inverseJoinColumns = @JoinColumn(name = "profesion_id",foreignKey = @ForeignKey(name = "fk_profesion_beneficiario"))
    )
    private Set<Profesion> profesiones = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "beneficiarios_oficios",
            joinColumns = @JoinColumn(name = "beneficiario_id",foreignKey = @ForeignKey(name = "fk_beneficiario_oficio")),
            inverseJoinColumns = @JoinColumn(name = "oficio_id",foreignKey = @ForeignKey(name = "fk_oficio_beneficiario"))
    )
    private Set<Oficio> oficios = new HashSet<>();

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "grupo_familiar_id", foreignKey = @ForeignKey(name = "fk_beneficiario_grupo_familiar"))
    private GrupoFamiliar grupoFamiliar;

    @Embedded
    protected Auditoria auditoria;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Auditoria getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public NivelParentesco getNivelParentesco() {
        return nivelParentesco;
    }

    public void setNivelParentesco(NivelParentesco nivelParentesco) {
        this.nivelParentesco = nivelParentesco;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        if (this.fechaNacimiento != null) {
            Period diff = Period.between(this.fechaNacimiento, LocalDate.now());

            this.edad = diff.getYears();
        }
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Lgbti getLgbti() {
        return lgbti;
    }

    public void setLgbti(Lgbti lgbti) {
        this.lgbti = lgbti;
    }

    public Etnia getEtnia() {
        return etnia;
    }

    public void setEtnia(Etnia etnia) {
        this.etnia = etnia;
    }

    public Discapacidad getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(Discapacidad discapacidad) {
        this.discapacidad = discapacidad;
    }

    public LocalDate getFechaIngresoPais() {
        return fechaIngresoPais;
    }

    public void setFechaIngresoPais(LocalDate fechaIngresoPais) {
        this.fechaIngresoPais = fechaIngresoPais;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public EstatutoMigratorio getEstatutoMigratorio() {
        return estatutoMigratorio;
    }

    public void setEstatutoMigratorio(EstatutoMigratorio estatutoMigratorio) {
        this.estatutoMigratorio = estatutoMigratorio;
    }

    public SituacionMigratoria getSituacionMigratoria() {
        return situacionMigratoria;
    }

    public void setSituacionMigratoria(SituacionMigratoria situacionMigratoria) {
        this.situacionMigratoria = situacionMigratoria;
    }

    public MotivoSalidaPais getMotivoSalidaPais() {
        return motivoSalidaPais;
    }

    public void setMotivoSalidaPais(MotivoSalidaPais motivoSalidaPais) {
        this.motivoSalidaPais = motivoSalidaPais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public NivelEscolaridad getNivelEscolaridad() {
        return nivelEscolaridad;
    }

    public void setNivelEscolaridad(NivelEscolaridad nivelEscolaridad) {
        this.nivelEscolaridad = nivelEscolaridad;
    }

    public Set<Profesion> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(Set<Profesion> profesiones) {
        this.profesiones = profesiones;
    }

    public Set<Oficio> getOficios() {
        return oficios;
    }

    public void setOficios(Set<Oficio> oficios) {
        this.oficios = oficios;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public GrupoFamiliar getGrupoFamiliar() {
        return grupoFamiliar;
    }

    public void setGrupoFamiliar(GrupoFamiliar grupoFamiliar) {
        this.grupoFamiliar = grupoFamiliar;
    }


    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDateTime getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(LocalDateTime ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
}
