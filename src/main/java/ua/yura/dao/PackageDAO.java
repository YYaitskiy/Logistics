package ua.yura.dao;

import org.springframework.stereotype.Component;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PackageDAO {

    private List<Package> packageList;
    private List<Package> packageList2;

    private List<Package> packageList3;

    public PackageDAO(List<Package> packageList, List<Package> packageList2, List<Package> packageList3) {
        this.packageList = packageList;
        this.packageList2 = packageList2;
        this.packageList3 = packageList3;

        packageList.add(new Package(UUID.randomUUID(),20450883238699L, 106391, "Глобал Київ", "Панч", 80));
        packageList.add(new Package(UUID.randomUUID(),20450883238698L, 106385, "Опера", "пейджеры-5шт", 130));

        packageList2.add(new Package(UUID.randomUUID(),20450883238697L, 106239, "Дружба", "монитор с креплением", 185));
        packageList2.add(new Package(UUID.randomUUID(),20450883238696L, 106241, "Перспективна", "Ппланшет+чехол+стекло", 155));
        packageList2.add(new Package(UUID.randomUUID(),20450883238695L, 106242, "Парк", "вкладка манипулятора-2шт", 90));
        packageList2.add(new Package(UUID.randomUUID(),20450883238694L, 106244, "Короленка", "подменный монитор", 145));
        packageList2.add(new Package(UUID.randomUUID(),20450883238693L, 106260, "Підстанція", "PC-box", 185));
        packageList2.add(new Package(UUID.randomUUID(),20450883238692L, 106264, "Глобал Житомир", "кнопка", 90));

        packageList3.add(new Package(UUID.randomUUID(),20450883238691L, 104979, "Смілянська", "монитор", 208));
        packageList3.add(new Package(UUID.randomUUID(),20450883238690L, 105021, "Стрийський", "кнопки", 130));
    }

    public List<Package> getPackageList() {
        return packageList;
    }

    public List<Package> getPackageList2() {
        return packageList2;
    }

    public List<Package> getPackageList3() {
        return packageList3;
    }

    public List<Package> listIndexPackage (Lot lot){
        List<Package> list = new ArrayList<>();
       if (lot.getPackageList()==packageList){
           list=packageList;
       }
       else if (lot.getPackageList()==packageList2){
           list=packageList2;
       }
       else if (lot.getPackageList()==packageList3){
           list=packageList3;
       }
       return list;
    }

}
