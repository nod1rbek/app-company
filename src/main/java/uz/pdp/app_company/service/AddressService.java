package uz.pdp.app_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_company.entity.Address;
import uz.pdp.app_company.payload.ResponseApi;
import uz.pdp.app_company.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * Creat address
     */
    public ResponseApi addAddress(Address address) {
        boolean exists = addressRepository.existsByHomeNumberAndStreetAndIdNot(address.getHomeNumber(), address.getStreet(), address.getId());
        if (exists)
            return new ResponseApi("This address already added", false);

        Address addedAddress = new Address(address.getStreet(), address.getHomeNumber());
        addressRepository.save(addedAddress);
        return new ResponseApi("Address added", true);
    }

    /**
     * Get all address
     */
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    /**
     * Get one address by id
     */
    public Address getOneAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElseGet(Address::new);
    }

    /**
     * Update address by id
     */
    public ResponseApi editAddress(Integer id, Address address) {
        boolean exists = addressRepository.existsByHomeNumberAndStreetAndIdNot(address.getHomeNumber(), address.getStreet(), address.getId());
        if (exists)
            return new ResponseApi("This address already added", false);
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address editedAddress = addressOptional.get();
            editedAddress.setStreet(address.getStreet());
            editedAddress.setHomeNumber(address.getHomeNumber());

            addressRepository.save(editedAddress);
            return new ResponseApi("Address edited", true);
        }
        return new ResponseApi("ERROR! Address not found", false);
    }

    /**
     * Delete address
     */
    public ResponseApi deleteAddress(Integer id) {
        try {
            addressRepository.deleteById(id);
            return new ResponseApi("Address deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR! Address not found", false);
        }
    }

}
