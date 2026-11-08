package io.github.danilotavares.sistemafinanceirodio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String endereco;

    @JsonIgnore
    private BigDecimal saldo = BigDecimal.ZERO;

    public void depositar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("ERRO: O valor do depósito deve ser maior que zero.");
        }

        this.saldo = this.saldo.add(valor);
        System.out.println("SUCESSO: Depósito bem sucedido.");
    }

    public void sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("ERRO: O valor do saque deve ser maior que zero.");
        }

        if (this.saldo.compareTo(valor) < 0) {
            throw new RuntimeException("ERRO: Saldo insuficiente.");
        }

        this.saldo = this.saldo.subtract(valor);
        System.out.println("SUCESSO: Saque bem sucedido.");
    }

    public BigDecimal consultarSaldo() {
        return this.saldo;
    }
}
