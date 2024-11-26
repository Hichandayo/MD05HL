package ra.md05hl.controller.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.md05hl.model.entity.CartItems;
import ra.md05hl.model.entity.Carts;
import ra.md05hl.service.cart.CartServiceImpl;

import java.util.List;
@RestController
@RequestMapping("/api.myservice.com/v1/users/cart")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;
    // Lấy danh sách sản phẩm trong giỏ hàng
    @GetMapping
    public List<CartItems> getCartItems() {
        return cartService.getCartItems();
    }

    // Thêm mới sản phẩm vào giỏ hàng
    @PostMapping
    public Carts addProductToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        return cartService.addProductToCart(productId, quantity);
    }

    // Thay đổi số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{productId}")
    public Carts updateProductQuantity(@PathVariable Long productId, @RequestParam Integer quantity) {
        return cartService.updateProductQuantity(productId, quantity);
    }

    // Xóa 1 sản phẩm trong giỏ hàng
    @DeleteMapping("/{productId}")
    public void removeProductFromCart(@PathVariable Long productId) {
        cartService.removeProductFromCart(productId);
    }

    // Xóa toàn bộ sản phẩm trong giỏ hàng
    @DeleteMapping
    public void clearCart() {
        cartService.clearCart();
    }

}