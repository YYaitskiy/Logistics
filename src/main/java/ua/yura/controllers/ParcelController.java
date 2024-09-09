package ua.yura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.yura.dao.CompanyDAO;
import ua.yura.dao.LotDAO;
import ua.yura.dao.PackageDAO;
import ua.yura.dao.SubdivisionDAO;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import java.util.UUID;

@Controller
@RequestMapping("/parcel")
public class ParcelController {

    private LotDAO lotDAO;
    private PackageDAO packageDAO;
    private CompanyDAO companyDAO;
    private SubdivisionDAO subdivisionDAO;



    @Autowired
    public ParcelController(LotDAO lotDAO, PackageDAO packageDAO, CompanyDAO companyDAO, SubdivisionDAO subdivisionDAO) {
        this.lotDAO = lotDAO;
        this.packageDAO = packageDAO;
        this.companyDAO=companyDAO;
        this.subdivisionDAO=subdivisionDAO;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("parcel", lotDAO.show());
        return "parcel/show";
    }

    @GetMapping("/{id}")
    public String id(@PathVariable("id") UUID uuid, Model model){
        model.addAttribute("parcel", lotDAO.indexLot(uuid));

        return "parcel/index";
    }

    @GetMapping("/sent")
    public String sent(Model model){
        model.addAttribute("parcel", lotDAO.sentStatus());
        return "parcel/sent";
    }

    @GetMapping("/receive")
    public String receive(Model model){
        model.addAttribute("parcel", lotDAO.receiveStatus());
        return "parcel/receive";
    }

    @GetMapping("/newLot")
    public String newLot(Model model){
        model.addAttribute("lot", new Lot());
        return "parcel/newLot";
    }

    @PostMapping()
    public String createLot (@ModelAttribute("lot") Lot lot){
        lot.setId(UUID.randomUUID());
        lotDAO.save(lot);
        return "redirect:/parcel/" + lot.getId();
    }

    @GetMapping("/{id}/newMCD")
    public String newPackageMCD (Model model, @PathVariable("id") UUID uuid){
        model.addAttribute("package", new Package(uuid, companyDAO.getCompanyList().get(0).getName()));
        model.addAttribute("subdivisionDAO", subdivisionDAO);
        return "parcel/newPackageMcd";
    }

    @GetMapping("/{id}/newPuma")
    public String newPackagePuma (Model model, @PathVariable("id") UUID uuid){
        model.addAttribute("package", new Package(uuid, companyDAO.getCompanyList().get(1).getName()));
        model.addAttribute("subdivisionDAO", subdivisionDAO);
        return "parcel/newPackagePuma";
    }

    @GetMapping("/{id}/newUCL")
    public String newPackageUCL (Model model, @PathVariable("id") UUID uuid){
        model.addAttribute("package", new Package(uuid, companyDAO.getCompanyList().get(2).getName()));
        model.addAttribute("subdivisionDAO", subdivisionDAO);
        return "parcel/newPackageUCL";
    }

    @PostMapping("/{id}")
    public String createPackage(@ModelAttribute("package")  Package p, @PathVariable("id") UUID uuid){
        lotDAO.savePackage(p);
        return "redirect:/parcel/" + uuid;
    }

    @GetMapping("/{id}/editLot")
    public String editLot (@PathVariable("id") UUID uuid, Model model){
        Lot lot = lotDAO.indexLot(uuid);
        model.addAttribute("lot", lot);
        return "parcel/editLot";
    }

    @PatchMapping("/{id}/update")
    public String updateLot(@ModelAttribute("lot") Lot lot){
        lotDAO.updateLot(lot);
        return "redirect:/parcel";
    }

    @GetMapping("sent/{id}/editLot")
    public String sentEditLot (@PathVariable("id") UUID uuid, Model model){
        Lot lot = lotDAO.indexLot(uuid);
        model.addAttribute("lot", lot);
        return "parcel/sentEditLot";
    }

    @PatchMapping("sent/{id}/update")
    public String sentUpdateLot(@ModelAttribute("lot") Lot lot){
        lotDAO.updateLot(lot);
        return "redirect:/parcel/sent";
    }

    @GetMapping("receive/{id}/editLot")
    public String receiveSentEditLot (@PathVariable("id") UUID uuid, Model model){
        Lot lot = lotDAO.indexLot(uuid);
        model.addAttribute("lot", lot);
        return "parcel/receiveEditLot";
    }

    @PatchMapping("receive/{id}/update")
    public String receiveUpdateLot(@ModelAttribute("lot") Lot lot){
        lotDAO.updateLot(lot);
        return "redirect:/parcel/receive";
    }

    @GetMapping("/{id}/deleteLot")
    public String getDelete(@PathVariable("id") UUID uuid, Model model){
        Lot lot = lotDAO.indexLot(uuid);
        model.addAttribute("lot", lot);
        return "parcel/deleteLot";
    }

    @DeleteMapping("/{id}/deleteLot")
    public String delete(@PathVariable("id") UUID uuid){
        lotDAO.delete(uuid);
        return "redirect:/parcel";
    }

    @GetMapping("/sent/{id}/deleteLot")
    public String getSentDelete(@PathVariable("id") UUID uuid, Model model){
        Lot lot = lotDAO.indexLot(uuid);
        model.addAttribute("lot", lot);
        return "parcel/sentDeleteLot";
    }

    @DeleteMapping("sent/{id}/deleteLot")
    public String sentDelete(@PathVariable("id") UUID uuid){
        lotDAO.delete(uuid);
        return "redirect:/parcel/sent";
    }

    @GetMapping("/receive/{id}/deleteLot")
    public String getReceiveDelete(@PathVariable("id") UUID uuid, Model model){
        Lot lot = lotDAO.indexLot(uuid);
        model.addAttribute("lot", lot);
        return "parcel/receiveDeleteLot";
    }

    @DeleteMapping("receive/{id}/deleteLot")
    public String receiveDelete(@PathVariable("id") UUID uuid){
        lotDAO.delete(uuid);
        return "redirect:/parcel/receive";
    }

    @GetMapping("/{idLot}/{idPackage}/McDonald's/editPackage")
    public String editPackageMCD (@PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, Model model){
        Package p = lotDAO.indexPackage(uuidLot, uuidPackage);
        model.addAttribute("package", p);
        model.addAttribute("idLot", uuidLot);
        switch (p.getCompanyName()) {
            case "McDonald's":
                model.addAttribute("subdivisionDAO", subdivisionDAO.getMcdList());
                break;
            case "Puma":
                model.addAttribute("subdivisionDAO", subdivisionDAO.getPumaList());
                break;
            case "Ukrainian Computer Laboratory":
                model.addAttribute("subdivisionDAO", subdivisionDAO.getUclList());
                break;
        }
        return "parcel/editPackageMCD";
    }

    @GetMapping("/{idLot}/{idPackage}/infoPackage")
    public String infoPackageMCD(@PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, Model model){
        Package p = lotDAO.indexPackage(uuidLot, uuidPackage);
        model.addAttribute("package", p);
        model.addAttribute("idLot", uuidLot);
        model.addAttribute("subdivisionDAO", subdivisionDAO);
        return "parcel/infoPackage";
    }

    @PatchMapping("/{idLot}/{idPackage}/editPackage")
    public String updatePackage(@ModelAttribute("package") Package p, @PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage){
        p.setId(uuidPackage);
        lotDAO.updatePackage(p, uuidLot, uuidPackage);
        return "redirect:/parcel/" + uuidLot;
    }
    @GetMapping("/{idLot}/{idPackage}/deletePackage")
    public String getDeletePackage(@PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, Model model){
        Package p = lotDAO.indexPackage(uuidLot, uuidPackage);
        model.addAttribute("package", p);
        model.addAttribute("idLot", uuidLot);
        return "parcel/deletePackage";
    }
    @DeleteMapping("/{idLot}/{idPackage}/deletePackage")
    public String deletePackage(@PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage){
       lotDAO.deletePackage(uuidLot, uuidPackage);
        return "redirect:/parcel/" + uuidLot;
    }

    @GetMapping("/{companyName}/{idLot}/{idPackage}/company")
    public String companyInfo(@PathVariable("companyName") String companyName,@PathVariable("idLot") UUID idLot, @PathVariable("idPackage") UUID idPackage, Model model){
        model.addAttribute("company", companyDAO.searchCompany(companyName));
        Package p = lotDAO.indexPackage(idLot, idPackage);
        model.addAttribute("subdivision", companyDAO.searchSubdivision(p.getCompanyName(), p.getClient()));
        model.addAttribute("listAllParcelsSubdivision", lotDAO.findAllParcelsSubdivision(p.getClient()));
        model.addAttribute("lot", lotDAO.indexLot(idLot));
        return "parcel/companyShow";
    }





}
