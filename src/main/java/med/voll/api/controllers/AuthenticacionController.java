package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.Dominio.usuarios.DatosAutenticacionUsuario;
import med.voll.api.Dominio.usuarios.Usuario;
import med.voll.api.infra.security.DatosJWTToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthenticacionController {
    @Autowired//interface  de spring security
    private AuthenticationManager authtenticationManager;
    @Autowired//instancia del token serviceque creee
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        //interface de spring security  desde esete controller se intent econtrar el user y el password en la DB usando el repo intenta encontrarlo por login
        Authentication   authToken= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado =authtenticationManager.authenticate(authToken);// si es que la autenticacion es exitosa
        var JWTtoken=tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());// hay que enviarle un parametro de tipo Usuario
        //return ResponseEntity.ok().build();   El principal es el objeto usuario que ya fue auth en el sistema
        //return ResponseEntity.ok(JWTtoken);
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));


    }





}
