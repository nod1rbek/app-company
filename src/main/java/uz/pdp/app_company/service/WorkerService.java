package uz.pdp.app_company.service;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.app_company.entity.Address;
import uz.pdp.app_company.entity.Department;
import uz.pdp.app_company.entity.Worker;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.payload.WorkerDto;
import uz.pdp.app_company.repository.AddressRepository;
import uz.pdp.app_company.repository.DepartmentRepository;
import uz.pdp.app_company.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * Add worker
     */
    public ResponseApi addWorker(WorkerDto workerDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        boolean exists = workerRepository.existsByFirstNameAndLastNameAndPhoneNumber(workerDto.getFirstName(), workerDto.getLastName(), workerDto.getPhoneNumber());

        if (optionalDepartment.isPresent()) {
            Address address = new Address();
            address.setStreet(workerDto.getStreet());
            address.setHomeNumber(workerDto.getHomeNumber());
            Address savedAddress = addressRepository.save(address);

            Worker worker = new Worker();
            worker.setFirstName(workerDto.getFirstName());
            worker.setLastName(workerDto.getLastName());
            worker.setPhoneNumber(workerDto.getPhoneNumber());
            worker.setAddress(savedAddress);
            worker.setDepartment(optionalDepartment.get());

            if (exists)
                return new ResponseApi("ERROR! This worker already exist", false);

            return new ResponseApi("Worker added", true);
        }
        return new ResponseApi("ERROR! Department not found", false);
    }

    /**
     * Get all worker
     */
    public List<Worker> getAllWorker() {
        return workerRepository.findAll();
    }

    /**
     * Get one worker by id
     */
    public Worker getOneWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElseGet(Worker::new);
    }

    /**
     * Upload worker
     */
    public ResponseApi editWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            if (optionalWorker.isPresent() && optionalAddress.isPresent()) {
                Address editedAddress = optionalAddress.get();
                editedAddress.setStreet(workerDto.getStreet());
                editedAddress.setHomeNumber(workerDto.getHomeNumber());
                Address saveAddress = addressRepository.save(editedAddress);

                Worker editedWorker = optionalWorker.get();
                editedWorker.setFirstName(workerDto.getFirstName());
                editedWorker.setFirstName(workerDto.getLastName());
                editedWorker.setAddress(saveAddress);
                editedWorker.setDepartment(optionalDepartment.get());

                workerRepository.save(editedWorker);
                return new ResponseApi("Worker edited", false);
            }
        }
        return new ResponseApi("ERROR! Department not found", false);
    }

    /**
     * Delete worker by id
     */
    public ResponseApi deleteWorker(@PathVariable Integer id) {
        try {
            workerRepository.deleteById(id);
            return new ResponseApi("Worker deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR! Worker not found", false);
        }
    }
}
