package uz.pdp.app_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_company.entity.Company;
import uz.pdp.app_company.entity.Department;
import uz.pdp.app_company.payload.DepartmentDto;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.repository.CompanyRepository;
import uz.pdp.app_company.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;


    /**
     * Creat department
     */
    public ResponseApi addDepartment(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        boolean exists = departmentRepository.existsByName(departmentDto.getName());
        if (optionalCompany.isPresent()) {
            Department department = new Department();
            department.setName(departmentDto.getName());
            department.setCompany(optionalCompany.get());

            if (exists)
                return new ResponseApi("ERROR! This department already exist", false);

            departmentRepository.save(department);
            return new ResponseApi("Department added", true);
        }
        return new ResponseApi("ERROR! Company not found.", false);
    }

    /**
     * Get all department
     */
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    /**
     * Get one department by id
     */
    public Department getOneDepartmen(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElseGet(Department::new);
    }

    /**
     * Edit department by id
     */
    public ResponseApi editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        boolean exists = departmentRepository.existsByNameAndIdNot(departmentDto.getName(), departmentDto.getId());

        if (optionalCompany.isPresent()) {
            if (optionalDepartment.isPresent()) {
                Department editedDepartment = optionalDepartment.get();
                editedDepartment.setName(departmentDto.getName());
                editedDepartment.setCompany(optionalCompany.get());

                if (exists)
                    return new ResponseApi("ERROR! This department already exist", false);

                departmentRepository.save(editedDepartment);
                return new ResponseApi("Department edited", true);
            }
            return new ResponseApi("ERROR! Department not found", false);
        }
        return new ResponseApi("ERROR! Company not found", false);
    }

    /**
     * Delete department
     */
    public ResponseApi deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new ResponseApi("Department deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR! Department not found", false);
        }

    }
}
