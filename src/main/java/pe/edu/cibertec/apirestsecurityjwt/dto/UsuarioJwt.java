package pe.edu.cibertec.apirestsecurityjwt.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioJwt {
    private Integer idusuario;
    private String nomusuario;
    private String token;
}
