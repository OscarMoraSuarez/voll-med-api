package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;



import med.voll.api.Dominio.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;
    //private String  apiSecret= System.getenv("JWT_SECRET");
    public String generarToken(Usuario usuario){
        try {                               // se cambia el algoritmo que pide un string o arreglo de bytes
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);//que es el secret que es una contraseña para validar que
                                                                // el secret de voll es 123456
            return JWT.create()                         // el token puede ser validado si el receptor conoce esta firma
                    .withIssuer("voll-med")
                    .withSubject(usuario.getLogin())//que va dirigido
                    .withClaim("id",usuario.getId())//para retornar el id
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //para que no quede el catch vacio
            throw new RuntimeException();
        }

    }

    public String getSubject(String token) {
        if(token==null){

            throw new RuntimeException();

        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);// validando la firma del token
            verifier = JWT.require(algorithm)
                    .withIssuer("voll-med")// valida que el emisor sea voll-med
                    .build()// hace el build del objeto verifier
                    .verify(token);// verifica el token
            verifier.getSubject();//obtenermos el subject
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject()==null){
            throw new RuntimeException("verifier invalido");
        }

        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){//metodo creado para devolver un instante en que se cierre la sesion
                                   // esto devuelve una valides de dos horas para el token el 05 es supuestamente horario de suramerica
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));

    }

}
