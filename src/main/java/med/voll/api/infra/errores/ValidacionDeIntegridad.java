package med.voll.api.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException {//Throwable responde ante errores y excepcions
                                                              // solo responde ante excepciones
    public ValidacionDeIntegridad(String s) {
        super(s);

    }
}
