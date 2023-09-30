package med.voll.api.Dominio.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    //Page<Medico> finByActivoTrue(Pageable paginacion);


    Page<Medico> findByActivoTrue(Pageable paginacion);
            // he realizado el merge usando  git pull
            //esta consulta trabaja con el metodo de abajo, la anotacio @Query es para hacer consultas JPQL
            @Query("""
             select m from Medico m
              where m.activo= true
              and
              m.especialidad=:especialidad
              and
              m.id not in(
                select c.medico.id from Consulta c
                where
                c.data=:fecha
              )
              order by rand()
              limit 1
            """)

    Medico seleccionarMedicoConEspecialidadEnFecha(String especialidad, LocalDateTime fecha);
}

