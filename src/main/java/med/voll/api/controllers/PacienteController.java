package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.Dominio.paciente.DatosListadoPacientes;
import med.voll.api.Dominio.paciente.DatosRegistroPaciente;
import med.voll.api.Dominio.paciente.Paciente;
import med.voll.api.Dominio.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    public void RegistrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
        System.out.println(datosRegistroPaciente);
        pacienteRepository.save(new Paciente(datosRegistroPaciente));

    }
    @GetMapping
    public Page<DatosListadoPacientes> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion){

        return pacienteRepository.findAll(paginacion).map(DatosListadoPacientes::new);


    }


}
