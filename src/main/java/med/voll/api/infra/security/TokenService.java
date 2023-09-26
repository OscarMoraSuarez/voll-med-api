package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String GenerarToken(){
        try {                               // se cambia el algoritmo que pide un string o arreglo de bytes
            Algorithm algorithm = Algorithm.HMAC256("123456");//que es el secret que es una contraseña para validar que
                                                                // el secret de voll es 123456
            return JWT.create()                         // el token puede ser validado si el receptor conoce esta firma
                    .withIssuer("voll-med")
                    .withSubject("racso.mora")//que va dirigido
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //para que no quede el catch vacio
            throw new RuntimeException();
        }

    }

}
