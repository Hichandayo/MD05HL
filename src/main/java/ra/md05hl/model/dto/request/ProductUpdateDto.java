package ra.md05hl.model.dto.request;

import lombok.Data;
import ra.md05hl.model.entity.Categories;

@Data
public class ProductUpdateDto {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String image;
    private int stock;
    private boolean status;
    private Categories categories;
}
