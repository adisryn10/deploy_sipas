package apap.tugas.sipas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.repository.EmergencyContactDb;

@Service
@Transactional
public class EmergencyContactServiceImpl implements EmergencyContactService {

    @Autowired
    private EmergencyContactDb emergencyContactDb;

    @Override
    public void addEmergencyContact(EmergencyContactModel emergencyContact) {
        emergencyContactDb.save(emergencyContact);
    }

    @Override
    public Optional<EmergencyContactModel> getEmergencyContactById(Long id){
        return emergencyContactDb.findById(id);
    }

    @Override
    public EmergencyContactModel changeEmergencyContact(EmergencyContactModel emergencyContact){
        EmergencyContactModel targetEmergencyContact = emergencyContactDb.findById(emergencyContact.getId()).get();
        try {
            targetEmergencyContact.setNama(emergencyContact.getNama());
            targetEmergencyContact.setNik(emergencyContact.getNik());
            targetEmergencyContact.setNoHp(emergencyContact.getNoHp());

            emergencyContactDb.save(targetEmergencyContact);
            return targetEmergencyContact;
        } catch (NullPointerException nullException) {
            return null;
        }
    }


}