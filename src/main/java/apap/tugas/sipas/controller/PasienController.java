package apap.tugas.sipas.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.DiagnosisPenyakitService;
import apap.tugas.sipas.service.EmergencyContactService;
import apap.tugas.sipas.service.PasienDiagnosisService;
import apap.tugas.sipas.service.PasienService;

@Controller
public class PasienController{
    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("asuransiServiceImpl")
    @Autowired
    private AsuransiService asuransiService;

    @Qualifier("emergencyContactServiceImpl")
    @Autowired
    private EmergencyContactService emergencyContactService;

    @Qualifier("diagnosisPenyakitServiceImpl")
    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Qualifier("pasienDiagnosisServiceImpl")
    @Autowired
    private PasienDiagnosisService pasienDiagnosisService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model){
        List<PasienModel> listPasien = pasienService.getAllPasien();
        model.addAttribute("listPasien", listPasien);
        return "home";
    }


    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String addPasienFormPage(Model model){
        PasienModel pasien = new PasienModel();
        List<AsuransiModel> asuransiBaru = new ArrayList<AsuransiModel>();
        asuransiBaru.add(new AsuransiModel());

        pasien.setListAsuransi(asuransiBaru);

        EmergencyContactModel emergencyContacts = new EmergencyContactModel();
        pasien.setEmergencyContact(emergencyContacts);

        List<AsuransiModel> listAsuransi = asuransiService.getAllAsuransi();
        model.addAttribute("asuransiData", listAsuransi);
        model.addAttribute("pasien", pasien);

        return "form-add-pasien";
    }

    @PostMapping(value = "/pasien/tambah", params = {"tambahAsuransi"})
    public String tambahAsuransi(@ModelAttribute PasienModel pasien, Model model){
        if(pasien.getListAsuransi() == null){
            pasien.setListAsuransi(new ArrayList<AsuransiModel>());
        }
        pasien.getListAsuransi().add(new AsuransiModel());
        List<AsuransiModel> listAsuransi = asuransiService.getAllAsuransi();
        model.addAttribute("asuransiData", listAsuransi);
        model.addAttribute("pasien", pasien);
        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST, params = {"savePasien"})
    public String addPasienSubmit(@ModelAttribute PasienModel pasien, @ModelAttribute EmergencyContactModel emergencyContact,Model model){
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear() + 5;
        String tahun = String.valueOf(year);
        String tgl = pasien.getTanggalLahir();
        Random rnd = new Random();
        char char1 = (char) (rnd.nextInt(26) + 'A');
        char char2 = (char) (rnd.nextInt(26) + 'A');
        String date = tgl.substring(8,10) + tgl.substring(5,7) + tgl.substring(2,4);
        String kode = tahun + date + pasien.getJenisKelamin() + char1 + char2;

        pasien.setKode(kode);
        pasien.setEmergencyContact(pasien.getEmergencyContact());
        pasien.setListAsuransi(pasien.getListAsuransi());

        emergencyContactService.addEmergencyContact(pasien.getEmergencyContact());
        pasienService.addPasien(pasien);

        model.addAttribute("kode", kode);
        return "add-pasien";
    }

    @RequestMapping(path = "/pasien", method = RequestMethod.GET)
    public String view(
            @RequestParam(value = "nikPasien") String nikPasien, Model model
            ){
        
        PasienModel pasien = pasienService.getPasienByNik(nikPasien).get();
        List<PasienDiagnosisPenyakitModel> diagnosisPasien = pasienDiagnosisService.getPasienDiagnosisPenyakitByPasienNik(nikPasien);
        
        model.addAttribute("diagnosisPasien", diagnosisPasien);
        model.addAttribute("pasien", pasien);

        return "view-pasien";
    }
    @RequestMapping(value = "/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.GET)
    public String addViewPasienDiagnosis(
            @PathVariable String nikPasien, Model model
        ){
        
        PasienModel pasien = pasienService.getPasienByNik(nikPasien).get();
        List<DiagnosisPenyakitModel> diagnosisPenyakitList = diagnosisPenyakitService.getAllDiagnosis();
        List<PasienDiagnosisPenyakitModel> diagnosisPasien = pasienDiagnosisService.getPasienDiagnosisPenyakitByPasienNik(nikPasien);
        
        model.addAttribute("diagnosisPasien", diagnosisPasien);
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitList);
        model.addAttribute("pasien", pasien);

        PasienDiagnosisPenyakitModel pasienDiagnosis = new PasienDiagnosisPenyakitModel();
        model.addAttribute("pasienDiagnosis", pasienDiagnosis);

        return "view-pasien-tambah-diagnosis";
    }

    @RequestMapping(value = "/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.POST)
    public String addDiagnosisPasienPage(@ModelAttribute PasienDiagnosisPenyakitModel pasienDiagnosis, @PathVariable String nikPasien, Model model){
        LocalDateTime now = LocalDateTime.now();
        Date nowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formatter.format(nowDate);

        Long idDiagnosis = pasienDiagnosis.getDiagnosisPenyakit().getId();
        DiagnosisPenyakitModel diagnosis = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosis).get();
        
        PasienModel pasien = pasienService.getPasienByNik(nikPasien).get();
        pasienDiagnosis.setPasien(pasien);
        pasienDiagnosis.setTanggalDiagnosis(nowDate);
        pasienDiagnosisService.addPasienDiagnosisPenyakit(pasienDiagnosis);
        model.addAttribute("tanggalTambah", dateString);
        model.addAttribute("namaPasien", pasienDiagnosis.getPasien().getNama());
        model.addAttribute("namaPenyakit", diagnosis.getNama());
        return "add-diagnosis-penyakit-pasien";
    }

    @RequestMapping(value = "/pasien/ubah/{nikPasien}", method = RequestMethod.GET)
    public String changePasienFormPage(@PathVariable String nikPasien, Model model){
        PasienModel existingPasien = pasienService.getPasienByNik(nikPasien).get();
        model.addAttribute("pasien", existingPasien);
        return "form-change-pasien";
    }

    @RequestMapping(value = "/pasien/ubah/{nikPasien}", method = RequestMethod.POST)
    public String changePasienPage(@ModelAttribute PasienModel pasien, Model model){
        PasienModel newPasien = pasienService.changePasien(pasien);
        model.addAttribute("pasien", newPasien);
        return "change-pasien";
    }

    @RequestMapping(path = "/pasien/hapus", method = RequestMethod.POST)
    public String deleteWithPathVariable(
        @RequestParam(value = "idPasien") Long idPasien, Model model
            ){
        
        
        PasienModel pasien = pasienService.getPasienById(idPasien).get();
        model.addAttribute("pasien", pasien.getNama());
        List<PasienDiagnosisPenyakitModel> penyakitPasien = pasienDiagnosisService.getPasienDiagnosisPenyakitByPasienId(idPasien);
        if(penyakitPasien.size() > 0){
            return "failed-delete-pasien";
        }
        else{
            pasienService.removePasien(pasien);
            return "delete-pasien";
        }
    }

    @RequestMapping(path = "/pasien/cari", method = RequestMethod.GET)
    public String cariPasien(
            @RequestParam(value = "idAsuransi", required=false) Long idAsuransi,
            @RequestParam(value = "idDiagnosis", required=false) Long idDiagnosis, 
            Model model
            ){

        List<PasienDiagnosisPenyakitModel> pasienDiagnosisDicari = null;
        
        if(idDiagnosis == null){
            AsuransiModel asuransi = asuransiService.getAsuransiById(idAsuransi).get();
            List<PasienDiagnosisPenyakitModel> pasienCurrent = pasienDiagnosisService.getPasienDiagnosisByPasienListAsuransiId(idAsuransi);
            // Set<String> set = new HashSet<>(pasienCurrent.size());
            // pasienCurrent.removeIf(p -> !set.add(p.getPasien().getNik()));
            pasienDiagnosisDicari = pasienCurrent;
            model.addAttribute("namaAsuransi", asuransi.getNama());
        }
        else if(idAsuransi == null){
            DiagnosisPenyakitModel diagnosis = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosis).get();
            pasienDiagnosisDicari = pasienDiagnosisService.getPasienDiagnosisPenyakitByDiagnosisPenyakitId(idDiagnosis);
            model.addAttribute("namaPenyakit", diagnosis.getNama());
        }   
        else{
            AsuransiModel asuransi = asuransiService.getAsuransiById(idAsuransi).get();
            DiagnosisPenyakitModel diagnosis = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosis).get();
            pasienDiagnosisDicari = pasienDiagnosisService.getPasienDiagnosisByPasienListAsuransiIdAndDiagnosisId(idAsuransi,idDiagnosis);
            model.addAttribute("namaAsuransi", asuransi.getNama());
            model.addAttribute("namaPenyakit", diagnosis.getNama());
        }
        
        List<DiagnosisPenyakitModel> diagnosisPenyakitList = diagnosisPenyakitService.getAllDiagnosis();
        List<AsuransiModel> asuransiPasien = asuransiService.getAllAsuransi();
        
        model.addAttribute("pasienDicari", pasienDiagnosisDicari);
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitList);
        model.addAttribute("asuransiPasien", asuransiPasien);
        
        return "cari-pasien";
    }
    @RequestMapping(path = "/pasien/cari/lakilaki-perempuan", method = RequestMethod.GET)
    public String cariPasien(
            @RequestParam(value = "idDiagnosis") Long idDiagnosis,
            Model model
            ){
        
        // List<PasienDiagnosisPenyakitModel> pasienDiagnosisDicari = pasienDiagnosisService.getPasienDiagnosisByPasienListAsuransiIdAndDiagnosisId(idAsuransi,idDiagnosis);
        List<DiagnosisPenyakitModel> diagnosisPenyakitList = diagnosisPenyakitService.getAllDiagnosis();
        List<PasienDiagnosisPenyakitModel> pasienLakiLaki = pasienDiagnosisService.getPasienDiagnosisByPasienJenisKelaminAndDiagnosisId(1, idDiagnosis);
        List<PasienDiagnosisPenyakitModel> pasienPerempuan = pasienDiagnosisService.getPasienDiagnosisByPasienJenisKelaminAndDiagnosisId(0, idDiagnosis);
        DiagnosisPenyakitModel diagnosis = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosis).get();
        
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitList);
        model.addAttribute("lakilaki", pasienLakiLaki.size());
        model.addAttribute("perempuan", pasienPerempuan.size());
        model.addAttribute("namaPenyakit", diagnosis.getNama());

        return "cari-lakilaki-perempuan";
    }
}
