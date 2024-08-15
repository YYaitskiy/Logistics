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

        companyList.add(new Company(UUID.randomUUID(), "McDonald's", 23744453, subdivisionDAO.getMcdList()));
        companyList.add(new Company(UUID.randomUUID(), "Puma", 33741984, subdivisionDAO.getPumaList()));
        companyList.add(new Company(UUID.randomUUID(), "UCL", 11111111,subdivisionDAO.getUclList()));
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

    //    public CompanyDAO companySearch(Package p){
//
//    }


}
