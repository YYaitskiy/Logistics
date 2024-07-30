package ua.yura.dao;

import org.springframework.stereotype.Component;
import ua.yura.models.Package;

import java.util.List;
import java.util.UUID;

@Component
public class PackageDAO {

    private SubdivisionDAO subdivisionDAO;

    private List<Package> packageList;
    private List<Package> packageList2;
    private List<Package> packageList3;
    private List<Package> packageList4;
    private List<Package> packageList5;



    public PackageDAO(List<Package> packageList, List<Package> packageList2, List<Package> packageList3,
                      List<Package> packageList4, List<Package> packageList5, SubdivisionDAO subdivisionDAO) {
        this.subdivisionDAO = subdivisionDAO;
        this.packageList = packageList;
        this.packageList2 = packageList2;
        this.packageList3 = packageList3;
        this.packageList4 = packageList4;
        this.packageList5 = packageList5;


        packageList.add(new Package(UUID.randomUUID(),20450883238699L, "MD24-106391", subdivisionDAO.getMcdList().get(12), "Панч", 80));
        packageList.add(new Package(UUID.randomUUID(),20450883238698L, "MD24-106385", subdivisionDAO.getMcdList().get(13), "пейджеры-5шт", 130));

        packageList2.add(new Package(UUID.randomUUID(),20450883238697L, "MD24-106239", subdivisionDAO.getMcdList().get(5), "монитор с креплением", 185));
        packageList2.add(new Package(UUID.randomUUID(),20450883238696L, "MD24-106241", subdivisionDAO.getMcdList().get(6), "Ппланшет+чехол+стекло", 155));
        packageList2.add(new Package(UUID.randomUUID(),20450883238695L, "MD24-106242", subdivisionDAO.getMcdList().get(7), "вкладка манипулятора-2шт", 90));
        packageList2.add(new Package(UUID.randomUUID(),20450883238694L, "MD24-106244", subdivisionDAO.getMcdList().get(8), "подменный монитор", 145));
        packageList2.add(new Package(UUID.randomUUID(),20450883238693L, "MD24-106260", subdivisionDAO.getMcdList().get(9), "PC-box", 185));
        packageList2.add(new Package(UUID.randomUUID(),20450883238692L, "MD24-106264", subdivisionDAO.getMcdList().get(2), "кнопка", 90));

        packageList3.add(new Package(UUID.randomUUID(),20450883238691L, "MD24-104979", subdivisionDAO.getMcdList().get(3), "монитор", 208));
        packageList3.add(new Package(UUID.randomUUID(),20450883238690L, "MD24-105021", subdivisionDAO.getMcdList().get(4), "кнопки", 130));

        packageList4.add(new Package(UUID.randomUUID(),20450883234537L, "MD24-111439", subdivisionDAO.getMcdList().get(10), "Тонкий клієнт", 95));
        packageList4.add(new Package(UUID.randomUUID(),20450883362398L, "MD24-109645", subdivisionDAO.getMcdList().get(11), "старі atrast", 140));

        packageList5.add(new Package(UUID.randomUUID(),20450883664490L, "MD24-111064", subdivisionDAO.getMcdList().get(0), "переопломбування РРО", 55));
        packageList5.add(new Package(UUID.randomUUID(),20450883373892L, "MD24-111208", subdivisionDAO.getMcdList().get(1), "Atrust", 95));
        packageList5.add(new Package(UUID.randomUUID(),20450883373473L, "MD24-111564", subdivisionDAO.getMcdList().get(2), "таблет 2шт", 100));
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

    public List<Package> getPackageList4() {
        return packageList4;
    }

    public List<Package> getPackageList5() {
        return packageList5;
    }



}
