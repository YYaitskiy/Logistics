package ua.yura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.yura.dao.CompanyDAO;
import ua.yura.dao.LotDAO;
import ua.yura.dao.PackageDAO;
import ua.yura.dao.SubdivisionDAO;
import ua.yura.models.Company;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;



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
        List<Lot> lots = lotDAO.show();

        Map<Integer, List<Lot>> lotsByWeek = new LinkedHashMap<>();
        for (Lot lot : lots) {
            int weekOfYear = lot.getShippingDate().get(WeekFields.ISO.weekOfYear());

            lotsByWeek.computeIfAbsent(weekOfYear, k -> new ArrayList<>()).add(lot);
        }

        model.addAttribute("lotsByWeek", lotsByWeek);


        return "parcel/show";

    }

    @GetMapping("/{id}")
    public String id(@PathVariable("id") UUID uuid, Model model){
        System.out.println("id " + uuid);
        model.addAttribute("parcel", lotDAO.indexLot(uuid));
        model.addAttribute("companyNameMCD", companyDAO.getCompanyList().get(0).getName());
        model.addAttribute("companyNamePuma", companyDAO.getCompanyList().get(1).getName());
        model.addAttribute("companyNameUCL", companyDAO.getCompanyList().get(2).getName());
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

        if (lot.getShippingDate() == null) {
            lot.setShippingDate(LocalDate.now());
        }

        lotDAO.save(lot);
        return "redirect:/parcel/" + lot.getId();
    }

    @GetMapping("/{id}/newPackage")
    public String newPackage (Model model, @PathVariable("id") UUID uuid, @RequestParam("company") String companyName){
        Company company = companyDAO.searchCompany(companyName);
        model.addAttribute("lot", lotDAO.indexLot(uuid));
        model.addAttribute("package", new Package(uuid, company.getName()));
        model.addAttribute("subdivisionDAO", company.getSubdivisionList());
        model.addAttribute("company", company);
        return "parcel/newPackage";
    }

    @PostMapping("/{id}")
    public String createPackage(@ModelAttribute("package") @Valid Package p, BindingResult bindingResult, @PathVariable("id") UUID uuid, Model model){
        if (bindingResult.hasErrors()){
            Company company = companyDAO.searchCompany(p.getCompanyName());
            model.addAttribute("lot", lotDAO.indexLot(uuid));
            model.addAttribute("subdivisionDAO", company.getSubdivisionList());
            model.addAttribute("company", company);
            return "parcel/newPackage";
        }
        lotDAO.savePackage(p, uuid);
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

    @GetMapping("/{idLot}/{idPackage}/{company}/editPackage")
    public String editPackageMCD (@PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, Model model, @PathVariable("company") String company, @RequestParam(value = "from", required = false) String from){
        Package p = lotDAO.indexPackage(uuidLot, uuidPackage);
        Lot lot = lotDAO.indexLot(uuidLot);
        model.addAttribute("lot", lot);
        model.addAttribute("company", companyDAO.searchCompany(company));
        model.addAttribute("package", p);
        model.addAttribute("idLot", uuidLot);
        model.addAttribute("subdivisionDAO", companyDAO.searchListSubdivision(p.getCompanyName()));
        model.addAttribute("from", from);
        return "parcel/editPackage";
    }

    @PatchMapping("/{idLot}/{idPackage}/{companyName}/editPackage")
    public String updatePackage(@ModelAttribute("package") @Valid Package p, BindingResult bindingResult, @PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, @RequestParam(value = "from", required = false) String from, Model model, @PathVariable("companyName") String companyName){
        if (bindingResult.hasErrors()){
            Lot lot = lotDAO.indexLot(uuidLot);
            model.addAttribute("lot", lot);
            model.addAttribute("company", companyDAO.searchCompany(companyName));
            model.addAttribute("idPackage", uuidPackage);
            model.addAttribute("idLot", uuidLot);
            model.addAttribute("subdivisionDAO", companyDAO.searchListSubdivision(p.getCompanyName()));
            model.addAttribute("from", from);
            return "parcel/editPackage";
        }
        p.setId(uuidPackage);
        lotDAO.updatePackage(p, uuidLot, uuidPackage);
        return "redirect:/parcel/" + uuidLot + '/' + uuidPackage + '/' + p.getCompanyName() + "/infoPackage?from=" + from;
    }

    @GetMapping("/{idLot}/{idPackage}/{companyName}/infoPackage")
    public String infoPackageMCD(@PathVariable("companyName") String companyName, @PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, Model model, @RequestParam(value = "from", required = false) String from){
        Package p = lotDAO.indexPackage(uuidLot, uuidPackage);
        Lot lot = lotDAO.indexLot(uuidLot);
        model.addAttribute("lot", lot);
        model.addAttribute("company", companyDAO.searchCompany(companyName));
        model.addAttribute("package", p);
        model.addAttribute("idLot", uuidLot);
        model.addAttribute("from", from);
        return "parcel/infoPackage";
    }



    @GetMapping("/{idLot}/{idPackage}/deletePackage")
    public String getDeletePackage(@PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, @RequestParam(value = "from", required = false) String from, Model model){
        System.out.println("first getDeletePackage from= " + from);
        Lot lot = lotDAO.indexLot(uuidLot);
        Package p = lotDAO.indexPackage(uuidLot, uuidPackage);
        model.addAttribute("company", companyDAO.searchCompany(p.getCompanyName()));
        model.addAttribute("package", p);
        model.addAttribute("idLot", uuidLot);
        model.addAttribute("from", from);
        model.addAttribute("lot", lot);
        System.out.println("last getDeletePackage from= " + from);
        return "parcel/deletePackage";
    }
    @DeleteMapping("/{idLot}/{idPackage}/deletePackage")
    public String deletePackage(@PathVariable("idLot") UUID uuidLot, @PathVariable("idPackage") UUID uuidPackage, @RequestParam(value = "from", required = false) String from) throws UnsupportedEncodingException {
       Package p = lotDAO.indexPackage(uuidLot, uuidPackage);

        // Сохраняем имя компании и клиента перед удалением
        String companyName = p.getCompanyName();
        String client = p.getClient();

        // Кодируем параметры companyName и client в URL
        String encodedCompanyName = URLEncoder.encode(companyName, StandardCharsets.UTF_8.toString());
        String encodedClient = URLEncoder.encode(client, StandardCharsets.UTF_8.toString());

        lotDAO.deletePackage(uuidLot, uuidPackage);
        System.out.println("from= " + from);
        // Если параметр "from" равен "companyInfo", перенаправляем обратно на страницу companyInfo
        if ("companyInfo".equals(from)) {
            return "redirect:/parcel/" + encodedCompanyName + '/' + encodedClient + "/company?idLot=" + uuidLot;
        }

        // Если параметр "from" не передан, перенаправляем на страницу "id"
        return "redirect:/parcel/" + uuidLot;
    }

    @GetMapping("/{companyName}/{subdivisionName}/company")
    public String companyInfo(@PathVariable("companyName") String companyName,@PathVariable("subdivisionName") String subdivisionName, @RequestParam("idLot") UUID uuidLot, Model model) throws UnsupportedEncodingException {
        String decodedCompanyName = URLDecoder.decode(companyName, StandardCharsets.UTF_8.toString());
        String decodedSubdivisionName = URLDecoder.decode(subdivisionName, StandardCharsets.UTF_8.toString());
//        Lot lot = lotDAO.indexLot(uuidLot);
//        model.addAttribute("lot", lot);
        model.addAttribute("company", companyDAO.searchCompany(decodedCompanyName));
        model.addAttribute("subdivision", companyDAO.searchSubdivision(decodedCompanyName, decodedSubdivisionName));
        model.addAttribute("listAllParcelsSubdivision", lotDAO.findAllParcelsSubdivision(decodedSubdivisionName));
        model.addAttribute("idLot", uuidLot);
        System.out.println("companyInfo uuidLot " + uuidLot);
        return "parcel/companyShow";
    }

}
