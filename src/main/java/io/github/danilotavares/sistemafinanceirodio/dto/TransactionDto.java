package io.github.danilotavares.sistemafinanceirodio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record TransactionDto(
        @NotNull(message = "O ID do usuário é obrigatório.")
        Long userId,

        @NotNull(message = "O valor é obrigatório.")
        @Positive(message = "O valor deve ser maior que zero.")
        BigDecimal valor
) {}
