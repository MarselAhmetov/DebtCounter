package team404.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String email;
    //    @Email(message = "{errors.incorrect-email}")
    private String username;
    @Size(min = 8, max = 12)
    private String password;
}
