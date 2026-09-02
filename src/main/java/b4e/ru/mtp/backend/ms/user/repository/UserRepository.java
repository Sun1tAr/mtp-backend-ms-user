package b4e.ru.mtp.backend.ms.user.repository;

import b4e.ru.mtp.backend.ms.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select count(u) > 0 from UserEntity u where u.login = :login")
    boolean existsByLogin(String login);

}
