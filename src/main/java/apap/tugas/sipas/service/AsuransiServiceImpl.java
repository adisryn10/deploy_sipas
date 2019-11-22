package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.repository.AsuransiDb;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsuransiServiceImpl implements AsuransiService {

    @Autowired
    private AsuransiDb asuransiDb;

    @Override
    public Optional<AsuransiModel> getAsuransiById(Long id){
        return asuransiDb.findById(id);
    }
    
    @Override
    public List<AsuransiModel> getAllAsuransi(){
        List<AsuransiModel> listAsuransi =  asuransiDb.findAll();
        return listAsuransi;
    }
}