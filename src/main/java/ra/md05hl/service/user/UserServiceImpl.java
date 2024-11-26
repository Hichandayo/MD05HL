package ra.md05hl.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.md05hl.exception.DomainException;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.ChangePasswordRequest;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Roles;
import ra.md05hl.model.entity.Users;
import ra.md05hl.repository.IRoleRepository;
import ra.md05hl.repository.IUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;
    @Override
    public ResponseDto<Users> findById(Long userId) throws NotFoundElementException {
        Users users = userRepository.findById(userId).orElseThrow(() -> new NotFoundElementException("User Id not Found"));
        return new ResponseDto<>(200, HttpStatus.OK,users);
    }

    @Override
    public ResponseDto<Users> findByEmail(String email) {
        Users users = userRepository.findByEmail(email).orElseThrow(() -> DomainException.notFound(email));
        return new ResponseDto<>(200,HttpStatus.OK,users);
    }

    @Override
    public ResponseDto<List<Users>> findAll() {
        List<Users> users = userRepository.findAll();
        return new ResponseDto<>(200,HttpStatus.OK,users);
    }

    @Override
    public Users update(Users users, Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(users.getFullName());
        user.setEmail(users.getEmail());
        user.setPhone(users.getPhone());
        user.setEmail(users.getEmail());
        user.setSex(users.getSex());
        user.setDateOfBirth(users.getDateOfBirth());
        user.setAvatar(users.getAvatar());
        user.setAddress(user.getAddress());
        // Cập nhật các thông tin khác của người dùng

        return userRepository.save(user);
    }

    @Override
    public Page<Users> searchUsers(String search, Pageable pageable) {
        return userRepository.findByFullNameContainingOrEmailContaining(search, search, pageable);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseDto<Users> user = findByEmail(userDetails.getUsername());

        Boolean checkOldPasswor = passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword());
        if(!checkOldPasswor) {
            throw DomainException.badRequest("Old Password is not matching!");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public ResponseDto<Users> lockUser(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(false);
        return userRepository.save(user);
    }

    @Override
    public ResponseDto<Users> unlockUser(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(true);
        return userRepository.save(user);
    }

    @Override
    public ResponseDto<List<Roles>> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public ResponseDto<Users> addRoleToUser(Long userId, Long roleId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Roles role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRolesSet().add(role);
        return userRepository.save(user);
    }

    @Override
    public ResponseDto<Users> removeRoleFromUser(Long userId, Long roldId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Roles role = roleRepository.findById(roldId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRolesSet().remove(role);
        return userRepository.save(user);
    }
}
