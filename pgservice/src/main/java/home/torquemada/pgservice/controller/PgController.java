package home.torquemada.pgservice.controller;

import home.torquemada.pgservice.model.User;
import home.torquemada.pgservice.service.PgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PgController {

    private final PgService service;

    @GetMapping("/all")
    public List<User> allUsers() {
        log.info("All users requested!");
        return service.allUsers();
    }
}
