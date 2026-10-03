package pe.edu.cibertec.apirestsecurityjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apirestsecurityjwt.model.Rol;
import pe.edu.cibertec.apirestsecurityjwt.model.Usuario;
import pe.edu.cibertec.apirestsecurityjwt.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DetalleUsuarioService
        implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomusuario(
                username);
        List<String> roles = usuarioRepository.getRolesByNomusuario(
                username);
        return getUser(usuario, getAuthoritiesByRol(roles));
    }
    public List<GrantedAuthority> getAuthoritiesByRol(
            List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + role)
            );
        }
        return authorities;
    }

    public UserDetails getUser(Usuario usuario,
                               List<GrantedAuthority> authorities) {
        return new User(usuario.getNomusuario(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true,
                authorities);
    }
}
