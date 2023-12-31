package med.voll.api.Dominio.paciente;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Dominio.direccion.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @Pattern(regexp = "\\d{4,6}")
        @NotBlank
        String documento,

        @NotNull
        @Valid
        DatosDireccion direccion) {

}
