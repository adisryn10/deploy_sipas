package apap.tugas.sipas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;

@Repository
public interface PasienDiagnosisPenyakitDb extends JpaRepository<PasienDiagnosisPenyakitModel, Long>{
    Optional<PasienDiagnosisPenyakitModel> findById(Long id);
    List<PasienDiagnosisPenyakitModel> findAll();
    List<PasienDiagnosisPenyakitModel> findByPasienNik(String nik);
    List<PasienDiagnosisPenyakitModel> findByPasienId(Long id);
    List<PasienDiagnosisPenyakitModel> findByDiagnosisPenyakitId(Long id);
    List<PasienDiagnosisPenyakitModel> findByPasienListAsuransiId(Long idAsuransi);
    List<PasienDiagnosisPenyakitModel> findByPasienJenisKelaminAndDiagnosisPenyakitId(Integer jenisKelamin, Long idDiagnosis);
	List<PasienDiagnosisPenyakitModel> findByPasienListAsuransiIdAndDiagnosisPenyakitId(Long idAsuransi, Long idDiagnosis);
}