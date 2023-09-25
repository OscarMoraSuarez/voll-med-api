package med.voll.api.Dominio.paciente;

public record DatosListadoPacientes(String nombre,String email,String telefono, String documento) {

    public DatosListadoPacientes(Paciente paciente) {

        this(paciente.getNombre(), paciente.getEmail(),paciente.getTelefono(),paciente.getDocumento());

    }
}
