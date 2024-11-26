package ra.md05hl.model.dto.request;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ra.md05hl.validator.PasswordConfirm;
import ra.md05hl.validator.ValidEmail;


import java.time.LocalDate;
import java.util.List;

@Data
@PasswordConfirm(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
public class FormRegister {

    @ValidEmail
    @NotBlank(message = "không được để trống")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "không được để trống")
    private String username;

    @NotBlank(message = "không được để trống")
    @Size(min = 6, message = "Phải có ít nhất 6 kí tự")
    private String password;

    @NotBlank(message = "không được để trống")
    private String confirmPassword;

    @NotEmpty(message = "không được để trống")
    private String fullName;

    @Pattern(regexp = "^(0|\\+84)[0-9]{9,}$", message = "số điện thoại phải bắt đầu bằng 0 hoặc +84 và có ít nhất 10 kí tự !")
    @NotEmpty(message = "không được để trống")
    @Column(unique = true)
    private String phone;

    @NotEmpty(message = "không được để trống")
    private String address;

    private Boolean sex;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private List<String> roles;
}