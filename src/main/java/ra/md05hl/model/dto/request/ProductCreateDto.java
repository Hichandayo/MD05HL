package ra.md05hl.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductCreateDto {
    @NotBlank(message = "ko đc bỏ trống")
    private String name;
    @NotBlank(message = "ko đc để trống")
    private String description;
    @NotNull(message = "ko đc để trống")
    @Min(value = 10,message = "ko đc dưới 10")
    private Integer price;
    @NotBlank
    private String image;
    @NotNull(message = "ko đc ddeer trống")
    @Min(value = 20,message = "ko đc dưới 20")
    private Integer stock;
}
