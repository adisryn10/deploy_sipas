package apap.tugas.sipas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pasienDiagnosisPenyakit")
public class PasienDiagnosisPenyakitModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="tanggalDiagnosis", nullable = false)
    private Date tanggalDiagnosis;

    @ManyToOne
    @JoinColumn(name = "idPasien")
    PasienModel pasien;

    @ManyToOne
    @JoinColumn(name = "idDiagnosisPenyakit")
    DiagnosisPenyakitModel diagnosisPenyakit;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggalDiagnosis(){
        return tanggalDiagnosis;
    }
    public void setTanggalDiagnosis(Date tanggalDiagnosis){
        this.tanggalDiagnosis = tanggalDiagnosis;
    }
    public PasienModel getPasien(){
        return pasien;
    }
    public void setPasien(PasienModel pasien){
        this.pasien = pasien;
    }
    public DiagnosisPenyakitModel getDiagnosisPenyakit(){
        return diagnosisPenyakit;
    }
    public void setDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit){
        this.diagnosisPenyakit = diagnosisPenyakit;
    }
}