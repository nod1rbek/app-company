package uz.pdp.app_company.payload;

import lombok.Data;

@Data
public class CompanyDto {
    private Integer id;
    private String corpName;
    private String directorName;
    private Integer addressId;
}
