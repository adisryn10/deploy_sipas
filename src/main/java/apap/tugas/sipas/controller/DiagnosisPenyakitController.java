package apap.tugas.sipas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.service.DiagnosisPenyakitService;
import apap.tugas.sipas.service.PasienDiagnosisService;

@Controller
public class DiagnosisPenyakitController{
    @Qualifier("diagnosisPenyakitServiceImpl")
    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Qualifier("pasienDiagnosisServiceImpl")
    @Autowired
    private PasienDiagnosisService pasienDiagnosisService;

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.GET)
    public String addDiagnosisPenyakitFormPage(Model model){
        DiagnosisPenyakitModel diagnosisPenyakit = new DiagnosisPenyakitModel();
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
        return "form-add-diagnosis-penyakit";
    }
    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.POST)
    public String addDiagnosisPenyakitSubmit(@ModelAttribute DiagnosisPenyakitModel diagnosisPenyakit, Model model){
        diagnosisPenyakitService.addDiagnosisPenyakit(diagnosisPenyakit);
        return "add-diagnosis-penyakit";
    }

    @RequestMapping(path = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String diagnosisPenyakitAll(Model model){
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisPenyakitService.getAllDiagnosis();
        model.addAttribute("listDiagnosis", listDiagnosis);
        return "view-diagnosis-all";
    }
    
    @RequestMapping(path = "/diagnosis-penyakit/view", method = RequestMethod.GET)
    public String view(
            @RequestParam(value = "idDiagnosisPenyakit") Long idDiagnosisPenyakit, Model model
            ){
        
        DiagnosisPenyakitModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosisPenyakit).get();
        List<PasienDiagnosisPenyakitModel> diagnosisPasien = pasienDiagnosisService.getPasienDiagnosisPenyakitByDiagnosisPenyakitId(idDiagnosisPenyakit);
        
        model.addAttribute("diagnosisPasien", diagnosisPasien);
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);

        return "view-diagnosis-penyakit";
    }

    @RequestMapping(path = "/diagnosis-penyakit/hapus", method = RequestMethod.POST)
    public String deleteWithPathVariable(
        @RequestParam(value = "idDiagnosis") Long idDiagnosis, Model model
            ){
        
        List<PasienDiagnosisPenyakitModel> pasienTerdiagnosis = pasienDiagnosisService.getPasienDiagnosisPenyakitByDiagnosisPenyakitId(idDiagnosis);     
        
        if(pasienTerdiagnosis.size() > 0){
            return "failed-delete-diagnosis";
        }
        else{
            DiagnosisPenyakitModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosis).get();
            model.addAttribute("diagnosisPenyakit", diagnosisPenyakit.getNama());
            diagnosisPenyakitService.removeDiagnosisPenyakit(diagnosisPenyakit);
            return "delete-diagnosis-penyakit";
        }
    }

    @RequestMapping(value = "/diagnosis-penyakit/change/{idDiagnosisPenyakit}", method = RequestMethod.GET)
    public String changeDiagnosisPenyakitFormPage(@PathVariable Long idDiagnosisPenyakit, Model model){
        DiagnosisPenyakitModel existingDiagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosisPenyakit).get();
        model.addAttribute("diagnosisPenyakit", existingDiagnosisPenyakit);
        return "form-change-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/change/{idDiagnosisPenyakit}", method = RequestMethod.POST)
    public String changeDiagnosisPenyakitFormPage(@ModelAttribute DiagnosisPenyakitModel diagnosisPenyakit, Model model){
        DiagnosisPenyakitModel newDiagnosisPenyakit = diagnosisPenyakitService.changeDiagnosisPenyakit(diagnosisPenyakit);
        model.addAttribute("diagnosisPenyakit", newDiagnosisPenyakit);
        return "change-diagnosis-penyakit";
    }

}