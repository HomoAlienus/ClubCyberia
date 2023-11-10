package com.forum.Cyberia.services;

import com.forum.Cyberia.models.User;
import com.forum.Cyberia.models.exceptions.UserCreationException;
import com.forum.Cyberia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insert(User user) {
        validateUser(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);
    }

    public void save(User user) {
        repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank() || user.getUsername().isEmpty())
            throw new UserCreationException("Nome de usuário inválido.");

        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank())
            throw new UserCreationException("Senha inválida.");

        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().isBlank())
            throw new UserCreationException("E-mail inválido.");

        if (repository.findByUsername(user.getUsername()) != null)
            throw new UserCreationException("Nome de usuário já existe.");

        if (repository.findByEmail(user.getEmail()) != null)
            throw new UserCreationException("Endereço de e-mail já cadastrado.");
    }
}
