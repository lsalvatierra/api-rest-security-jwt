package pe.edu.cibertec.apirestsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.apirestsecurityjwt.model.UsuarioRol;
import pe.edu.cibertec.apirestsecurityjwt.model.pk.UsuarioRolId;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {
}
