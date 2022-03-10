package uz.pdp.app_company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_company.entity.Address;

import javax.validation.constraints.NotNull;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsByHomeNumberAndStreetAndIdNot(@NotNull String homeNumber, @NotNull String street, Integer id);
}
