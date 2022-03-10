package uz.pdp.app_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_company.entity.Address;
import uz.pdp.app_company.entity.Company;
import uz.pdp.app_company.payload.CompanyDto;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.repository.AddressRepository;
import uz.pdp.app_company.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * Creat company
     */
    public ResponseApi addCompany(CompanyDto companyDto) {
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        boolean exists = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (exists)
            return new ResponseApi("This company already added", false);
        if (optionalAddress.isPresent()) {
            Company company = new Company();
            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(companyDto.getDirectorName());
            company.setAddress(optionalAddress.get());
            companyRepository.save(company);

            return new ResponseApi("Company added", true);
        }
        return new ResponseApi("ERROR! Address not found", false);
    }

    /**
     * Get all company
     */
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    /**
     * Get one company by id
     */
    public Company getOneCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElseGet(Company::new);
    }

    /**
     * Edit company by id
     */
    public ResponseApi editCompany(Integer id, CompanyDto companyDto) {
        boolean exists = companyRepository.existsByDirectorNameAndIdNot(companyDto.getDirectorName(), companyDto.getId());
        if (exists)
            return new ResponseApi("This company already added", false);
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isPresent()) {
            if (optionalCompany.isPresent()) {
                Company editedCompany = optionalCompany.get();
                editedCompany.setCorpName(companyDto.getCorpName());
                editedCompany.setDirectorName(companyDto.getDirectorName());
                editedCompany.setAddress(optionalAddress.get());
                companyRepository.save(editedCompany);

                return new ResponseApi("Company edited", true);
            }
            return new ResponseApi("This company not found", false);
        }
        return new ResponseApi("This address not found.Another address add", false);
    }

    /**
     * Delete company by id
     */
    public ResponseApi deleteCompany(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ResponseApi("Company deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR! This company not found", false);
        }
    }
}
