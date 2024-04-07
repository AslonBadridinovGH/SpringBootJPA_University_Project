package uz.pdp.payload;

import lombok.Data;

@Data
public class TeacherDto {

    private String  firstName;
    private String  lastName;
    private String  subject;
    private Integer houseNumber;
    private String  street;
    private String  district;
    private String  region;
    private String  country;
    private String  continent;

}
