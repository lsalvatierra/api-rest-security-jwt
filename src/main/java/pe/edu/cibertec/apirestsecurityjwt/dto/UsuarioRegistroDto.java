package pe.edu.cibertec.apirestsecurityjwt.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioRegistroDto {
    private String nomusuario;
    private String email;
    private String password;
    private String nombres;
    private String apellidos;
    private List<Integer> rolesIds;
}
