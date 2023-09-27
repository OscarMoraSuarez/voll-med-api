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
    @Autowired
    private AuthenticationManager authtenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication   authToken= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
       var usuarioAutenticado =authtenticationManager.authenticate(authToken);// si es que la autenticacion es exitosa
         var JWTtoken=tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());// hay que enviarle un parametro de tipo Usuario
        //return ResponseEntity.ok().build();   El principal es el objeto usuario que ya fue auth en el sistema
        //return ResponseEntity.ok(JWTtoken);
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));


    }





}
