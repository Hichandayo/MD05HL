package ra.md05hl.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.md05hl.model.dto.request.ChangePasswordRequest;
import ra.md05hl.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api.myservice.com/v1/users/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserServiceImpl userServiceImpl;
//đổi mật khẩu
    @PutMapping("/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        userServiceImpl.changePassword(changePasswordRequest);
        return ResponseEntity.ok("Updated password successfully");
    }
    //cập nhật thông tin người dùng
    //thông tin người dùng
    //Đặt hàng(hên xui)
}

