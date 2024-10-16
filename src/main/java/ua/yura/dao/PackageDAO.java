package ua.yura.dao;

import org.springframework.stereotype.Component;
import ua.yura.models.Lot;
import ua.yura.models.Package;

import java.util.List;
import java.util.UUID;

@Component
public class PackageDAO {

    private SubdivisionDAO subdivisionDAO;
    private CompanyDAO companyDAO;
    private List<Package> packageList;
    private List<Package> packageList2;
    private List<Package> packageList3;
    private List<Package> packageList4;
    private List<Package> packageList5;



    public PackageDAO(List<Package> packageList, List<Package> packageList2, List<Package> packageList3,
                      List<Package> packageList4, List<Package> packageList5, SubdivisionDAO subdivisionDAO, CompanyDAO companyDAO){
        this.companyDAO=companyDAO;
        this.subdivisionDAO = subdivisionDAO;
        this.packageList = packageList;
        this.packageList2 = packageList2;
        this.packageList3 = packageList3;
        this.packageList4 = packageList4;
        this.packageList5 = packageList5;



        packageList.add(new Package(UUID.randomUUID(), "20450883238699", "MD24-106391", subdivisionDAO.getMcdList().get(12).getName(),companyDAO.getCompanyList().get(0).getName(),  "Панч", 80));
        packageList.add(new Package(UUID.randomUUID(), "20450883238698", "MD24-106385", subdivisionDAO.getMcdList().get(13).getName(),companyDAO.getCompanyList().get(0).getName(), "пейджеры-5шт", 130));
        packageList.add(new Package(UUID.randomUUID(), "20450979966353", "PM24-134586", subdivisionDAO.getPumaList().get(1).getName(),companyDAO.getCompanyList().get(1).getName(), "кнопки", 130));

        packageList2.add(new Package(UUID.randomUUID(), "20450979980178", "UC24-114564", subdivisionDAO.getUclList().get(0).getName(),companyDAO.getCompanyList().get(2).getName(), "таблет 2шт", 100));
        packageList2.add(new Package(UUID.randomUUID(), "20450883238697", "MD24-106239", subdivisionDAO.getMcdList().get(5).getName(),companyDAO.getCompanyList().get(0).getName(), "монитор с креплением", 185));
        packageList2.add(new Package(UUID.randomUUID(), "20450883238696", "MD24-106241", subdivisionDAO.getMcdList().get(6).getName(),companyDAO.getCompanyList().get(0).getName(), "Ппланшет+чехол+стекло", 155));
        packageList2.add(new Package(UUID.randomUUID(), "20450883238695", "MD24-106242", subdivisionDAO.getMcdList().get(7).getName(),companyDAO.getCompanyList().get(0).getName(), "вкладка манипулятора-2шт", 90));
        packageList2.add(new Package(UUID.randomUUID(), "20450883238694", "MD24-106244", subdivisionDAO.getMcdList().get(8).getName(),companyDAO.getCompanyList().get(0).getName(), "подменный монитор", 145));
        packageList2.add(new Package(UUID.randomUUID(), "20450883238693", "MD24-106260", subdivisionDAO.getMcdList().get(9).getName(),companyDAO.getCompanyList().get(0).getName(), "PC-box", 185));
        packageList2.add(new Package(UUID.randomUUID(), "20450883238692", "MD24-106264", subdivisionDAO.getMcdList().get(1).getName(),companyDAO.getCompanyList().get(0).getName(), "кнопка", 90));

        packageList3.add(new Package(UUID.randomUUID(), "20450883238691", "MD24-104979", subdivisionDAO.getMcdList().get(2).getName(),companyDAO.getCompanyList().get(0).getName(), "монитор", 208));
        packageList3.add(new Package(UUID.randomUUID(), "20450883238690", "MD24-105021", subdivisionDAO.getMcdList().get(3).getName(),companyDAO.getCompanyList().get(0).getName(), "кнопки", 130));

        packageList4.add(new Package(UUID.randomUUID(), "20450883234537", "MD24-111439", subdivisionDAO.getMcdList().get(10).getName(),companyDAO.getCompanyList().get(0).getName(), "Тонкий клієнт", 95));
        packageList4.add(new Package(UUID.randomUUID(), "20450883362398", "MD24-109645", subdivisionDAO.getMcdList().get(11).getName(),companyDAO.getCompanyList().get(0).getName(), "старі atrast", 140));

        packageList5.add(new Package(UUID.randomUUID(), "20450883664490", "MD24-111064", subdivisionDAO.getMcdList().get(0).getName(),companyDAO.getCompanyList().get(0).getName(), "переопломбування РРО", 55));
        packageList5.add(new Package(UUID.randomUUID(), "20450883373892", "MD24-111208", subdivisionDAO.getMcdList().get(1).getName(),companyDAO.getCompanyList().get(0).getName(), "Atrust", 95));
        packageList5.add(new Package(UUID.randomUUID(), "20450883373473", "MD24-111564", subdivisionDAO.getMcdList().get(3).getName(),companyDAO.getCompanyList().get(0).getName(), "таблет 2шт", 100));
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
