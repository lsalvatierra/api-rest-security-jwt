package pe.edu.cibertec.apirestsecurityjwt.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ErrorMessage {
    private Integer statusCode;
    private LocalDate dateError;
    private String message;
    private String description;
}
