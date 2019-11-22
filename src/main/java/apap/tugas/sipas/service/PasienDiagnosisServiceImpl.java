package apap.tugas.sipas.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.repository.PasienDiagnosisPenyakitDb;

@Service
@Transactional
public class PasienDiagnosisServiceImpl implements PasienDiagnosisService {

    @Autowired
    private PasienDiagnosisPenyakitDb pasienDiagnosisPenyakitDb;

    @Override
    public void addPasienDiagnosisPenyakit(PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit) {
        pasienDiagnosisPenyakitDb.save(pasienDiagnosisPenyakit);

    }

    @Override
    public Optional<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitById(Long id) {
        return pasienDiagnosisPenyakitDb.findById(id);
    }

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitByPasienNik(String nik) {
        return pasienDiagnosisPenyakitDb.findByPasienNik(nik);
    }

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitByDiagnosisPenyakitId(Long id){
        return pasienDiagnosisPenyakitDb.findByDiagnosisPenyakitId(id);
    }

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienDiagnosisByPasienListAsuransiIdAndDiagnosisId(Long idAsuransi, Long idDiagnosis) {
        return pasienDiagnosisPenyakitDb.findByPasienListAsuransiIdAndDiagnosisPenyakitId(idAsuransi, idDiagnosis);
    }

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienDiagnosisByPasienJenisKelaminAndDiagnosisId(Integer jenisKelamin,
            Long idDiagnosis) {
        return pasienDiagnosisPenyakitDb.findByPasienJenisKelaminAndDiagnosisPenyakitId(jenisKelamin, idDiagnosis);
    }

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitByPasienId(Long id) {
        return pasienDiagnosisPenyakitDb.findByPasienId(id);
    }

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienDiagnosisByPasienListAsuransiId(Long idAsuransi) {
        return pasienDiagnosisPenyakitDb.findByPasienListAsuransiId(idAsuransi);
    }
}