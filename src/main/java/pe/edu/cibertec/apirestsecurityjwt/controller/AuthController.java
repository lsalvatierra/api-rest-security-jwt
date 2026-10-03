package pe.edu.cibertec.apirestsecurityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.apirestsecurityjwt.dto.ErrorMessage;
import pe.edu.cibertec.apirestsecurityjwt.dto.GenericResponse;
import pe.edu.cibertec.apirestsecurityjwt.dto.Login;
import pe.edu.cibertec.apirestsecurityjwt.dto.UsuarioJwt;
import pe.edu.cibertec.apirestsecurityjwt.model.Usuario;
import pe.edu.cibertec.apirestsecurityjwt.security.JwtService;
import pe.edu.cibertec.apirestsecurityjwt.service.DetalleUsuarioService;
import pe.edu.cibertec.apirestsecurityjwt.service.UsuarioService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final DetalleUsuarioService detalleUsuarioService;;
    private final JwtService  jwtService;
    private final AuthenticationManager authManager;
    private final UsuarioService usuarioService;

    //localhost:8080/api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<GenericResponse<UsuarioJwt>>
        login(@RequestBody Login login) {
        GenericResponse<UsuarioJwt> response;
        try{
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(),
                            login.getPassword()));
            Usuario usuario = usuarioService.getUsuarioByNomusuario(
                    login.getUsername());
            List<String> roles = usuarioService.getRolesByNomusuario(
                    login.getUsername());
            String token = jwtService.generarToken(usuario,
                    detalleUsuarioService.getAuthoritiesByRol(roles));
            response = GenericResponse.<UsuarioJwt>builder()
                    .response(UsuarioJwt.builder()
                            .idusuario(usuario.getIdusuario())
                            .nomusuario(usuario.getNomusuario())
                            .token(token)
                            .build())
                    .build();
            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            response =GenericResponse.<UsuarioJwt>builder()
                    .error(ErrorMessage.builder()
                            .message("Usuario y/o password incorrecto")
                            .build()).build();
            return  ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
