package med.voll.api.infra.security;

//es el estereotipo mas generico de spring para definir simplemente un componente de sprin que hace el escaneo de la
// clase para incluirlo en su contexto service, repository , controller son estereotipos basados en component, en spring
// todo podría ser component podría nombrar component un servicio, pero apra estandarizar la lectura de codigo de un
//  desarrollador spring  divide los estereotipos y les da tipos

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var  token=request.getHeader("Authorization").replace("Bearer","");//para quitar el prefijo Bearer
        System.out.println(token);

        filterChain.doFilter(request,response);// filtro ejecuta tu filtro y filtra esto y envia el reques y el response
                                                // del metodo html
    }
}
