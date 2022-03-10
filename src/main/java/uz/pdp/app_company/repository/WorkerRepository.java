package uz.pdp.app_company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_company.entity.Worker;

import javax.validation.constraints.NotNull;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    boolean existsByFirstNameAndLastNameAndPhoneNumber(@NotNull String firstName, @NotNull String lastName, @NotNull String phoneNumber);
}
