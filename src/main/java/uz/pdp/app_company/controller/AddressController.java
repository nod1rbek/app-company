package uz.pdp.app_company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_company.entity.Address;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.service.AddressService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<ResponseApi> add(@Valid @RequestBody Address address) {
        ResponseApi responseApi = addressService.addAddress(address);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    @GetMapping
    public ResponseEntity<List<Address>> get() {
        List<Address> allAddress = addressService.getAllAddress();
        return ResponseEntity.ok(allAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getOne(@Valid @PathVariable Integer id) {
        Address oneAddressById = addressService.getOneAddressById(id);
        return ResponseEntity.ok(oneAddressById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi> edit(@Valid @PathVariable Integer id, @RequestBody Address address) {
        ResponseApi responseApi = addressService.editAddress(id, address);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(responseApi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Integer id) {
        ResponseApi responseApi = addressService.deleteAddress(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(responseApi);
    }
}
