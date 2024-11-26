package ra.md05hl.model.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Carts cart;
    private Long productId;
    private Integer quantity;
}
