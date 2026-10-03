package pe.edu.cibertec.apirestsecurityjwt.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericResponse<T> {
    private T response;
    private ErrorMessage error;
}
