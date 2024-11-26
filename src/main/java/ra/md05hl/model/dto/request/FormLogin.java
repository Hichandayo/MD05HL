package ra.md05hl.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormLogin {
    @NotBlank(message = "Please enter Email !")
    private String email;
    @NotBlank(message = "Please enter Password !")
    private String password;


}
