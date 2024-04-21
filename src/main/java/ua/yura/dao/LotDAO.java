package ua.yura.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class LotDAO {
    private PackageDAO packageDAO;
    private List<Lot> lotList;

    @Autowired
    public LotDAO(PackageDAO packageDAO, List<Lot> lotList) {
        this.packageDAO = packageDAO;
        this.lotList = lotList;

        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 6), packageDAO.getPackageList()));
        lotList.add(new Lot(UUID.randomUUID(), "Отримані", LocalDate.of(2024, 4, 9), packageDAO.getPackageList4()));
        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 7), packageDAO.getPackageList2()));
        lotList.add(new Lot(UUID.randomUUID(), "Відправлені", LocalDate.of(2024, 3, 5), packageDAO.getPackageList3()));
        lotList.add(new Lot(UUID.randomUUID(), "Отримані", LocalDate.of(2024, 4, 8), packageDAO.getPackageList5()));

    }

    public List<Lot> show(){
        return lotList;
    }

    public List<Package> index (UUID uuid){
        Lot lotId = new Lot();
        for(Lot lot:lotList){
            if (lot.getId().equals(uuid)){
               lotId=lot;
               break;
            }
        }
        return lotId.getPackageList();
    }

    public List<Lot> sentStatus(){
        List<Lot> lotList1 = new ArrayList<>();
        for (Lot lot:lotList){
            if(lot.getStatus().equals("Відправлені")){
                lotList1.add(lot);
            }
        }
        return lotList1;
    }

    public List<Lot> receiveStatus(){
        List<Lot> lotList1 = new ArrayList<>();
        for (Lot lot:lotList){
            if(lot.getStatus().equals("Отримані")){
                lotList1.add(lot);
            }
        }
        return lotList1;
    }

    public void save(Lot lot){
        lot.setId(UUID.randomUUID());
//        lot.setShippingDate(LocalDate.now());
        lot.setPackageList(new ArrayList<>());
        lotList.add(0,lot);
    }


}
