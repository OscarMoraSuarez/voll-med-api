package med.voll.api.Dominio.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    // ese metodo lo que busca es un objeto de tipo userdetails que es el objeto que se crea cuando se
    //implementa la interface UserDetails

    UserDetails findByLogin(String username);
}
