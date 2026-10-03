package pe.edu.cibertec.apirestsecurityjwt.model;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pe.edu.cibertec.apirestsecurityjwt.model.pk.UsuarioRolId;

@Getter
@Setter
@Entity
@Table(name = "usuario_rol")
public class UsuarioRol {
    @EmbeddedId
    private UsuarioRolId usuarioRolId;
}
