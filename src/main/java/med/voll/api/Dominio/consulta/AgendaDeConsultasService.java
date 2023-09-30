package med.voll.api.Dominio.consulta;

import med.voll.api.Dominio.medico.Medico;
import med.voll.api.Dominio.medico.MedicoRepository;
import med.voll.api.Dominio.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service// se usa para que esté disponible en el contexto de la app y poderlo inyectar en el controller
public class AgendaDeConsultasService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;


    @Autowired// para que springboot sepa donde inyectar  el valor del repositorio sino se coloca la anotacion sería null
    // la idea es poder usar los metodos del repo
    private ConsultaRepository consultaRepository;
    public void agendar(DatosAgendarConsulta datos){
                              // retorna un opcional        // metodo del opcional isPresent
        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){// is present retorna un booleano en cao de encontrarlo true
            throw new ValidacionDeIntegridad("Este id para el paciente no fue encontrado");
        }                                                 //aca directamente estoy usando datos.idMedico que retorna un bool
        if (datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionDeIntegridad("Este id para el medico no fue encontrado");
        }
        var paciente=pacienteRepository.findById(datos.idPaciente()).get();// get me retorna un object type paciente
        var medico=selecionarMedico(datos);

        var consulta=new Consulta(null,medico,paciente,datos.fecha() );

        consultaRepository.save(consulta);

    }

    private Medico selecionarMedico(DatosAgendarConsulta datos) {

        if(datos.idMedico()!=null){
                    // retornamos el medico con ese id esta forma es mas rapida que llamarlo con findById.get()
           return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad()==null){
            throw new ValidacionDeIntegridad("debe seleccionarse una especialidad para el medico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());

    }

}
