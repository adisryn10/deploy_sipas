package apap.tugas.sipas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="pasien")
public class PasienModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name="nik", nullable = false)
    private String nik;

    @NotNull
    @Size(max = 255)
    @Column(name="kode", nullable = false)
    private String kode;

    @NotNull
    @Size(max = 255)
    @Column(name="nama", nullable = false)
    private String nama;

    @ManyToMany
    @JoinTable(
        name = "pasien_asuransi", 
        joinColumns = @JoinColumn(name = "id_pasien"), 
        inverseJoinColumns = @JoinColumn(name = "id_asuransi"))
    private List<AsuransiModel> listAsuransi;

    @NotNull
    @Column(name="jenisKelamin", nullable = false)
    private int jenisKelamin;

    @NotNull
    @Column(name="tanggalLahir", nullable = false)
    private String tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name="tempatLahir", nullable = false)
    private String tempatLahir;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmergencyContact")
    private EmergencyContactModel emergencyContact;

    @OneToMany(mappedBy = "pasien")
    private List<PasienDiagnosisPenyakitModel> pasienDiagnosisPenyakit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNik(String nik){
        this.nik = nik;
    }
    public String getNik(){
        return nik;
    }
    public void setListAsuransi(List<AsuransiModel> listAsuransi){
        this.listAsuransi = listAsuransi;
    }
    public List<AsuransiModel> getListAsuransi(){
        return listAsuransi;
    }

    public void setKode(String kode){
        this.kode = kode;
    }
    public String getKode(){
        return kode;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(int jenisKelamin){
        this.jenisKelamin = jenisKelamin;
    }
    public int getJenisKelamin(){
        return jenisKelamin;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
    public String getTanggalLahir(){
        return tanggalLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }
    public String getTempatLahir(){
        return tempatLahir;
    }

    public void setEmergencyContact(EmergencyContactModel emergencyContact){
        this.emergencyContact = emergencyContact;
    }
    public EmergencyContactModel getEmergencyContact(){
        return emergencyContact;
    }
    public List<PasienDiagnosisPenyakitModel> getListPasienDiagnosis(){
        return pasienDiagnosisPenyakit;
    }
    public void setListPasienDiagnosis(List<PasienDiagnosisPenyakitModel> pasienDiagnosisPenyakit){
        this.pasienDiagnosisPenyakit = pasienDiagnosisPenyakit;
    }
}