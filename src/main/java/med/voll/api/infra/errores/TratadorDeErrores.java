package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//programacion orientada a aspectos
public class TratadorDeErrores {
    // si no existe y no lo encuentra
    @ExceptionHandler(EntityNotFoundException.class)// anotacion que dice que va a tratar als excepciones
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)// en el caso de no mandar campos validados @Notblank
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores=e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);


    }
    private record DatosErrorValidacion(String campo,String error){
        public DatosErrorValidacion (FieldError error){

            this(error.getField(),error.getDefaultMessage());

        }
    }


}
