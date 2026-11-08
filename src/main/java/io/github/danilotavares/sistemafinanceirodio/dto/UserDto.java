package io.github.danilotavares.sistemafinanceirodio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
        @NotBlank(message = "O nome é obrigatório.")
        String name,

        @NotBlank(message = "O CPF é obrigatório.")
        @Size(min = 11, max = 14, message = "O cpf informado viola a capacidade de caracteres.")
        String cpf,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail informado está em um formato inválido.")
        String email,

        @NotBlank(message = "O telefone é obrigatório.")
        @Size(min = 11, max = 15, message = "O telefone informado viola a capacidade de caracteres.")
        String phone,

        @NotBlank(message = "O endereço é obrigatório.")
        String endereco
) {
}
