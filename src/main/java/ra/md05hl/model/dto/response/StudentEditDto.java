package ra.md05hl.model.dto.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import ra.md05hl.validation.PhoneUnique;

import java.time.LocalDate;

@Data
public class StudentEditDto {
    private String name;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @PhoneUnique(message = "Số điện thoại đã tồn tại")
    private String phone;

    private MultipartFile file;

    private Boolean sex;

    private String address;

    private String imageUrl;
}
