package code.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {
    private Long userId;

    private String userName;

    private String email;

    private String password;
}
