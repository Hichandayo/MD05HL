package ra.md05hl.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ra.md05hl.model.entity.CartItems;
import ra.md05hl.model.entity.Carts;
import ra.md05hl.repository.ICartItemRepository;
import ra.md05hl.repository.ICartRepository;
import ra.md05hl.sercurity.principal.UserDetailsCustom;


import java.util.List;


@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private ICartItemRepository cartItemRepository;
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustom userDetails = (UserDetailsCustom) authentication.getPrincipal();
        return userDetails.getId(); // Giả sử bạn có CustomUserDetails chứa userId
    }
    // Lấy danh sách sản phẩm trong giỏ hàng
    @Override
    public List<CartItems> getCartItems() {
        Long userId = getCurrentUserId();
        Carts cart = cartRepository.findByUserId(userId);
        return cart != null ? cart.getItems() : List.of();
    }
    // Thêm sản phẩm vào giỏ hàng
    @Override
    public Carts addProductToCart(Long productId, Integer quantity) {
        Long userId = getCurrentUserId();
        Carts cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Carts();
            cart.setUserId(userId);
            cartRepository.save(cart);
        }

        CartItems cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        if (cartItem == null) {
            cartItem = new CartItems();
            cartItem.setCart(cart);
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItemRepository.save(cartItem);
        return cart;
    }
    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public Carts updateProductQuantity(Long productId, Integer quantity) {
        Long userId = getCurrentUserId();
        Carts cart = cartRepository.findByUserId(userId);
        if (cart == null) throw new RuntimeException("Cart not found");

        CartItems cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        if (cartItem == null) throw new RuntimeException("Product not found in cart");

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return cart;
    }


    // Xóa 1 sản phẩm khỏi giỏ hàng
    public void removeProductFromCart(Long productId) {
        Long userId = getCurrentUserId();
        Carts cart = cartRepository.findByUserId(userId);
        if (cart == null) throw new RuntimeException("Cart not found");

        CartItems cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
        }
    }
    // Xóa giỏ hàng
    @Override
    public void clearCart() {
        Long userId = getCurrentUserId();
        Carts cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cartItemRepository.deleteAll(cart.getItems());
        }
    }
}