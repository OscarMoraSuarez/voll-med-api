package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController//le digo que es un RestController
@RequestMapping("/medicos")//le digo la ruta http que recibe el payload


public class MedicoController {
    // para que instancie el repositorio
    @Autowired
    private MedicoRepository medicoRepository;
    //private MedicoRepository medicoRepository;
    @PostMapping//le digo que recibe del metodo post
    public void RegistrarMedico(@RequestBody @Valid DatosRegistroMedico datoRegistroMedico){//le digo que este parametro es el requestBody
        System.out.println(datoRegistroMedico);
        // paso los datos a un constructor Medico porque el
        medicoRepository.save(new Medico(datoRegistroMedico));


    }

    /* @GetMapping
    public List<DatosListadoMedico> listadoMedicos(){

                                //fidnAll() va a retornar objetos de tipoMedico pero yo quiero datosListadoMedico para lo cual se hace uso del API stream y que
                                //Mapeelos datos del datoslIstadoMedico y cree un nuevo datolistadoMedico con cada medico que traiga de la DB
                                //.stream(): El método findAll() devuelve un iterable de médicos, y al llamar a .stream() se convierte en un flujo de elementos.
        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();// en el repository no viene ningún metodo implementado pero extiende de una clase
        // que si implementa este metodo

    }*/
    @GetMapping// para la paginación se neceista retornar una pagina y trabajar iun parametro Pageable que llega desde el cliente
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion){
        //la paginacion viene spring internamente ejecuta la misma query contra la bd trae la lista o array de entidad medico
        //internamente spring lo arregla y lo transforma a datos listado medico
        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);// en el repository no viene ningún metodo implementado pero extiende de una clase
                                     //como paginacion devuelve un page no se neceista the stream ni toList

    }

}
