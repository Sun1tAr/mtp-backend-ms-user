package b4e.ru.mtp.backend.ms.user.repository;

import b4e.ru.mtp.backend.ms.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select count(u) > 0 from UserEntity u where u.login = :login")
    boolean existsByLogin(String login);

    @Query(value = "select * from {h-schema}app_user u where u.login = :login", nativeQuery = true)
    Optional<UserEntity> findByLogin(@Param("login") String login);

}
