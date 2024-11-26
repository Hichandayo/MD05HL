package ra.md05hl.service.cart;

import org.springframework.stereotype.Repository;
import ra.md05hl.model.entity.CartItems;
import ra.md05hl.model.entity.Carts;

import java.util.List;


@Repository

public interface ICartService {
    List<CartItems> getCartItems();
    Carts addProductToCart(Long productId, Integer quantity);
    Carts updateProductQuantity(Long productId, Integer quantity);
    void removeProductFromCart(Long productId);
    void clearCart();
}

