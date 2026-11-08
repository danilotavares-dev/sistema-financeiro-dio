package io.github.danilotavares.sistemafinanceirodio.service;

import io.github.danilotavares.sistemafinanceirodio.dto.TransactionDto;
import io.github.danilotavares.sistemafinanceirodio.model.Transaction;
import io.github.danilotavares.sistemafinanceirodio.model.TransactionType;
import io.github.danilotavares.sistemafinanceirodio.model.User;
import io.github.danilotavares.sistemafinanceirodio.repository.TransactionRepository;
import io.github.danilotavares.sistemafinanceirodio.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void depositar(TransactionDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID informado."));

        // Executa a lógica de soma dentro do modelo User
        user.depositar(dto.valor());
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSITO);
        transaction.setValor(dto.valor());
        transaction.setUser(user);
        transactionRepository.save(transaction);
    }

    @Transactional
    public void sacar(TransactionDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID informado."));

        user.sacar(dto.valor());
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.SAQUE);
        transaction.setValor(dto.valor());
        transaction.setUser(user);
        transactionRepository.save(transaction);
    }

    public List<Transaction> obterExtrato(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID informado.");
        }
        return transactionRepository.findByUserIdOrderByTimestampDesc(userId);
    }
}
