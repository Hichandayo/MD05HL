package ra.md05hl.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormProduct {
    @NotBlank(message = "không được để trống")
    private String name;
    @NotBlank(message = "không được để trống")
    private String description;
    @NotBlank(message = "không được để trống")
    @Min(value = 10,message = "ko đc dưới 10")
    private Double price;
    @NotBlank(message = "không được để trống")
    private List<MultipartFile> image;
    @NotBlank(message = "không được để trống")
    @Min(value = 20,message = "ko đc dưới 20")
    private Integer stock;
    private Long categoryId;


}