package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import java.util.Optional;
import java.util.List;

public interface AsuransiService{
    Optional<AsuransiModel> getAsuransiById(Long id);
    List<AsuransiModel> getAllAsuransi();
}
