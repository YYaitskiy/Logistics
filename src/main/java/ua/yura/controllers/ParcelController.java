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
        System.out.println("Выполнился show контроллер");
        model.addAttribute("parcel", lotDAO.show());
        return "parcel/show";
    }

    @GetMapping("/{id}")
    public String id(@PathVariable("id") UUID uuid, Model model){
        System.out.println("Выполнился id контроллер");
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

//    @PostMapping()
//    public String createLot (@ModelAttribute("lot") Lot lot){
//        System.out.println("Выполнился createLot контроллер");
//        lot.setId(UUID.randomUUID());
//        lotDAO.save(lot);
//        return "redirect:/parcel/" + lot.getId();
//    }

    @GetMapping("/{id}/new")
    public String newPackage (Model model, @PathVariable("id") UUID uuid){
        model.addAttribute("package", new Package(uuid));
        return "parcel/newPackage";
    }

    @PostMapping("/{id}")
    public String createPackage(@ModelAttribute("package")  Package p, @PathVariable("id") UUID uuid){
        System.out.println("Выполнился createPackage контроллер");
        lotDAO.savePackage(p);
        return "redirect:/parcel/" + uuid;
    }

    @GetMapping("/{id}/editLot")
    public String editLot (@PathVariable("id") UUID uuid, Model model){
        System.out.println("Выполнился editLot контроллер" + uuid);
        Lot lot = lotDAO.indexLot(uuid);
        System.out.println("Выполнился editLot контроллер после метода indexLot");
        System.out.println(lot.getId());
        model.addAttribute("lot", lot);
        return "parcel/editLot";
    }

    @PostMapping()
    public String updateLot(@ModelAttribute("lot") Lot lot){
        System.out.println("Проверка ввыполнения Обновления Lot");
        System.out.println(lot.getStatus());
        System.out.println(lot.getShippingDate());
        System.out.println(lot.getId());
       lotDAO.updateLot(lot);
        return "redirect:/parcel";
    }



}
