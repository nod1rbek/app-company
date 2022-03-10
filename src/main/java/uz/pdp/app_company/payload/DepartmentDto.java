package uz.pdp.app_company.payload;

import lombok.Data;

@Data
public class DepartmentDto {
    private Integer id;
    private String name;
    private Integer companyId;
}
