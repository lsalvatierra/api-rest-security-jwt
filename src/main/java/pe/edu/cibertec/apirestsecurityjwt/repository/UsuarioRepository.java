package pe.edu.cibertec.apirestsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.apirestsecurityjwt.model.Usuario;

import java.util.List;

public interface UsuarioRepository extends
        JpaRepository<Usuario,Integer> {
    //select * from usuario where nomusuario=:nomusuario
    Usuario findByNomusuario(String nomusuario);

    @Query(value = """
        select r.nomrol from usuario u
        inner join usuario_rol ur on u.idusuario=ur.idusuario
        inner join rol r on ur.idrol = r.idrol
        where u.nomusuario=:nomusuario
    """, nativeQuery = true)
    List<String> getRolesByNomusuario(
            @Param("nomusuario") String nomusuario);


}
