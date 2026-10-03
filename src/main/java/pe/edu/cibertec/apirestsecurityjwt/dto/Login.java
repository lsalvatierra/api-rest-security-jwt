package pe.edu.cibertec.apirestsecurityjwt.dto;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;
}
