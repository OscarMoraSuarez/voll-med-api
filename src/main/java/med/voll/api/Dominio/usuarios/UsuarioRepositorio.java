package med.voll.api.Dominio.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {


    UserDetails findByUsername(String username);
}
