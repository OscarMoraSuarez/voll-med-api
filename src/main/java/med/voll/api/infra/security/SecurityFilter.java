package med.voll.api.infra.security;

//es el estereotipo mas generico de spring para definir simplemente un componente de spring que hace el escaneo de la
// clase para incluirlo en su contexto service, repository , controller son estereotipos basados en component, en spring
// todo podría ser component podría nombrar component un servicio, pero para estandarizar la lectura de codigo de un
//  desarrollador spring  divide los estereotipos y les da tipos

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.Dominio.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         //obtener token del header
        var  authHeader=request.getHeader("Authorization");// por estandar así se le llama a la prametro header del auth

            if (authHeader!=null){// si no está nulo seactiva el  filtro de lo contrario nose hace nada
                authHeader=authHeader.replace("Bearer ","");//para quitar el prefijo Bearer
                System.out.println(authHeader);
                System.out.println(tokenService.getSubject(authHeader));// este usuario tiene una sesion?, como la autenticacion es
                // stateles no tengo anda en la memoria nsi tiene sesion o no
                var nombreUsuario=tokenService.getSubject(authHeader);
                if(nombreUsuario!=null){// si no llega nulo es que el token está valido

                var usuario=usuarioRepository.findByLogin(nombreUsuario);
                var authentication=new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());//forzar un inicio de sesion
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }
        filterChain.doFilter(request,response);// filtro ejecuta tu filtro y filtra esto y envia el reques y el response
        // del metodo html

        }
        // explicaion del codigo se obtiene el header authorization si no esta nulo se remplaza el Bearer
        // luego se obtiene el ususario con el getSubject, forzamos inicio de sesion porque ya que el login
        // es valido  por el se está autenticando el usuario y verificando que existe entonces se setea
        // manualmente el inicio de sesion, para que para los demás request ya esté auth
}
