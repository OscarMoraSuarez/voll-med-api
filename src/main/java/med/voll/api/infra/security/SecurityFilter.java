package med.voll.api.infra.security;

//es el estereotipo mas generico de spring para definir simplemente un componente de spring que hace el escaneo de la
// clase para incluirlo en su contexto service, repository , controller son estereotipos basados en component, en spring
// todo podría ser component podría nombrar component un servicio, pero para estandarizar la lectura de codigo de un
//  desarrollador spring  divide los estereotipos y les da tipos

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         //obtener token del header
        var  token=request.getHeader("Authorization");// por estandar así se le llama ala prametro header del auth
        if ( token==null || token=="" ){
            throw new RuntimeException("El token enviado no es valido");
        }
       token=token.replace("Bearer ","");//para quitar el prefijo Bearer
        System.out.println(token);
        System.out.println(tokenService.getSubject(token));// este usuario tiene una sesion?, como la autenticacion es
                                                           // stateles no tengo anda en la memoria nsi tiene sesion o no

        filterChain.doFilter(request,response);// filtro ejecuta tu filtro y filtra esto y envia el reques y el response
                                                // del metodo html
    }
}
