package ua.yura.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.yura.models.Company;
import ua.yura.models.Package;
import ua.yura.models.Subdivision;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CompanyDAO {

    private SubdivisionDAO subdivisionDAO;

    private List<Company> companyList;

    @Autowired
    public CompanyDAO(SubdivisionDAO subdivisionDAO, List<Company> companyList) {
        this.subdivisionDAO = subdivisionDAO;
        this.companyList = companyList;

        companyList.add(new Company(UUID.randomUUID(), "McDonald's", 23744453, "#FFC72C", subdivisionDAO.getMcdList()));
        companyList.add(new Company(UUID.randomUUID(), "Puma", 33741984,"black", subdivisionDAO.getPumaList()));
        companyList.add(new Company(UUID.randomUUID(), "Ukrainian Computer Laboratory", 11111111, "red", subdivisionDAO.getUclList()));
    }

    public SubdivisionDAO getSubdivisionDAO() {
        return subdivisionDAO;
    }

    public void setSubdivisionDAO(SubdivisionDAO subdivisionDAO) {
        this.subdivisionDAO = subdivisionDAO;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public Company searchCompany(String companyName){
        Company foundCompany = new Company();
        for (Company company:companyList) {
            if (company.getName().equals(companyName)){
                foundCompany=company;
            }
        }
        return foundCompany;
    }

    public Subdivision searchSubdivision(String companyName, String client){
        Subdivision foundSubdivision = new Subdivision();
        Company company = searchCompany(companyName);
        for (Subdivision subdivision:company.getSubdivisionList()) {
            if (subdivision.getName().equals(client)){
                foundSubdivision=subdivision;
            }

        }
        return foundSubdivision;
    }
    public List<Subdivision> searchListSubdivision(String companyName){
        Company company = searchCompany(companyName);
        return company.getSubdivisionList();
    }




}
