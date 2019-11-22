package apap.tugas.sipas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="diagnosisPenyakit")
public class DiagnosisPenyakitModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name="kode", nullable = false)
    private String kode;

    @OneToMany(mappedBy = "diagnosisPenyakit")
    private List<PasienDiagnosisPenyakitModel> pasienDiagnosisPenyakit;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getKode(){
        return kode;
    }
    public void setKode(String kode){
        this.kode = kode;
    }
    public List<PasienDiagnosisPenyakitModel> getListPasienDiagnosis(){
        return pasienDiagnosisPenyakit;
    }
    public void setListPasienDiagnosis(List<PasienDiagnosisPenyakitModel> pasienDiagnosisPenyakit){
        this.pasienDiagnosisPenyakit = pasienDiagnosisPenyakit;
    }
}