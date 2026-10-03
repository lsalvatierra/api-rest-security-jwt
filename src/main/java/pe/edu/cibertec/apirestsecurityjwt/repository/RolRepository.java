package pe.edu.cibertec.apirestsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.apirestsecurityjwt.model.Rol;

public interface RolRepository extends
        JpaRepository<Rol,Integer> {

}
