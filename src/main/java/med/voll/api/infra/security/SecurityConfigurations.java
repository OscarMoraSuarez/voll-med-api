package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration// con esta anotacion va a venir y reconocer la configuracion, primero se escanean lso objetos @Configuracion
@EnableWebSecurity// para que spring reconozca que es una cong del contexto de sguridad
public class SecurityConfigurations {
    @Autowired
    private  SecurityFilter securityFilter;
    // se necesita un tipo de objeto public que retorna un objeto del tipo SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// indicamos tipod esesion
                .authorizeHttpRequests(auth-> auth.requestMatchers(HttpMethod.POST,"/login").permitAll()// las request que hacen math s epermiten
                        .anyRequest().authenticated())//las reques que no han match requieren auth
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean //para que este disponible en el contexto de Spring
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();// se le especifica que tipo de algoritmose usa para la encriptacion
        // es decir par aque use ese Bcrypt y encrupte el password ingresado por el user y haga match al hacer
        // comparacion con el que está almacenado
    }



}
