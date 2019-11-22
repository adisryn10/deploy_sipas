package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.repository.DiagnosisPenyakitDb;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiagnosisPenyakitServiceImpl implements DiagnosisPenyakitService {

    @Autowired
    private DiagnosisPenyakitDb diagnosisPenyakitDb;

    @Override
    public void addDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit) {
        diagnosisPenyakitDb.save(diagnosisPenyakit);
    }

    @Override
    public Optional<DiagnosisPenyakitModel> getDiagnosisPenyakitById(Long id){
        return diagnosisPenyakitDb.findById(id);
    }
    
    @Override
    public List<DiagnosisPenyakitModel> getAllDiagnosis(){
        List<DiagnosisPenyakitModel> listDiagnosis =  diagnosisPenyakitDb.findAll();
        return listDiagnosis;
    }

    @Override
    public DiagnosisPenyakitModel changeDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakitModel) {
        DiagnosisPenyakitModel targetDiagnosisPenyakit = diagnosisPenyakitDb.findById(diagnosisPenyakitModel.getId()).get();
        try {
            targetDiagnosisPenyakit.setNama(diagnosisPenyakitModel.getNama());
            targetDiagnosisPenyakit.setKode(diagnosisPenyakitModel.getKode());
            diagnosisPenyakitDb.save(targetDiagnosisPenyakit);
            return targetDiagnosisPenyakit;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void removeDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit) {
        diagnosisPenyakitDb.delete(diagnosisPenyakit);
    }
}