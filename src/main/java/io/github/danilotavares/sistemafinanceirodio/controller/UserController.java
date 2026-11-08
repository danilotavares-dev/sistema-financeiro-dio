package io.github.danilotavares.sistemafinanceirodio.controller;

import io.github.danilotavares.sistemafinanceirodio.dto.UserDto;
import io.github.danilotavares.sistemafinanceirodio.model.User;
import io.github.danilotavares.sistemafinanceirodio.repository.UserRepository;
import io.github.danilotavares.sistemafinanceirodio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody  UserDto userDto) {
        userService.register(userDto);
    }

    @GetMapping("/allUsers")
    public List<User> findALl() {
        return userRepository.findAll();
    }
}
