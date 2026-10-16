package b4e.ru.mtp.backend.ms.user.controller;


import b4e.ru.mtp.backend.ms.user.api.dto.CreateUserRequest;
import b4e.ru.mtp.backend.ms.user.api.dto.CreateUserResponse;
import b4e.ru.mtp.backend.ms.user.api.dto.GetUserResponse;
import b4e.ru.mtp.backend.ms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/by-login")
    public ResponseEntity<?> getUserByLogin(@RequestParam String login) {
        GetUserResponse user = userService.findUserByLogin(login);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        GetUserResponse user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }



}
