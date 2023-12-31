package med.voll.api.Dominio.medico;

public record DatosListadoMedico(Long id, String nombre,String  especialidad, String documento,String email) {
    //  este DTO esta especificando que datos quiero en especifico en ves de devolverlos todos
    public DatosListadoMedico(Medico medico) {
        //esta sintaxis es la adecuada para incializar record, si se hace
        //la asignacion directa tal como con las clases se genera un error de compilacion
        //porque no  tiene la sintaxis correcta
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
