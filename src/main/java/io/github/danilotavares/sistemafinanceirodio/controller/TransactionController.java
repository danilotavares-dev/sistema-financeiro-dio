package io.github.danilotavares.sistemafinanceirodio.controller;

import io.github.danilotavares.sistemafinanceirodio.dto.TransactionDto;
import io.github.danilotavares.sistemafinanceirodio.model.Transaction;
import io.github.danilotavares.sistemafinanceirodio.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<Void> realizarDeposito(@RequestBody @Valid TransactionDto dto) {
        transactionService.depositar(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/saque")
    public ResponseEntity<Void> realizarSaque(@RequestBody @Valid TransactionDto dto) {
        transactionService.sacar(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/extrato/{userId}")
    public ResponseEntity<List<Transaction>> buscarExtrato(@PathVariable Long userId) {
        List<Transaction> extrato = transactionService.obterExtrato(userId);
        return ResponseEntity.ok(extrato);
    }
}
