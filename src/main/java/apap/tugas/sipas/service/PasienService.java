package apap.tugas.sipas.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sipas.model.PasienModel;

public interface PasienService{
    void addPasien(PasienModel pasien);
    Optional<PasienModel> getPasienById(Long id);
    Optional<PasienModel> getPasienByNik(String nik);
    List<PasienModel> getAllPasien();
    PasienModel changePasien(PasienModel pasien);
    void removePasien(PasienModel pasien);
}
