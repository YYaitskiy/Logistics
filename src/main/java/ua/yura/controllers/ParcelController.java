package ua.yura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yura.dao.LotDAO;
import ua.yura.dao.PackageDAO;
import ua.yura.models.Lot;
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
}
