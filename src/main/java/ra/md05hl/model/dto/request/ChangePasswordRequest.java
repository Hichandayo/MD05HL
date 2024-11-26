package ra.md05hl.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ra.md05hl.validator.PasswordConfirm;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordConfirm(
        password = "newPassword",
        confirmPassword = "confirmNewPassword",
        message = "New Password and Confirm New Password are not matching!"
)
public class ChangePasswordRequest {
    @NotBlank(message = "Please enter your Old Password")
    private String oldPassword;


    @NotBlank(message = "Please enter Password")
    private String newPassword;

    @NotBlank(message = "Please enter ConfirmPassword")
    private String confirmNewPassword;
}