package med.voll.api.controllers;

import med.voll.api.Dominio.usuarios.DatosAutenticacionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authtenticationManager;//esto lanzara una excepcion porque no tiene un objeto de este tipo
    @PostMapping                                      // en su contexto entonces hay que crearlo en la configuración
    public ResponseEntity autenticarUsuario(DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication token= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        authtenticationManager.authenticate(token);
        return ResponseEntity.ok().build();


    }
    // esto sería todo lo necesario para hacer ese  trigger del metodo de login generando con los auth
    //datos del ususario un token que va a ser usado por el authtentication Manager para autenticarme




}
