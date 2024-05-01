package ua.yura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.yura.dao.LotDAO;
import ua.yura.dao.PackageDAO;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import java.util.UUID;

@Controller
@RequestMapping("/parcel")
public class ParcelController {

    private LotDAO lotDAO;
    private PackageDAO packageDAO;



    @Autowired
    public ParcelController(LotDAO lotDAO, PackageDAO packageDAO) {
        this.lotDAO = lotDAO;
        this.packageDAO = packageDAO;
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
        System.out.println("Проверка newLot");
        return "parcel/newLot";
    }

    @PostMapping()
    public String createLot (@ModelAttribute("lot") Lot lot){

        lot.setId(UUID.randomUUID());
        lotDAO.save(lot);
        System.out.println("Проверка createLot");
        return "redirect:/parcel";
    }

    @GetMapping("/{id}/new")
    public String newPackage (Model model, @PathVariable("id") UUID uuid){
        model.addAttribute("package", new Package(uuid));
        System.out.println("Проверка newPackage");
        return "parcel/newPackage";
    }

    @PostMapping("/{id}")
    public String createPackage(@ModelAttribute("package")  Package p, @PathVariable("id") UUID uuid){
        System.out.println("Проверка createPackage");
        System.out.println(p.getId());
        System.out.println(p.getParcelTrackingNumber());
        System.out.println(p.getCardNumber());
        System.out.println(p.getClient());
        System.out.println(p.getDescriptions());
        System.out.println(p.getDeliveryPrice());
        lotDAO.savePackage(p);
        return "redirect:/parcel/" + uuid;
    }
}
