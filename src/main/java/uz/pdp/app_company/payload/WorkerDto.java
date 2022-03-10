package uz.pdp.app_company.payload;

import lombok.Data;


@Data
public class WorkerDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String street;

    private String homeNumber;

    private Integer departmentId;
}
