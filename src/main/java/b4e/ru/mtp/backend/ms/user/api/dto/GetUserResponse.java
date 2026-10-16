package b4e.ru.mtp.backend.ms.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUserResponse {

    private Long id;
    private String password;
    private String login;
}
