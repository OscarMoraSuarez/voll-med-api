package med.voll.api.Dominio.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table(name="usuarios")
@Entity(name ="Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long id;
    private String login;
    private String clave;


    @Override//si es null me da un forbidden porque no se ha especificado el role del usuaruio si no tiene un tipo de rol
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return null; se tiene que especificar
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//si la cuenta está bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {// si la credencial está expirada
        return true;
    }

    @Override
    public boolean isEnabled() {// si el user esta habilitado
        return true;
    }
}
