package b4e.ru.mtp.backend.ms.user.service;

import b4e.ru.mtp.backend.commons.dto.user.CreateUserRequest;
import b4e.ru.mtp.backend.commons.dto.user.GetUserResponse;

public interface UserService {

    void createUser(CreateUserRequest request);
    GetUserResponse findUserById(long id);

}
