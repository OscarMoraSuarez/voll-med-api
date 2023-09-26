package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// esta calse intercepta los metodos del controller y atrapa las excepciones que sean lanzadas
// hace uso del resopnseEntity que es un wrapper de las respuestas

@RestControllerAdvice//programacion orientada a aspectos
public class TratadorDeErrores {
    // si no existe y no lo encuentra
                       // el error que se pasa como parametro es el que salía en consola
    //@Exception handler identifica que se lanzaa una excepcion cuando en el rest controller advice identifique que es
    // lanzado un entity notfounexcepcion ejecute el codigo subyacente
    @ExceptionHandler(EntityNotFoundException.class)// anotacion que dice que va a tratar las excepciones
    //reponse entity interactu con los metodos del controller
    public ResponseEntity tratarError404(){// estos e implementó porque no es error del server sino del aprametro de la busqueda
        return ResponseEntity.notFound().build();// que no existe en la DB


    }
                        // nombre del error que salía en consola
    @ExceptionHandler(MethodArgumentNotValidException.class)// en el caso de no mandar campos validados @Notblank
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){// enviar parametros incompletos de bisqueda
                       // devuelve una lista de field error getAllErrors devuelve una lista de objetos
        var errores=e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();//identifico la lista de errores
        return ResponseEntity.badRequest().body(errores);// se envian los errores dentro del body

        // este metodo genera una lista muy dificil de leer para el cliente trabaja con un dto
    }


    // este es un DTO construido en el mismmo handler
    private record DatosErrorValidacion(String campo,String error){ // este DTO construye el objeto con la info de los campos
        public DatosErrorValidacion (FieldError error){             //que generan el error
                        // aqui se recibe el arreglo de field error y se mapea
            this(error.getField(),error.getDefaultMessage());

        }
    }


}
