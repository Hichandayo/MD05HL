package ra.md05hl.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import ra.md05hl.validation.PhoneUnique;

import java.time.LocalDate;

@Data
public class StudentCreateDto {
    @NotBlank(message = "Không được để trống")
    private String name;
    @NotNull(message = "Không được để trống")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Không để trống")
    @PhoneUnique(message = "Số iện thoại đã tồn tại")
    private String phone;
    private MultipartFile file;
    @NotNull(message = "Khồng được để trống")
    private Boolean sex;
    @NotBlank(message = "Khồng được để trống")
    private String address;
}
