package uz.pdp.app_company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_company.entity.Department;
import uz.pdp.app_company.payload.DepartmentDto;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    /**
     * Creat department
     *
     * @param departmentDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody DepartmentDto departmentDto) {
        ResponseApi responseApi = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Get all department
     *
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<Department>> all() {
        List<Department> allDepartment = departmentService.getAllDepartment();
        return ResponseEntity.ok(allDepartment);
    }

    /**
     * Get one department by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Integer id) {
        Department oneDepartmen = departmentService.getOneDepartmen(id);
        return ResponseEntity.ok(oneDepartmen);
    }

    /**
     * Upload department by id
     * @param id
     * @param departmentDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto) {
        ResponseApi responseApi = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Delete department by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi responseApi = departmentService.deleteDepartment(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(responseApi);
    }
}
