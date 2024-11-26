package ra.md05hl.service.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.ChangePasswordRequest;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Roles;
import ra.md05hl.model.entity.Users;

import java.util.List;


public interface IUserService {
    ResponseDto<Users> findById(Long userId) throws NotFoundElementException;
    ResponseDto<Users> findByEmail(String email);// dùng để đổi mật khẩu
    ResponseDto<List<Users>> findAll();
    Users update(Users users, Long id);
    void changePassword(ChangePasswordRequest changePasswordRequest);
    ResponseDto<Users> lockUser(Long userId);
    ResponseDto<Users> unlockUser(Long userId);
    ResponseDto<List<Roles>> getRoles();
    ResponseDto<Users> addRoleToUser(Long userId, Long roleId);
    ResponseDto<Users> removeRoleFromUser(Long userId, Long roldId);
    Page<Users> searchUsers(String search, Pageable pageable);
}