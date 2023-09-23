package med.voll.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")//para decirle que ruta de http esta siguiendo el metodo que vamos a crear
public class HelloController {
    @GetMapping
    public String helloWorld(){

        return "Hello world from Brazil";

    }

}
