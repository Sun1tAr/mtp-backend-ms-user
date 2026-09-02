package b4e.ru.mtp.backend.ms.user.controller;

import b4e.ru.mtp.backend.commons.dto.user.CreateUserRequest;
import b4e.ru.mtp.backend.commons.dto.user.GetUserResponse;
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

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam long id) {
        GetUserResponse user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

}
