package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
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
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size =20) Pageable paginacion){
        //la paginacion viene spring internamente ejecuta la misma query contra la bd trae la lista o array de entidad medico
        //internamente spring lo arregla y lo transforma a datos listado medico
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);// en el repository no viene ningún metodo implementado pero extiende de una clase
                                     //como paginacion devuelve un page no se neceista the stream ni toList
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }                           // esta nomenclatura la usa spring Data JPA para crear dinamicamente las queries
                                // y hacer el where en el select
    @PutMapping
    @Transactional
// se cierra la transacción para que se actualice hibernatemapea que caundo termine ese metodo se libere la transaccion
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        System.out.println(datosActualizarMedico);
        Medico medico=medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }
    //JPA mapea el medico que se trae de la DB y cuando está en el contexto de JPA y una vez se actualizan los datos
    // como está dentro de la misma transaccion una vex que el metodo termina la transaccion termina, cunado la
    // transaccion termina hace un commit en la DB y los cambios se guardan correctamente
    //otra ventaja de @Transactional es que si aquí tambien hay un metodo para editar la direccion que pasa si seactualizan
    // correctamente los datos del medico y sucede un error al actualizar direccion?, lps datos de medico estarían
    // guardados y habria inconsistencia de datos, con transactional eso ya está cubierto porque la transaccion no va a
    // hacer commit en la base de datos hace un rollback

    //Delete Logico
    @DeleteMapping("/{id}") // le indico es ques un pathvariable

    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        //traigo el elemento con JPA
        Medico medico=medicoRepository.getReferenceById(id);// ya se cargo a nivel del repositorio
                                                            // ya se esta trabajando con la entidad aquí directamente
        medico.desactivarMedico();

    }

    //Delete en DB
    /*@DeleteMapping("/{id}") // le indico es ques un pathvariable

    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        //traigo el elemento con JPA
        Medico medico=medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);

    }*/


}

