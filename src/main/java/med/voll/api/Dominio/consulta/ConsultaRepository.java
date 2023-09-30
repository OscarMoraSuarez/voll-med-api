package med.voll.api.Dominio.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository// las clases que impoementan JPARepository heredan esa anotacion y son instaniadas y gerenciadas por Spring
public interface ConsultaRepository extends JpaRepository<Consulta,Long> {



}
