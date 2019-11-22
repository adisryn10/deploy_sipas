package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import java.util.Optional;
import java.util.List;

public interface DiagnosisPenyakitService{
    void addDiagnosisPenyakit(DiagnosisPenyakitModel pasien);
    Optional<DiagnosisPenyakitModel> getDiagnosisPenyakitById(Long id);
    List<DiagnosisPenyakitModel> getAllDiagnosis();
    DiagnosisPenyakitModel changeDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit);
    void removeDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit);
}
