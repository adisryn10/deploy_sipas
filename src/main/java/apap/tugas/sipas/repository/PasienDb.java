package apap.tugas.sipas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipas.model.PasienModel;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long>{
    Optional<PasienModel> findById(Long id);
    Optional<PasienModel> findByNik(String nik);
    List<PasienModel> findAll();
    List<PasienModel> findByListAsuransiId(Long idAsuransi);
}