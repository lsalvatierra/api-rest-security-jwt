package pe.edu.cibertec.apirestsecurityjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apirestsecurityjwt.model.Usuario;
import pe.edu.cibertec.apirestsecurityjwt.repository.UsuarioRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario getUsuarioByNomusuario(String nomusuario){
        return usuarioRepository.findByNomusuario(nomusuario);
    }
    public List<String> getRolesByNomusuario(String nomusuario){
        return usuarioRepository.getRolesByNomusuario(nomusuario);
    }
}
