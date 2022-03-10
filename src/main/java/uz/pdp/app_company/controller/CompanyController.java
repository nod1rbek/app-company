package uz.pdp.app_company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_company.entity.Company;
import uz.pdp.app_company.payload.CompanyDto;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    /**
     * Create company
     * @param companyDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CompanyDto companyDto) {
        ResponseApi responseApi = companyService.addCompany(companyDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Read company
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<Company>> get() {
        List<Company> allCompany = companyService.getAllCompany();
        return ResponseEntity.ok(allCompany);
    }

    /**
     * Get one company by id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getOne(@PathVariable Integer id) {
        Company oneCompany = companyService.getOneCompany(id);
        return ResponseEntity.ok(oneCompany);
    }

    /**
     * Upload company by id
     * @param id
     * @param companyDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        ResponseApi responseApi = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Delete company by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi responseApi = companyService.deleteCompany(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(responseApi);
    }
}
