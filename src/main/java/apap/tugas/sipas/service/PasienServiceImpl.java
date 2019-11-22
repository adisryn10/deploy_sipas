package apap.tugas.sipas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
    @Qualifier("emergencyContactServiceImpl")
    @Autowired
    private EmergencyContactService emergencyContactService;

    @Autowired
    private PasienDb pasienDb;

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public Optional<PasienModel> getPasienByNik(String nik){
        return pasienDb.findByNik(nik);
    }

    @Override
    public Optional<PasienModel> getPasienById(Long id){
        return pasienDb.findById(id);
    }

    @Override
    public List<PasienModel> getAllPasien(){
        return pasienDb.findAll();
    }

    @Override
    public PasienModel changePasien(PasienModel pasien){
        PasienModel targetPasien = pasienDb.findById(pasien.getId()).get();
        try {
            targetPasien.setNama(pasien.getNama());
            targetPasien.setNik(pasien.getNik());
            targetPasien.setJenisKelamin(pasien.getJenisKelamin());
            targetPasien.setTempatLahir(pasien.getTempatLahir());
            targetPasien.setTanggalLahir(pasien.getTanggalLahir());

            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear() + 5;
            String tahun = String.valueOf(year);
            String tgl = pasien.getTanggalLahir();
            Random rnd = new Random();
            char char1 = (char) (rnd.nextInt(26) + 'A');
            char char2 = (char) (rnd.nextInt(26) + 'A');
            String date = tgl.substring(8,10) + tgl.substring(5,7) + tgl.substring(2,4);
            String kode = tahun + date + pasien.getJenisKelamin() + char1 + char2;
            targetPasien.setKode(kode);

            EmergencyContactModel emergencyContact = emergencyContactService.changeEmergencyContact(pasien.getEmergencyContact());
            targetPasien.setEmergencyContact(pasien.getEmergencyContact());
            pasienDb.save(targetPasien);
            return targetPasien;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void removePasien(PasienModel pasien){
        pasienDb.delete(pasien);
    }
}