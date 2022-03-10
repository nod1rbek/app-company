package uz.pdp.app_company.controller;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_company.entity.Worker;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.payload.WorkerDto;
import uz.pdp.app_company.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    /**
     * Add worker
     *
     * @param workerDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody WorkerDto workerDto) {
        ResponseApi responseApi = workerService.addWorker(workerDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Get all workers
     *
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<Worker>> getAll() {
        return ResponseEntity.ok(workerService.getAllWorker());
    }

    /**
     * Get one worker by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(workerService.getOneWorker(id));
    }

    /**
     * Edit worker
     * @param id
     * @param workerDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody WorkerDto workerDto) {
        ResponseApi responseApi = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Delete worker
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi responseApi = workerService.deleteWorker(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(responseApi);
    }
}
