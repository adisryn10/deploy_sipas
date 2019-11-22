package apap.tugas.sipas.service;

import java.util.Optional;

import apap.tugas.sipas.model.EmergencyContactModel;

public interface EmergencyContactService{
    void addEmergencyContact(EmergencyContactModel emergencyContact);
    Optional<EmergencyContactModel> getEmergencyContactById(Long id);
    EmergencyContactModel changeEmergencyContact(EmergencyContactModel emergencyContact);
}
