package ua.yura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.yura.dao.LotDAO;
import ua.yura.dao.PackageDAO;

@Controller
public class ParcelController {

    private LotDAO lotDAO;
    private PackageDAO packageDAO;


    @Autowired
    public ParcelController(LotDAO lotDAO, PackageDAO packageDAO) {
        this.lotDAO = lotDAO;
        this.packageDAO = packageDAO;
    }

    @GetMapping("/parcel")
    public String show(Model model){
        model.addAttribute("parcel", lotDAO.show());
        System.out.println("Вывод кирилицы");
        return "parcel/show";
    }


}
