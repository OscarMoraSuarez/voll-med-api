package med.voll.api.Dominio.medico;

import med.voll.api.Dominio.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento, DatosDireccion direccion) {




}
