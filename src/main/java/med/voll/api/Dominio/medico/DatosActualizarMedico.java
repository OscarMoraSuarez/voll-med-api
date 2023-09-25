package med.voll.api.Dominio.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Dominio.direccion.DatosDireccion;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
}