package pe.edu.cibertec.apirestsecurityjwt.model.pk;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class UsuarioRolId implements Serializable {
    private Integer idusuario;
    private Integer idrol;
}
