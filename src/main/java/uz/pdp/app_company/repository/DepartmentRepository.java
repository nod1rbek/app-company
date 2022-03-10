package uz.pdp.app_company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_company.entity.Department;

import javax.validation.constraints.NotNull;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsByName(@NotNull String name);

    boolean existsByNameAndIdNot(@NotNull String name, Integer id);
}
