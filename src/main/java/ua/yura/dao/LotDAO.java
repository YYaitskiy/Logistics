package ua.yura.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class LotDAO {

   private PackageDAO packageDAO;

    private List<Lot> lotList;

    @Autowired
    public LotDAO(PackageDAO packageDAO) {
        this.packageDAO = packageDAO;
        lotList = new ArrayList<>();

        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 6), packageDAO.getPackageList()));
        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 7), packageDAO.getPackageList2()));
        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 5), packageDAO.getPackageList3()));
    }






    public List<Lot> show(){
        return lotList;
    }


}
