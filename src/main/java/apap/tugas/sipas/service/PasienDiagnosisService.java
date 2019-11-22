package apap.tugas.sipas.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;

public interface PasienDiagnosisService{
    void addPasienDiagnosisPenyakit(PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit);
    Optional<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitById(Long id);
    List<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitByPasienNik(String nik);
    List<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitByPasienId(Long id);
    List<PasienDiagnosisPenyakitModel> getPasienDiagnosisByPasienListAsuransiId(Long idAsuransi);
    List<PasienDiagnosisPenyakitModel> getPasienDiagnosisPenyakitByDiagnosisPenyakitId(Long id);
    List<PasienDiagnosisPenyakitModel> getPasienDiagnosisByPasienListAsuransiIdAndDiagnosisId(Long idAsuransi, Long idDiagnosis);
    List<PasienDiagnosisPenyakitModel> getPasienDiagnosisByPasienJenisKelaminAndDiagnosisId(Integer jenisKelamin, Long idDiagnosis);
}
