package ra.md05hl.service.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.ChangePasswordRequest;
import ra.md05hl.model.entity.Roles;
import ra.md05hl.model.entity.Users;

import java.util.List;


public interface IUserService {
    Users findById(Long userId) throws NotFoundElementException;
    Users findByEmail(String email);
    Page<Users> findAll(Pageable pageable);
    Users update(Users users, Long id);
    void changePassword(ChangePasswordRequest changePasswordRequest);
    Users lockUser(Long userId);
    Users unlockUser(Long userId);
    List<Roles> getRoles();
    Users addRoleToUser(Long userId, Long roleId);
    Users removeRoleFromUser(Long userId, Long roldId);
    Page<Users> searchUsers(String search, Pageable pageable);
}