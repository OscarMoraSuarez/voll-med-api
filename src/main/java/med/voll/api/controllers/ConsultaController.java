package med.voll.api.controllers;


import jakarta.validation.Valid;
import med.voll.api.Dominio.consulta.AgendaDeConsultasService;
import med.voll.api.Dominio.consulta.DatosAgendarConsulta;
import med.voll.api.Dominio.consulta.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/consultas")// estas anotaciones son componentes gerenciados por spring ,lo qeu significa que
// hay que generar la palabra new ConsultaControler, para poder hacer uso de esa clase spring automaticamente
// instancia esa clase y la deja disponible apra nuestro uso
public class ConsultaController {
    @Autowired
    AgendaDeConsultasService service;
    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){

        System.out.println(datos);
        service.agendar(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null,null,null,null));

    }
}
