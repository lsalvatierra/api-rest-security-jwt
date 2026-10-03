package pe.edu.cibertec.apirestsecurityjwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    //localhost:8062/api/v1/hello
    @GetMapping
    public ResponseEntity<String> hello(HttpServletRequest request) {
        return ResponseEntity.ok("Bienvenido al proyecto backend");
    }

}
