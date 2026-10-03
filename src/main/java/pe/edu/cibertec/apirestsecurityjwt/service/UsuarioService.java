package pe.edu.cibertec.apirestsecurityjwt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apirestsecurityjwt.dto.UsuarioRegistroDto;
import pe.edu.cibertec.apirestsecurityjwt.model.Rol;
import pe.edu.cibertec.apirestsecurityjwt.model.Usuario;
import pe.edu.cibertec.apirestsecurityjwt.model.UsuarioRol;
import pe.edu.cibertec.apirestsecurityjwt.model.pk.UsuarioRolId;
import pe.edu.cibertec.apirestsecurityjwt.repository.RolRepository;
import pe.edu.cibertec.apirestsecurityjwt.repository.UsuarioRepository;
import pe.edu.cibertec.apirestsecurityjwt.repository.UsuarioRolRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario getUsuarioByNomusuario(String nomusuario){
        return usuarioRepository.findByNomusuario(nomusuario);
    }
    public List<String> getRolesByNomusuario(String nomusuario){
        return usuarioRepository.getRolesByNomusuario(nomusuario);
    }

    @Transactional
    public Usuario crearUsuarioConRoles(UsuarioRegistroDto dto) {
        if (usuarioRepository.existsByNomusuario(dto.getNomusuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso.");
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNomusuario(dto.getNomusuario());
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        nuevoUsuario.setNombres(dto.getNombres());
        nuevoUsuario.setApellidos(dto.getApellidos());
        nuevoUsuario.setActivo(true); // Activo por defecto
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        if (dto.getRolesIds() != null && !dto.getRolesIds().isEmpty()) {
            for (Integer idRol : dto.getRolesIds()) {
                Rol rol = rolRepository.findById(idRol)
                        .orElseThrow(() -> new RuntimeException("El rol con ID " + idRol + " no existe."));
                UsuarioRolId usuarioRolId = new UsuarioRolId();
                usuarioRolId.setIdusuario(usuarioGuardado.getIdusuario());
                usuarioRolId.setIdrol(rol.getIdrol());
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setUsuarioRolId(usuarioRolId);
                usuarioRolRepository.save(usuarioRol);
            }
        }
        return usuarioGuardado;
    }
}
