package b4e.ru.mtp.backend.ms.user.service;


import b4e.ru.mtp.backend.ms.user.api.dto.CreateUserRequest;
import b4e.ru.mtp.backend.ms.user.api.dto.CreateUserResponse;
import b4e.ru.mtp.backend.ms.user.api.dto.GetUserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);
    GetUserResponse findUserById(long id);
    GetUserResponse findUserByLogin(String login);
}
