package home.torquemada.pgservice.service;

import home.torquemada.pgservice.UserRepository;
import home.torquemada.pgservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PgService {

    private final UserRepository repository;

    public List<User> allUsers() {
        return repository.findAll();
    }
}
