package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.AsuransiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface AsuransiDb extends JpaRepository<AsuransiModel, Long>{
    Optional<AsuransiModel> findById(Long id);
    List<AsuransiModel> findAll();
}