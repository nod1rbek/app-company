package uz.pdp.app_company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_company.entity.Company;

import javax.validation.constraints.NotNull;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByCorpName(@NotNull String corpName);

    boolean existsByDirectorNameAndIdNot(@NotNull String directorName, Integer id);
}
