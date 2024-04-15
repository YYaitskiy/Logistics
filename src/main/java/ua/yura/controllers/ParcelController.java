package ua.yura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.yura.dao.LotDAO;
import ua.yura.dao.PackageDAO;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import java.util.ArrayList;
import java.util.List;
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
        Lot lot1 = lotDAO.index(uuid);
        model.addAttribute("parcel", packageDAO.listIndexPackage(lot1));
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

    @GetMapping("/new")
    public String newParcel(Model model){
        model.addAttribute("lot", new Lot(new ArrayList<>()));
        return "parcel/new";
    }

    @PostMapping()
    public String create (@ModelAttribute("lot") Lot lot){
        lotDAO.save(lot);
        return "redirect:/parcel";
    }
}
