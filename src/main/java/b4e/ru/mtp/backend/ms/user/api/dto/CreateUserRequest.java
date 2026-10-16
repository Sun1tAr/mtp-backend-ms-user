package b4e.ru.mtp.backend.ms.user.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String login;

    @NotBlank
    private String passwordHash;

}
