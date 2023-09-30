package med.voll.api.Dominio.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(


        @NotNull
       @JsonAlias(value = {"idPaciente", "PacienteId"}) Long idPaciente,

        @NotNull
        @JsonAlias(value = {"idMedico", "MedicoId","Medico_Id","Id_Medico"})
        Long idMedico,
        @NotNull
        @Future// tiene que ser una fecha posterior a la fecha actual
        LocalDateTime fecha,
        // podría poner un @NotNull pero usando el Bean Vlidation, no puedo enviar un msn personalizado al cliente
        // se deja así aora enviar un mensaje a través del throw
        String especialidad

        ) {




}
