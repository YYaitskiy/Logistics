package ua.yura.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.yura.models.Lot;
import ua.yura.models.Package;
import ua.yura.models.Subdivision;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<Lot> getLotList() {
        return lotList;
    }

    public void setLotList(List<Lot> lotList) {
        this.lotList = lotList;
    }

    public List<Lot> show() {
        return lotList;
    }

    public Lot indexLot(UUID uuid) {
        Lot lotId = new Lot();

        for (Lot lot : lotList) {
            if (lot.getId().equals(uuid)) {
                lotId = lot;
                break;
            }
        }
        return lotId;
    }

    public Package indexPackage(UUID idLot, UUID idPackage) {
        Package packageId = new Package();
        for (Lot lot : lotList) {
            if (lot.getId().equals(idLot)) {
                for (Package p : lot.getPackageList()) {
                    if (p.getId().equals(idPackage)) {
                        packageId = p;
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("LotDao indexPackage " + packageId.getId());
        return packageId;
    }

    public Package indexPackageCompany(UUID idPackageLot) {
        Package packageId = new Package();
        for (Lot lot : lotList) {
                for (Package p : lot.getPackageList()) {
                    if (p.getIdLot().equals(idPackageLot)) {
                        packageId = p;
                        break;
                    }
                }


        }
        System.out.println("LotDao indexPackageCompany " + packageId.getId());
        return packageId;
    }


    public List<Lot> sentStatus() {
        List<Lot> lotList1 = new ArrayList<>();
        for (Lot lot : lotList) {
            if (lot.getStatus().equals("Відправлені")) {
                lotList1.add(lot);
            }
        }
        return lotList1;
    }

    public List<Lot> receiveStatus() {
        List<Lot> lotList1 = new ArrayList<>();
        for (Lot lot : lotList) {
            if (lot.getStatus().equals("Отримані")) {
                lotList1.add(lot);
            }
        }
        return lotList1;
    }

    public void save(Lot lot) {
        lotList.add(0, lot);
    }

    public void savePackage(Package p, UUID uuid) {
        for (int i = 0; i < lotList.size(); i++) {
            if (lotList.get(i).getId().equals(p.getId())) {
                if (lotList.get(i).getPackageList() == null) {
                    lotList.get(i).setPackageList(new ArrayList<>());
                }
                p.setId(UUID.randomUUID());
                p.setIdLot(uuid);
                lotList.get(i).getPackageList().add(p);
            }
        }
    }

    public void updateLot(Lot lot) {
        int i = 0;
        for (Lot lotUpdate : lotList) {
            if (lotUpdate.getId().equals(lot.getId())) {
                lot.setPackageList(lotUpdate.getPackageList());
                lotList.set(i, lot);
                break;
            }
            i++;
        }

    }

    public void delete(UUID uuid) {
        int i = 0;
        for (Lot lot : lotList) {
            if (lot.getId().equals(uuid)) {
                lotList.remove(i);
                break;
            }
            i++;
        }

    }

    public void updatePackage(Package p, UUID idLot, UUID idPackage) {
        int i = 0;
        int i2 =0;
        for (Lot lot : lotList) {
            if (lot.getId().equals(idLot)) {
                for (Package p1 : lot.getPackageList()) {
                    if (p1.getId().equals(idPackage)) {
                        lotList.get(i).getPackageList().set(i2, p);
                        break;
                    }
                    i2++;
                }
                break;
            }
            i++;
        }
    }

    public void deletePackage(UUID idLot, UUID idPackage) {
        int i = 0;
        int i2 =0;
        for (Lot lot : lotList) {
            if (lot.getId().equals(idLot)) {
                for (Package p1 : lot.getPackageList()) {
                    if (p1.getId().equals(idPackage)) {
                        lotList.get(i).getPackageList().remove(i2);
                        break;
                    }
                    i2++;
                }
                break;
            }
            i++;
        }
    }

//    public List<Package> findAllParcelsSubdivision(String client){
//        List<Package> allParcelsSubdivision = new ArrayList<>();
//        for (Lot lot : lotList) {
//            if (lot.getPackageList() == null || lot.getPackageList().isEmpty()) {
//                continue;
//            }
//            for (Package p : lot.getPackageList()) {
//                    if (p.getClient().equals(client)) {
//                        allParcelsSubdivision.add(p);
//                    }
//                }
//        }
//        return allParcelsSubdivision;
//    }

    public List<Lot> findAllParcelsSubdivision(String client) {
        List<Lot> findAllParcelsSubdivisionInLot = new ArrayList<>();
        for (Lot lot : lotList) {
            List<Package> allParcelsSubdivision = new ArrayList<>();
            if (lot.getPackageList() != null && !lot.getPackageList().isEmpty()) {
                for (Package p : lot.getPackageList()) {
                    if (p.getClient().equals(client)) {
                        allParcelsSubdivision.add(p);
                    }
                }
                if (!allParcelsSubdivision.isEmpty()) {
                    Lot lotWithFilteredPackages = new Lot(lot.getId(), lot.getStatus(), lot.getShippingDate(), allParcelsSubdivision);
                    findAllParcelsSubdivisionInLot.add(lotWithFilteredPackages);
                }
            }
        }
        return findAllParcelsSubdivisionInLot;
    }



}
