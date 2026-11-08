package io.github.danilotavares.sistemafinanceirodio.service;

import io.github.danilotavares.sistemafinanceirodio.dto.UserDto;
import io.github.danilotavares.sistemafinanceirodio.model.User;
import io.github.danilotavares.sistemafinanceirodio.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserDto userDto) {
        if (userRepository.existsByCpf(userDto.cpf())) {
            throw new IllegalArgumentException("O CPF informado já está cadastrado.");
        }
        if (userRepository.existsByEmail(userDto.email())) {
            throw new IllegalArgumentException("O e-mail informado já está cadastrado.");
        }

        User user = new User();
        user.setName(userDto.name());
        user.setCpf(userDto.cpf());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
        user.setEndereco(userDto.endereco());

        userRepository.save(user);
    }
}
