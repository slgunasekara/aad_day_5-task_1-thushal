package edu.ijse.test.demo.dto;

import edu.ijse.test.demo.enumeration.EnumUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private String address;
    private EnumUser role;

    public UserDTO(String name, String email, String password, String contactNumber, String address, EnumUser role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
        this.role = role;
    }
}
