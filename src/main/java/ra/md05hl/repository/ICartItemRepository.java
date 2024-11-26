package ra.md05hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.md05hl.model.entity.CartItems;

public interface ICartItemRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartIdAndProductId(Long cartId, Long productId); // Tìm sản phẩm trong giỏ hàng
}
