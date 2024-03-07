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

    @Autowired
    public LotDAO(PackageDAO packageDAO) {
        this.packageDAO = packageDAO;
    }

    private List<Lot> lotList;


    {
        lotList = new ArrayList<>();
        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 6), packageDAO.getPackageList()));
        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 7), packageDAO.getPackageList2()));
        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 5), packageDAO.getPackageList3()));


    }


}
