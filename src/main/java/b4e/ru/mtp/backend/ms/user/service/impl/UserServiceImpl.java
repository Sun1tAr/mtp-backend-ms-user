package b4e.ru.mtp.backend.ms.user.service.impl;

import b4e.ru.mtp.backend.commons.dto.user.CreateUserRequest;
import b4e.ru.mtp.backend.commons.dto.user.CreateUserResponse;
import b4e.ru.mtp.backend.commons.dto.user.GetUserResponse;
import b4e.ru.mtp.backend.commons.exception.MtpErrorCode;
import b4e.ru.mtp.backend.commons.exception.MtpException;
import b4e.ru.mtp.backend.ms.user.entity.UserEntity;
import b4e.ru.mtp.backend.ms.user.repository.UserRepository;
import b4e.ru.mtp.backend.ms.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new MtpException(MtpErrorCode.USER_ALREADY_EXIST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(request.getLogin());
        userEntity.setPasswordHash(request.getPasswordHash());
        UserEntity saved = userRepository.save(userEntity);

        return new CreateUserResponse(saved.getId());
    }

    @Override
    @Deprecated
    public GetUserResponse findUserById(long id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new MtpException(MtpErrorCode.USER_NOT_FOUND);
        }

        UserEntity userEntity = userOpt.get();
        GetUserResponse response = new GetUserResponse();
        response.setId(userEntity.getId());
        response.setPassword(userEntity.getPasswordHash());
        response.setLogin(userEntity.getLogin());
        return response;
    }

    @Override
    public GetUserResponse findUserByLogin(String login) {
        Optional<UserEntity> userOpt = userRepository.findByLogin(login);
        if (userOpt.isEmpty()) {
            throw new MtpException(MtpErrorCode.USER_NOT_FOUND);
        }

        UserEntity userEntity = userOpt.get();
        GetUserResponse response = new GetUserResponse();
        response.setId(userEntity.getId());
        response.setPassword(userEntity.getPasswordHash());
        response.setLogin(userEntity.getLogin());
        return response;
    }
}
