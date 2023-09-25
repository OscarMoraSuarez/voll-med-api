package med.voll.api.Dominio.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    //Page<Medico> finByActivoTrue(Pageable paginacion);


    Page<Medico> findByActivoTrue(Pageable paginacion);
}
